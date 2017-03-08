package org.usfirst.frc.team696.robot;

public class FlywheelPID implements Runnable{
	private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	private double alpha = 0;
	private double error = 0;
	private double oldError = 0;
	private double cumulativeError = 0;
	private double outputValue = 0;
	private boolean isFinished = false;
	Thread t = new Thread(this);
	
	public FlywheelPID(double kP, double kI, double kD, double alpha){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.alpha = alpha;
	}
	
	public FlywheelPID(double kP, double kI, double kD){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	public void start(){
		t.start();
		isFinished = false;
	}
	
	public void stop(){
		isFinished = true;
	}
	
	public void setError(double error){
		oldError = this.error;
		this.error = error;
	}
	
	public void setPID(double kP, double kI, double kD, double alpha){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.alpha = alpha;
	}
	
	public void setPID(double kP, double kI, double kD){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	public void setPID(double kP){
		this.kP = kP;
	}
	
	private double P(){
		return error * kP;
	}
	
	private double I(){
		cumulativeError *= alpha;
		cumulativeError += error;
		return cumulativeError * kI;
	}
	
	private double D(){
		return (error - oldError) * kD;
	}
	
	private double constrain(double value){
		if(value > 1)return 1;
		if(value < 0)return 0;
		return value;
	}
	
	public double getValue(){
		return outputValue = constrain(outputValue);
	}
	
	public void run(){
		while(!isFinished){
			outputValue+=(P() + I() + D());
		}
	}
}

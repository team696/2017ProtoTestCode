package org.usfirst.frc.team696.robot.utilities;

public class PID implements Runnable{
	private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	private double alpha = 1;
	private double error = 0;
	private double oldError = 0;
	private double cumulativeError = 0;
	private double outputValue = 0;
	Thread t = new Thread(this);
	
	public PID(double kP, double kI, double kD, double alpha){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.alpha = alpha;
		t.start();
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
	
	public void setPID(double kP, double kI, double alpha){
		this.kP = kP;
		this.kI = kI;
		this.alpha = alpha;
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
	
	public double getValue(){
		return outputValue;
	}
	
	public void run(){
		while(true)outputValue = P() + I() + D();
	}
}

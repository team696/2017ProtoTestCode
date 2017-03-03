package org.usfirst.frc.team696.robot;

public class TakeBackHalfController implements Runnable{

	Thread t;
	String threadName = "thread";
	double error = 0;
	double oldError = 10;
	double targetRPM = 0;
	double outputValue = 0;
	double gain = 0;
	double tbh = 0;

	public TakeBackHalfController(double gain){
		this.gain = gain;
	}
	
	public void start(){
		if(t == null)t = new Thread(this, threadName);
		t.start();
	}
	
	public void setGain(double gain){
		this.gain = gain;
	}
	
	public void setTargetRPM(double targetRPM){
		this.targetRPM = targetRPM;
	}
	
	@Override
	public void run() {
		while(true){
			error = targetRPM - getRPM();
			
			outputValue += gain*error;
			if(outputValue > 1)outputValue = 1;
			if(outputValue < -1)outputValue = -1;
			
			if(oldError > 0 != error > 0){
				outputValue = 0.5 * (outputValue + tbh);
				tbh = outputValue;
				oldError = error;
			}
			
			if(targetRPM == 0)outputValue = 0;
		}
	}

	private double getRPM(){
		return Robot.masterTalon.get();
	}
	
	public double getOutputValue(){
		return outputValue;
	}
}

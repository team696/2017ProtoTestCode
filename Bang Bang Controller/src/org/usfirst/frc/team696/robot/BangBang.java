package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BangBang implements Runnable{

	Thread t = new Thread(this);
	
	public void start(){
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Robot.velocity = -Robot.masterTalon.getEncVelocity()*0.1464829974811083;
			Robot.minThreshold = SmartDashboard.getNumber("minThreshold", Robot.minThreshold);
	        SmartDashboard.putNumber("currentRPM", Robot.velocity);
	        Robot.targetRPM = SmartDashboard.getNumber("targetRPM", 2700);
	        Robot.powerForFlywheel = SmartDashboard.getNumber("powerForFlywheel", 0);
	        
	        if(Robot.velocity > Robot.targetRPM)Robot.outputValue = 0;
	        if(Robot.velocity < Robot.minThreshold)Robot.outputValue = Robot.powerForFlywheel;
	        
	        SmartDashboard.putNumber("masterTalon current", Robot.masterTalon.getOutputCurrent());
	        SmartDashboard.putNumber("slaveTalon current", Robot.slaveTalon.getOutputCurrent());
	        
	        Robot.masterTalon.set(Robot.outputValue);
	        Robot.slaveTalon.set(-Robot.outputValue);
		}
	}

}

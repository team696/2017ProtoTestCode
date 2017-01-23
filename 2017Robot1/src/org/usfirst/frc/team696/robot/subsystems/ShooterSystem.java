package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSystem extends Subsystem {

	CANTalon shooterMotorOne = new CANTalon(RobotMap.shooterMotorOne);
	CANTalon shooterMotorTwo = new CANTalon(RobotMap.shooterMotorTwo);
	double speed = 0;
	double targetRPM = 0;
	
	public ShooterSystem(){
		/*
		 * setting up Talon Motor One: Master
		 */
		shooterMotorOne.changeControlMode(TalonControlMode.Speed);
    	shooterMotorOne.set(0);
    	shooterMotorOne.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	shooterMotorOne.enableBrakeMode(false);
    	shooterMotorOne.enable();
    	
    	/*
    	 * setting up Talon Motor Two: Slave
    	 */
    	shooterMotorTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
    	shooterMotorTwo.set(shooterMotorOne.getDeviceID());
    	shooterMotorTwo.enableBrakeMode(false);
    	shooterMotorTwo.enable();
	}
	
    public void initDefaultCommand() {
    	
    }
    
    /*
     * set target RPM for flywheel
     */
    public void setTargetRPM(double RPM){
    	targetRPM = RPM;
    	run();
    }
    
    /*
     * set the inversion of the outputs of all the motors
     */
    public void setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	shooterMotorOne.reverseOutput(invertMotorOne);
    	shooterMotorTwo.reverseOutput(invertMotorTwo);
    }
    
    /*
     * set target RPM for master Talon
     */
    private void run(){
    	shooterMotorOne.setSetpoint(targetRPM);
    }
    
    /*
     * get the currents of all the motors
     */
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterMotorTwoPDP);
    }
    
}


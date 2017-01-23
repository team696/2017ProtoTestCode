package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chasis extends Subsystem {
    
	RobotDrive driveOne = new RobotDrive(RobotMap.frontLeftMotor, 
										RobotMap.rearLeftMotor, 
										RobotMap.frontRightMotor, 
										RobotMap.rearRightMotor);
	RobotDrive driveTwo = new RobotDrive(RobotMap.midLeftMotor, RobotMap.midRightMotor);
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	public Chasis(){
		
	}

    public void initDefaultCommand() {
    	
    }
    
    /*
     * set the desired speeds(-1 to 1)
     */
    public void setSpeeds(double leftSpeed, double rightSpeed){
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	run();
    }
    
    /*
     * set the inversions of all the motors
     */
    public void setInverted(boolean invertFrontLeftMotor, 
    						boolean invertMidLeftMotor,
    						boolean invertRearLeftMotor, 
    						boolean invertFrontRightMotor,
    						boolean invertMidRightMotor,
    						boolean invertRearRightMotor){
    	driveOne.setInvertedMotor(MotorType.kFrontLeft, invertFrontLeftMotor);
    	driveTwo.setInvertedMotor(MotorType.kFrontLeft, invertMidLeftMotor);
    	driveOne.setInvertedMotor(MotorType.kRearLeft, invertRearRightMotor);
    	driveOne.setInvertedMotor(MotorType.kFrontRight, invertFrontRightMotor);
    	driveTwo.setInvertedMotor(MotorType.kFrontRight, invertMidRightMotor);   	
    	driveOne.setInvertedMotor(MotorType.kRearRight, invertRearRightMotor);
    }
    
    /*
     * applies the desired speed
     */
    private void run(){
    	driveOne.tankDrive(leftSpeed, rightSpeed);
    	driveTwo.tankDrive(leftSpeed, rightSpeed);
    }
    
    /*
     * get the current from the motors
     */
    public double getFrontLeftMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.frontLeftMotorPDP);
    }
    
    public double getMidLeftMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.midLeftMotorPDP);
    }
    
    public double getRearLeftMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.rearLeftMotorPDP);
    }
    
    public double getFrontRightMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.frontRightMotorPDP);
    }
    
    public double getMidRightMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.midRightMotorPDP);
    }
    
    public double getRearRightMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.rearRightMotorPDP);
    }
    
}


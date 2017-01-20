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
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	RobotDrive drive = new RobotDrive(RobotMap.frontLeftMotor, 
										RobotMap.rearLeftMotor, 
										RobotMap.frontRightMotor, 
										RobotMap.rearRightMotor);
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	public Chasis(){
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeeds(double leftSpeed, double rightSpeed){
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	run();
    }
    
    public void setInverted(boolean invertFrontLeftMotor, 
    						boolean invertRearLeftMotor, 
    						boolean invertFrontRightMotor, 
    						boolean invertRearRightMotor){
    	drive.setInvertedMotor(MotorType.kFrontLeft, invertFrontLeftMotor);
    	drive.setInvertedMotor(MotorType.kRearLeft, invertRearRightMotor);
    	drive.setInvertedMotor(MotorType.kFrontRight, invertFrontRightMotor);
    	drive.setInvertedMotor(MotorType.kRearRight, invertRearRightMotor);
    }
    
    private void run(){
    	drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public double getFrontLeftMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.frontLeftMotorPDP);
    }
    
    public double getRearLeftMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.rearLeftMotorPDP);
    }
    
    public double getFrontRightMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.frontRightMotorPDP);
    }
    
    public double getRearRightMotorCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.rearRightMotorPDP);
    }
}


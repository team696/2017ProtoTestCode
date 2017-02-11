package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.utilities.SixMotorDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SixMotorDrive drive;
	double leftValue = 0;
	double rightValue = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public DriveTrainSubsystem(int frontLeftMotor,
    							int midLeftMotor,
    							int rearLeftMotor,
    							int frontRightMotor,
    							int midRightMotor,
    							int rearRightMotor){
    	drive = new SixMotorDrive(frontLeftMotor, 
    								midLeftMotor, 
    								rearLeftMotor, 
    								frontRightMotor, 
    								midRightMotor, 
    								rearRightMotor);
    }
 
    public void setValues(double leftValue, double rightValue){
    	this.leftValue = leftValue;
    	this.rightValue = rightValue;
    	run();
    }
    
    private void run(){
    	drive.tankDrive(leftValue, rightValue);
    }
}


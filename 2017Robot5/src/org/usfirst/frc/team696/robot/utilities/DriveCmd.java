package org.usfirst.frc.team696.robot.utilities;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveCmd extends Subsystem{
	RobotDrive driveA;
	RobotDrive driveB;
	
	public DriveCmd(int frontLeftMotor, 
					int midLeftMotor,
					int rearLeftMotor,
					int frontRightMotor,
					int midRightMotor,
					int rearRightMotor){
		driveA = new RobotDrive(frontLeftMotor, 
								rearLeftMotor, 
								frontRightMotor, 
								rearRightMotor);
		driveB = new RobotDrive(midLeftMotor,
								midRightMotor);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void tankDrive(double leftValue, double rightValue){
		driveA.tankDrive(leftValue, rightValue);
		driveB.tankDrive(leftValue, rightValue);
	}

}

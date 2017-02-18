package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class DriveCmd {
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
	
	public void tankDrive(double leftValue, double rightValue){
//		System.out.print("leftValue: " + leftValue + "   rightValue: " + rightValue);
		driveA.tankDrive(leftValue, rightValue);
		driveB.tankDrive(leftValue, rightValue);
	}
}

package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.DriveCmd;
import org.usfirst.frc.team696.robot.utilities.SixMotorDrive;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

	RobotDrive driveA;
	RobotDrive driveB;
	
	public DriveTrainSubsystem(int frontLeftMotor, int midLeftMotor, int rearLeftMotor, int frontRightMotor,
			int midRightMotor, int rearRightMotor) {
		driveA = new RobotDrive(frontLeftMotor, 
				rearLeftMotor, 
				frontRightMotor, 
				rearRightMotor);
		driveB = new RobotDrive(midLeftMotor,
				midRightMotor);
	}
	
	public void set(double leftValue, double rightValue){
		driveA.tankDrive(leftValue, rightValue);
		driveB.tankDrive(leftValue, rightValue);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}


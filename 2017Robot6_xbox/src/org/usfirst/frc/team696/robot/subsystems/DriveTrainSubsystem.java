package org.usfirst.frc.team696.robot.subsystems;


import edu.wpi.first.wpilibj.RobotDrive;
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

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void tankDrive(double leftValue, double rightValue){
		driveA.tankDrive(leftValue * 0.75, rightValue * 0.75);
		driveB.tankDrive(leftValue * 0.75, rightValue * 0.75);
		
	}
	
	public void kill() {
		driveA.tankDrive(0, 0);
		driveB.tankDrive(0, 0);
	}
}


package org.usfirst.frc.team696.robot.subsystems;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

	DifferentialDrive driveA;
	SpeedControllerGroup groupA;
	SpeedControllerGroup groupB;
	VictorSP frontLeft;
	VictorSP midLeft;
	VictorSP rearLeft;
	VictorSP frontRight;
	VictorSP midRight;
	VictorSP rearRight;
	
	public DriveTrainSubsystem(int frontLeftMotor, int midLeftMotor, int rearLeftMotor, int frontRightMotor,
			int midRightMotor, int rearRightMotor) {
//		driveA = new DifferentialDrive(frontLeftMotor,
//				rearLeftMotor,
//				frontRightMotor,
//				rearRightMotor);
//		driveB = new DifferentialDrive(midLeftMotor,
//				midRightMotor);

		this.frontLeft = new VictorSP(frontLeftMotor);
		this.midLeft = new VictorSP(midLeftMotor);
		this.rearLeft = new VictorSP(rearLeftMotor);
		this.frontRight = new VictorSP(frontRightMotor);
		this.midRight = new VictorSP(midRightMotor);
		this.rearRight = new VictorSP(rearRightMotor);

		groupA = new SpeedControllerGroup(frontLeft, midLeft, rearLeft);
		groupB = new SpeedControllerGroup(frontRight, midRight, rearRight);

		driveA = new DifferentialDrive(groupA, groupB);


	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void tankDrive(double leftValue, double rightValue){
		driveA.tankDrive(leftValue, rightValue);
	}
}


package org.usfirst.frc.team696.robot.utilities;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class DriveTrainOutput extends SixMotorDrive implements PIDOutput {

	double leftValue = 0;
	double rightValue = 0;
	double stick = 0;
	double turn = 0;
	
	public DriveTrainOutput(int frontLeftMotor, int midLeftMotor, int rearLeftMotor, int frontRightMotor,
			int midRightMotor, int rearRightMotor) {
		super(frontLeftMotor, midLeftMotor, rearLeftMotor, frontRightMotor, midRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}
	
	public void setSpeed(double stick){
		this.stick = stick;
	}
	
	public void setTurn(double turn){
		this.turn = turn;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		if(!Robot.driveStraightTempEnabled)output = turn;
//		
//		if(!Robot.driveStraightEnabled)output = turn;
		
		leftValue = stick + output;
		rightValue = stick - output;
		
		this.tankDrive(leftValue, rightValue);

	}

}


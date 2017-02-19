package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.utilities.DriveCmd;

import edu.wpi.first.wpilibj.PIDOutput;

public class DriveTrainSubsystem extends DriveCmd implements PIDOutput  {

	double leftValue = 0;
	double rightValue = 0;
	int leftSign = 1;
	int rightSign = 1;
	double tempMaxValue = 1;
	double stick = 0;
	double turn = 0;
	boolean driveStraight = true;
	
	public DriveTrainSubsystem(int frontLeftMotor, 
						int midLeftMotor, 
						int rearLeftMotor, 
						int frontRightMotor, 
						int midRightMotor,
						int rearRightMotor) {
		super(frontLeftMotor, 
				midLeftMotor, 
				rearLeftMotor, 
				frontRightMotor, 
				midRightMotor, 
				rearRightMotor);
	}
	
	public void setStick(double stick){
		this.stick = stick;
	}
	
	public void setTurn(double turn){
		this.turn = turn;
	}
	
	public void driveStraightMode(boolean driveStraight){
		this.driveStraight = driveStraight;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		if(!driveStraight)output = turn;
//		System.out.println("mode: " + driveStraight);
		leftValue = stick + output;
		rightValue = stick - output;
		
//		if(Math.abs(leftValue) > 1 && 
//				Math.abs(leftValue) > Math.abs(rightValue)){
//			tempMaxValue = Math.abs(leftValue);
//			leftValue = leftValue / tempMaxValue;
//			rightValue = rightValue / tempMaxValue;
//		}
//		
//		if(Math.abs(rightValue) > 1 &&
//				Math.abs(rightValue) > Math.abs(leftValue)){
//			tempMaxValue = Math.abs(rightValue);
//			rightValue = leftValue / tempMaxValue;
//			leftValue = rightValue / tempMaxValue;
//		}
		
//		System.out.print("leftValue: " + leftValue + "    rightValue: " + rightValue);
		this.tankDrive(leftValue, rightValue);
//		this.tankDrive(0, 0);
//		System.out.println("pidWrite");
	}

}

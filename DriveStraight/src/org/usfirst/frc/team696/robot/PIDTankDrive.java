package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDTankDrive extends DriveCmd implements PIDOutput  {

	double alpha = 0;
	double leftValue = 0;
	double rightValue = 0;
	int sign = 1;
	double tempMaxValue = 1;
	
	public PIDTankDrive(int frontLeftMotor, 
						int midLeftMotor, 
						int rearLeftMotor, 
						int frontRightMotor, 
						int midRightMotor,
						int rearRightMotor,
						double alpha) {
		super(frontLeftMotor, 
				midLeftMotor, 
				rearLeftMotor, 
				frontRightMotor, 
				midRightMotor, 
				rearRightMotor);
		this.alpha = alpha;
	}
	
	public void setStick(double stick){
		leftValue = stick;
		rightValue = stick;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		leftValue = leftValue + alpha * output;
		rightValue = rightValue - alpha * output;
		
		if(Math.abs(leftValue) > 1 && 
				Math.abs(leftValue) > Math.abs(rightValue)){
			tempMaxValue = Math.abs(leftValue);
			leftValue = leftValue / tempMaxValue;
			rightValue = rightValue / tempMaxValue;
		}
		
		if(Math.abs(rightValue) > 1 &&
				Math.abs(rightValue) > Math.abs(leftValue)){
			tempMaxValue = Math.abs(rightValue);
			rightValue = leftValue / tempMaxValue;
			leftValue = rightValue / tempMaxValue;
		}
		
		this.tankDrive(leftValue, rightValue);
		System.out.println("Reaching PIDWrite");
	}

}

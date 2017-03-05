package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.JSCommand;
import org.usfirst.frc.team696.robot.utilities.PID;

public class Drive extends JSCommand{
	
	double targetDistance = 0;
	double distanceError = 0;
	double currentDistance = 0;
	double targetDirection = 0;
	double directionError = 0;

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	
	PID distancePID = new PID(0, 0, 0, 0);
	PID directionPID = new PID(0, 0, 0, 0);
	
	public Drive(double distance, double direction){
		targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
	}
	
	public Drive(double distance, double direction, boolean inParallel){
		targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
		JSCommand.inParallel = inParallel;
	}

	public void update(){
		currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance()) / 2;
		distanceError = targetDistance - currentDistance;
		
		directionError = targetDirection - Robot.navX.getYaw();
		
		distancePID.setError(distanceError);
		directionPID.setError(directionError);
		
		speed = distancePID.getValue();
		turn = directionPID.getValue();
		
		leftValue = speed + turn;
		rightValue = speed - turn;
		
		if(Math.abs(distanceError) < 3 && Math.abs(directionError) < 1){
			JSCommand.isFinished = true;
			leftValue = 0;
			rightValue = 0;
		}
		
		Robot.drive.tankDrive(leftValue, rightValue);
		
	}
}

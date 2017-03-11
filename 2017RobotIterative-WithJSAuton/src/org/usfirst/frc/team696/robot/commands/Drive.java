package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.JSCommand;
import org.usfirst.frc.team696.robot.utilities.PID;

public class Drive {
	
	double targetDistance = 0;
	double distanceError = 0;
	double currentDistance = 0;
	double targetDirection = 0;
	double directionError = 0;

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean isFinished = false;
	double maxSpeed = 1;
	
	PID distancePID = new PID(0.03, 0, 0, 0);
	PID directionPID = new PID(0.023, 0.0000, 0.00, 0.1);
	
	public Drive(double distance, double direction, double maxSpeed){
		targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
	}
	
	public Drive(double distance, double direction, boolean inParallel){
		targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
		JSCommand.inParallel = inParallel;
	}
	
	public Drive(double distance, double direction, double diskP, double diskI, double diskD, double dirkP, double dirkI, double dirkD){
		targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
		distancePID.setPID(diskP, diskI, diskD);
		directionPID.setPID(dirkP, dirkI, dirkD);
	}
	
	public void start(){
		Robot.leftDriveEncoder.reset();
		Robot.rightDriveEncoder.reset();
	}

	public void update(){
		currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance()) / 2;
		distanceError = targetDistance - currentDistance;
		
		directionError = targetDirection - Robot.navX.getYaw();

		if(targetDirection > 180)targetDirection = targetDirection - 360;
		if(targetDirection < -180)targetDirection = targetDirection + 360;
		
		if(directionError > 180)directionError = directionError - 360;
    	if(directionError < -180)directionError = directionError + 360;
		
		distancePID.setError(distanceError);
		directionPID.setError(directionError);
		
		speed = distancePID.getValue();
		if(speed > maxSpeed)speed = maxSpeed;
		if(speed < -maxSpeed)speed = -maxSpeed;
		turn = directionPID.getValue();
		
		leftValue = speed + turn;
		rightValue = speed - turn;
		
		if(Math.abs(distanceError) < 10 && Math.abs(directionError) < 2){
			isFinished = true;
			leftValue = 0;
			rightValue = 0;
		}
		
		Robot.drive.tankDrive(leftValue, rightValue);
		
	}
	
	public boolean isFinished(){
		return isFinished;
	}
}

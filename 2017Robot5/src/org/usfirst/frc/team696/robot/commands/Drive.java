package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

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
	
	PID distancePID = new PID(0.02, 0, 0, 0);
	PID directionPID = new PID(0.0001, 0.0000, 0.00, 0.0);
	
    public Drive(double distance, double direction) {
    	requires(Robot.driveTrainSubsystem);
    	targetDistance = distance;
		targetDirection = Robot.navX.getYaw() + direction;
    }

    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
		
		if(Math.abs(distanceError) < 2 && Math.abs(directionError) < 2){
			isFinished = true;
			leftValue = 0;
			rightValue = 0;
		}

		System.out.println("targetDirection " + targetDirection + "    currentDirection " + Robot.navX.getYaw());
		
		Robot.driveTrainSubsystem.set(leftValue, rightValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
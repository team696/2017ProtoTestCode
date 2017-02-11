package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PIDControl;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	double kPDistance = 0,
			kIDistance = 0,
			kDDistance = 0,
			alphaDistance = 1;
	double kPDirection = 0,
			kIDirection = 0,
			kDDirection = 0,
			alphaDirection = 1;
	double distance,
			direction,
			maxSpeed;
	double targetDistance,
			currentDistance,
			targetDirection,
			currentDirection;
	double distanceError = 0,
			directionError = 0;
	double speed = 0,
			leftValue,
			rightValue;
	boolean firstRun = true,
			isFinished = false;
	
	PIDControl directionPIDController = new PIDControl(kPDistance, kIDistance, kDDistance, alphaDistance),
				distancePIDController = new PIDControl(kPDirection, kIDirection, kDDirection, alphaDirection);
	
    public Drive() {
    	requires(Robot.driveTrainSubsystem);
    	distance = 0;
    	direction = 0;
    	maxSpeed = 1;
    }
    
    public Drive(double distance){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	direction = 0;
    	maxSpeed = 1;
    }
    
    public Drive(double distance, double direction){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	this.direction = direction;
    	this.maxSpeed = 1;
    }
    
    public Drive(double distance, double direction, double maxSpeed){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	this.direction = direction;
    	this.maxSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance())/2;
    	currentDirection = Robot.navX.getYaw();
    	if(firstRun){
    		targetDistance = currentDistance + distance;
    		targetDirection = currentDirection + direction;
    		firstRun = false;
    	}
    	
    	distanceError = targetDistance - currentDistance;
    	directionError = targetDirection - currentDirection;
    	
    	if(directionError > 180)directionError = directionError - 360;
    	if(directionError < -180)directionError = directionError + 360;
    	
    	distancePIDController.setError(distanceError);
    	directionPIDController.setError(directionError);
    	
    	leftValue = distancePIDController.getValue() + directionPIDController.getValue();
    	rightValue = distancePIDController.getValue() + directionPIDController.getValue();
    	
    	if(Math.abs(distanceError) < 3 && Math.abs(directionError) < 5)isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	firstRun = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

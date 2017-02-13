package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PIDControl;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	double kPDistance = 0.03,
			kIDistance = 0,
			kDDistance = 0,
			alphaDistance = 0;
	double kPDirection = 0.0095,
			kIDirection = 0.0,
			kDDirection = 0,
			alphaDirection = 0;
	double distance,
			direction,
			maxSpeed;
	double targetDistance,
			currentDistance,
			targetDirection,
			currentDirection,
			tempMaxValue;
	double distanceError = 0,
			directionError = 0;
	double speed = 0,
			leftValue,
			rightValue;
	boolean firstRun = true,
			isFinished = false;
	
	PIDControl distancePIDController = new PIDControl(kPDistance, kIDistance, kDDistance, alphaDistance),
				directionPIDController = new PIDControl(kPDirection, kIDirection, kDDirection, alphaDirection);
	
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
    	System.out.println("in drive");
    }
    
    public Drive(double distance, double direction, double maxSpeed){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	this.direction = direction;
    	this.maxSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    	System.out.println("initailize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance())/2;
    	currentDirection = Robot.navX.getYaw();
    	if(firstRun){
    		Robot.leftDriveEncoder.reset();
    		Robot.rightDriveEncoder.reset();
        	currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance())/2;

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
    	rightValue = distancePIDController.getValue() - directionPIDController.getValue();
    	
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
    	
    	if(Math.abs(distanceError) < 6 && Math.abs(directionError) < 5)isFinished = true;
		
    	System.out.println("left: " + leftValue + "    right: " + rightValue + "   error: " + distanceError + "    current: " + currentDistance);
    	Robot.driveTrainSubsystem.setValues(leftValue, rightValue);
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

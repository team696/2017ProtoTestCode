package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	double distance,
			direction,
			maxSpeed,
			tolerance;
	
    public Drive() {
    	requires(Robot.driveTrainSubsystem);
    	distance = 0;
    	direction = 0;
    	maxSpeed = 1;
    	tolerance = 0;
    }
    
    public Drive(double distance){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	direction = 0;
    	maxSpeed = 1;
    	tolerance = 0;
    }
    
    public Drive(double distance, double direction){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	this.direction = direction;
    	this.maxSpeed = 1;
    	this.tolerance = 0;
    	
    }
    
    public Drive(double distance, double direction, double maxSpeed, double tolerance){
    	requires(Robot.driveTrainSubsystem);
    	this.distance = distance;
    	this.direction = direction;
    	this.maxSpeed = maxSpeed;
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

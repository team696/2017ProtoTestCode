package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LockPosition extends Command {

	double kPDirection = 0.012;
	double kIDirection = 0;
	double kDDirection = 0;
	double alphaDirection = 0;
	
    public LockPosition(double kPDirection) {
        // Use requires() here to declare subsystem dependencies
    	this.kPDirection = kPDirection;
    	this.kIDirection = 0;
    	this.kDDirection = 0;
    }
    
    public LockPosition(double kPDirection, double kIDirection, double kDDirection, double alphaDirection) {
        // Use requires() here to declare subsystem dependencies
    	this.kPDirection = kPDirection;
    	this.kIDirection = kIDirection;
    	this.kDDirection = kDDirection;
    	this.alphaDirection = alphaDirection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kPDirection = kPDirection;
    	Robot.kIDirection = kIDirection;
    	Robot.kDDirection = kDDirection;
    	Robot.alphaDirection = alphaDirection;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

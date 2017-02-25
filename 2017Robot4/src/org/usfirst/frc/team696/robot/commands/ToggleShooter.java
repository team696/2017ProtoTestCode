package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleShooter extends Command {

	double targetRPM = 0;
	boolean run = false;
	
    public ToggleShooter(double targetRPM) {
    	this.targetRPM = targetRPM;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	run = !run;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!run)targetRPM = 0;
    	Robot.targetRPM = targetRPM;
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

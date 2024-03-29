package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleHopper extends Command {

	double speed = 0.5;
	
    public ToggleHopper() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runHopper = !Robot.runHopper;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.runHopper)Robot.hopperSpeed = speed;
    	else Robot.hopperSpeed = 0;
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

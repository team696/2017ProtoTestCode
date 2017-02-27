package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleIntake extends Command {

	double speed = 0.75;
	
    public ToggleIntake() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runIntake = !Robot.runIntake;
    	System.out.println("initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.runIntake)Robot.intakeSpeed = speed;
    	else Robot.intakeSpeed = 0;
    	System.out.println("execute " + Robot.intakeSpeed);
    	
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

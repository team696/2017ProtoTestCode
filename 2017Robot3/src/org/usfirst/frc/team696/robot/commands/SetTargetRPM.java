package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTargetRPM extends Command {

	double targetRPM = 0;
	
    public SetTargetRPM(double targetRPM) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetRPM = targetRPM;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.targetRPM = targetRPM;
    	System.out.println("set TARGET execute     " + targetRPM + "     " + Robot.targetRPM);
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

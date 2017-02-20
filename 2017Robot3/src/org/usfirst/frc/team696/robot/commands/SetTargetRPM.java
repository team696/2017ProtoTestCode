package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTargetRPM extends Command {

	double targetRPM = 0;
	boolean runFlyWheel = false;
	
    public SetTargetRPM(double targetRPM) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetRPM = targetRPM;
    	requires(Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	runFlyWheel = !runFlyWheel;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!runFlyWheel)targetRPM = 0;
    	Robot.shooterSubsystem.setTargetRPM(targetRPM);
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

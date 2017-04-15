package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {

	final double targetAngle = 31;
	double currentAngle = 0;
	double error = 0;
	
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.useCamera = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.table.getNumber("Vision Target Azimuth", 0);
    	error = targetAngle - currentAngle;
    	error = Util.map(error, -160, 160, -23.5, 23.5);
    	Robot.targetDirection = Robot.navX.getYaw() + error;
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

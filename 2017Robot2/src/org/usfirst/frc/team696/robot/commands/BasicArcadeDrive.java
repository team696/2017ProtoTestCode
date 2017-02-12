package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicArcadeDrive extends Command {

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	double directionError = 0;
	double alpha = 0.001;
	double tempMaxValue = 0;
	
    public BasicArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = -Robot.oi.arduino.getRawAxis(4);
    	turn = Robot.oi.wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	turn = Util.smoothDeadZone(turn, -0.05, 0.05, -1, 1, 0) * 0.8;
    	
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	Robot.driveTrainSubsystem.setValues(leftValue, rightValue);
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

package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseGroundIntake extends Command {

	
    public CloseGroundIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
			Robot.gearIntakeSpeed = 0;
			Robot.pivotSubsystem.constrainOutput(12, -12);
			Robot.gearPivotTarget = Robot.gearPivotStowed;
			Robot.firstRunIntake = true;
			Robot.gearIntakeTimer.stop();
			Robot.gearIntakeTimer.reset();
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

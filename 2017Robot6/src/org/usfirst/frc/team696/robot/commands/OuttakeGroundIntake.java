package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OuttakeGroundIntake extends Command {

	boolean isFinished = false;
	
    public OuttakeGroundIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearInGroundPickup = false;
		if(Robot.firstRunIntake){
			Robot.gearIntakeTimer.start();
			Robot.firstRunIntake = false;
			Robot.gearIntakeSpeed = -0.4;
		}
		if(Robot.gearIntakeTimer.get() > 0.5){
			Robot.gearIntakeSpeed = -0.2;
			Robot.pivotSubsystem.constrainOutput(3, -12);
			Robot.gearPivotTarget = Robot.gearPivotOut;
			Robot.gearIntakeTimer.stop();
			Robot.gearIntakeTimer.reset();
			isFinished = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

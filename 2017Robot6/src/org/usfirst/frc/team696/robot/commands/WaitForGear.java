package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForGear extends Command {

	boolean isFinished = false;
	boolean oldBeamBreakState = false;
	Timer timer = new Timer();
	
    public WaitForGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearBeamBreakSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(!oldBeamBreakState && Robot.gearBeamBreakSubsystem.getTop())timer.start();
    	if(!oldBeamBreakState && Robot.gearBeamBreakSubsystem.getBot())timer.start();
    	if(timer.get() > 4){
//    	if(timer.get() > 0.2){
    		timer.stop();
    		isFinished = true;
    	}
//    	oldBeamBreakState = Robot.gearBeamBreakSubsystem.getTop();
    	oldBeamBreakState = Robot.gearBeamBreakSubsystem.getBot();
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

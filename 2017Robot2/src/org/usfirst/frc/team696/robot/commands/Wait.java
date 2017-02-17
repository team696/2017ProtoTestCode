package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	Timer timer = new Timer();
	double timeToWait = 0;
	boolean firstRun = true,
			isFinished = false;
	
    public Wait(double timeToWait) {
    	this.timeToWait = timeToWait;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(firstRun){
    		firstRun = false;
    		timer.start();
    	}
    	if(timer.get() > timeToWait)isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	timer.reset();
    	firstRun = true;
    	isFinished = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunBeamBreak extends Command {

	Timer timer = new Timer();
	
    public RunBeamBreak() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.gearBeamBreakSubsystem);
    	requires(Robot.redLEDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.gearBeamBreakSubsystem.get()){
    		Robot.oi.Psoc5.setOutput(5, true);
    		Robot.redLEDSubsystem.set(true);
    		Robot.closeGearFlap = false;
    	}
    	else {
    		Robot.oi.Psoc5.setOutput(5, false);
    		if(Robot.closeGearFlap){
    			if(timer.get() == 0)timer.start();
    			if(timer.get() > 0.1){
    				Robot.redLEDSubsystem.set(!Robot.redLEDSubsystem.get());
    				timer.stop();
    				timer.reset();
    			}
    		} else Robot.redLEDSubsystem.set(false);
    	}
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

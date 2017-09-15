package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLightShow extends Command {

	Timer timer = new Timer();
	int position = 5;
	
    public AutoLightShow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	for(int i = 5; i < 17; i++)Robot.oi.Psoc5.setOutput(i, false);
    	Robot.oi.Psoc5.setOutput(position, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() == 0)timer.start();
    	if(timer.get() > 0.5){
    		for(int i = 5; i < 17; i++)Robot.oi.Psoc5.setOutput(i, false);
    		if(position > 16)position = 5;
    		Robot.oi.Psoc5.setOutput(position, true);
    		position++;
    		timer.stop();
    		timer.reset();
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

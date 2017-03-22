package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopLightShow extends Command {

    public TeleopLightShow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(get(1))set(13, true);
    	else set(13, false);
    	if(get(11))set(16,true);
    	else set(16,false);
    	if(get(12))set(15,true);
    	else set(15, false);
    	if(get(13))set(13,true);
    	else set(13, false);
    	if(get(14))set(12, true);
    	else set(12, false);
    	if(get(15))set(11, true);
    	else set(10, false);
    	if(get(16))set(10, true);
    	else set(10,false);
    }

    public boolean get(int button){
    	return Robot.oi.Psoc5.getRawButton(button);
    }
    
    public void set(int output, boolean state){
    	Robot.oi.Psoc5.setOutput(output, state);
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

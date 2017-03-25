package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIXYAim extends Command {

	double targetAngle = 28;
	double error = 0;
	double k = 1;
	boolean isFinished = false;
	
    public PIXYAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.usePIXYAngle = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.parsePIXY.getXs()[0] != 0){
	    	error = targetAngle - Util.map(Robot.parsePIXY.getXs()[0], 0, 255, 0, 47);
	    	Robot.targetDirection = -error;
	    	System.out.println(error);
	    	isFinished = true;
    	} else {
    		System.out.println("x equals 0");
    	}
    }

//    public void finish(){
//    	isFinished = true;
//    }
    
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

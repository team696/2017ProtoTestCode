package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {

	final double targetPixelAngle = 31;
	double currentPixelAngle = 0;
	double error = 0;
	boolean isFinished;
	final double initialPower = 0.6;
	final double runningPower = 0.3;
	double power = 0;
	Timer timer = new Timer();
	
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.useCamera = true;
    	isFinished = true;
//    	power = initialPower;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentPixelAngle = Robot.table.getNumber("Vision Target Azimuth", 0);
    	error =  currentPixelAngle - targetPixelAngle;
    	System.out.print(error + "     ");
    	error = Util.map(error, -160, 160, -23.5, 23.5);
    	System.out.println(error);
    	Robot.targetDirection = error;
    	
//    	currentPixelAngle = Robot.table.getNumber("Vision Target Azimuth", 0);
//    	error =  currentPixelAngle - targetPixelAngle;
//    	
//    	System.out.println(error);
//    	
//    	if(timer.get() > 0.5)power = runningPower;
//    	
//    	if(error > -5)Robot.driveTrainSubsystem.tankDrive(power, -power);
//    	else if(error < 5)Robot.driveTrainSubsystem.tankDrive(-power, power);
//    	else isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

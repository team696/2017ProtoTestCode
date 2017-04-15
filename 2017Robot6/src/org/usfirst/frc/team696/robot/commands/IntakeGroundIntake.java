package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeGroundIntake extends Command {

	boolean isFinished = false;
	
    public IntakeGroundIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.PDP.getCurrent(RobotMap.gearIntakePDPChannel) > 55 && Robot.gearJamIntake.get() == 0)Robot.gearJamIntake.start();
		if(Robot.gearJamIntake.get() > 0.25 && !Robot.gearInGroundPickup){
			Robot.gearJamIntake.stop();
			Robot.gearJamIntake.reset();
		}else if(Robot.PDP.getCurrent(RobotMap.gearIntakePDPChannel) > 40 && Robot.gearJamIntake.get() > 0.2){
			Robot.gearJamIntake.stop();
			Robot.gearJamIntake.reset();
			Robot.gearIntakeTimer.start();
			Robot.gearPivotTarget = Robot.gearPivotStowed;
			Robot.gearIntakeSpeed = Robot.gearIntakeSlowSpeed;
			Robot.gearInGroundPickup = true;
		} else if(Robot.gearIntakeTimer.get() > 0.5 && Robot.gearInGroundPickup){
			Robot.gearIntakeTimer.stop();
			Robot.gearIntakeSpeed-=0.01;
			if(Robot.gearIntakeSpeed < 0.05){
				Robot.runIntake = false;
				Robot.gearIntakeTimer.stop();
				Robot.gearIntakeTimer.reset();
				isFinished = true;
			}
		} else {
			if(Robot.firstRunIntake){
				Robot.gearIntakeSpeed = 0.9;
				Robot.pivotSubsystem.constrainOutput(12, -12);
				Robot.gearPivotTarget = Robot.gearPivotOut;
				Robot.firstRunIntake = false;
			}
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

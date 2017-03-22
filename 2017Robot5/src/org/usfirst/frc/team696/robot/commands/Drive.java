package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class Drive extends Command {

	double targetDistance = 0;
	double distanceError = 0;
	double currentDistance = 0;
	double directionError = 0;

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean isFinished = false;
	double maxSpeed = 1;
	
	PID distancePID = new PID(0.02, 0, 0, 0);
	PID directionPID = new PID(1, 0.0000, 0.00, 0.0);
	
    public Drive(double distance, double direction) {
    	targetDistance = distance;
		Robot.targetDirection = direction;
    }
    
    public Drive() {
    	targetDistance = 0;
    }

    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    	Robot.targetDirection = Robot.navX.getYaw() + Robot.targetDirection;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance()) / 2;
		distanceError = targetDistance - currentDistance;
		
		directionError = Robot.targetDirection - Robot.navX.getYaw();

		if(Robot.targetDirection > 180)Robot.targetDirection = Robot.targetDirection - 360;
		if(Robot.targetDirection < -180)Robot.targetDirection = Robot.targetDirection + 360;
		
		if(directionError > 180)directionError = directionError - 360;
    	if(directionError < -180)directionError = directionError + 360;
		
		distancePID.setError(distanceError);
		directionPID.setError(directionError);
		
		speed = distancePID.getValue();
		if(speed > maxSpeed)speed = maxSpeed;
		if(speed < -maxSpeed)speed = -maxSpeed;
		turn = directionPID.getValue();
		
		leftValue = speed + turn;
		rightValue = speed - turn;
		
		if(Robot.autonomousCommand.isRunning()){
			if(Math.abs(distanceError) < 2 && Math.abs(directionError) < 2){
				isFinished = true;
				leftValue = 0;
				rightValue = 0;
			}
		}

		System.out.println(Robot.navX.getYaw() + "    " + Robot.targetDirection);
		
		if(Robot.tracking)Robot.driveTrainSubsystem.tankDrive(leftValue, rightValue);
    }
    
    public void finish(){
    	isFinished = true;
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

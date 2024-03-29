package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	double targetDistance = 0;
	double distanceError = 0;
	double currentDistance = 0;
	double directionError = 0;
	double tempTargetDirection = 0;

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean isFinished = false;
	double maxSpeed = 1;
	Timer timer = new Timer();
	
	PID distancePID = new PID(0.013, 0, 0, 0);
	PID directionPID = new PID(0.02525, 0.0000000000001, 0.000, 0.8);
	
    public Drive(double distance, double direction) {
    	targetDistance = distance;
		tempTargetDirection = direction;
    }
    
    public Drive() {
    	targetDistance = 0;
//    	Robot.targetDirection = 0;
    }

    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    	if(!Robot.usePIXYAngle)Robot.targetDirection = Robot.navX.getYaw() + tempTargetDirection;
    	else Robot.targetDirection = Robot.navX.getYaw() + Robot.targetDirection;
    	if(Robot.usePIXYAngle)directionPID.setPID(0.055, 0.05, 0.1, 0.009);
    	Robot.usePIXYAngle = false;
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
		
//		if(Robot.tracking){
//			Robot.redLEDSubsystem.set(false);
//			Robot.oi.Psoc5.setOutput(6, false);
//		}
		
		
		if(Math.abs(distanceError) < 1.5 && Math.abs(directionError) < 4){
//			if(Robot.autonomousCommand.isRunning()){
			if(timer.get() == 0)timer.start();
			if(timer.get() > 0.2){
				isFinished = true;
				leftValue = 0;
				rightValue = 0;
			}
//			} else {
//				if(Robot.tracking){
//					Robot.redLEDSubsystem.set(true);
//					Robot.oi.Psoc5.setOutput(6, true);
//				}
//			}
		} else {
			timer.stop();
			timer.reset();
		}
		
		System.out.println("direction: " + Robot.targetDirection + "    " + Robot.navX.getYaw() + "/t distance: " + targetDistance + "    " + currentDistance);

		/*if(Robot.tracking || Robot.autonomousCommand.isRunning())*/Robot.driveTrainSubsystem.tankDrive(leftValue, rightValue);
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

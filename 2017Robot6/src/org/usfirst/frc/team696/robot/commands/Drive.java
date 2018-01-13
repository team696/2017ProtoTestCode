package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public static double targetDistance = 0;
	public static double distanceError = 0;
	public static double currentDistance = 0;
	public static double directionError = 0;
	double tempTargetDirection = 0;
	double commandedTurn = 0;
	double commandedDrive = 0;

	public static double speed = 0;
	public static double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean isFinished = false;
	double maxSpeed = 0;
	double rampSpeed = 0;
	Timer isFinishedTimer = new Timer();

	public static double kPB = 0.0185; // 0.0185
	public static double kIB = 0.0001; // 0.0001
	public static double kDB = 0; // 0
	public static double alphaB = 0.5; // 0.5
	
	public static double kPF = 0.013; // 0.013
	public static double kIF = 0.0001; // 0.001
	public static double kDF = 0; // 0
	public static double alphaF = 0.5; // 0.5

	public static PID distancePID = new PID(kPB, kIB, kDB, alphaB);
	public static PID directionPID = new PID(0.03, 0.0, 0.00, 0.2);
	
    public Drive(double distance, double direction, double maxSpeed, double rampSpeed) {
    	this.targetDistance = distance;
		this.tempTargetDirection = direction;
		this.maxSpeed = maxSpeed;
		this.rampSpeed = rampSpeed;
    }
    
    public Drive() {
    	targetDistance = 0;
//    	Robot.targetDirection = 0;
    }

    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    	if(!Robot.useCamera)Robot.targetDirection = Robot.navX.getYaw() + tempTargetDirection;
    	else Robot.targetDirection = Robot.navX.getYaw() + Robot.targetDirection;
//    	maxSpeed = 0.75;
    	if(Robot.useCamera){
    		directionPID.setPID(0.08, 0.004, 0.0, 0.2);
    		maxSpeed = 0.3;
    	}
    	Robot.useCamera = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	currentDistance = -((Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance()) / 2);
		distanceError = targetDistance - currentDistance;
		
		directionError = Robot.targetDirection - Robot.navX.getYaw();
		
		if(distanceError > 0)distancePID.setPID(kPF, kIF, kDF, alphaF);
		if(distanceError < 0)distancePID.setPID(kPB, kIB, kDB, alphaB);

		if(Robot.targetDirection > 180)Robot.targetDirection = Robot.targetDirection - 360;
		if(Robot.targetDirection < -180)Robot.targetDirection = Robot.targetDirection + 360;
		
		if(directionError > 180)directionError = directionError - 360;
    	if(directionError < -180)directionError = directionError + 360;
		
		distancePID.setError(distanceError);
		directionPID.setError(directionError);

		commandedDrive = distancePID.getValue() * 5;
//		commandedTurn = distancePID.getValue();
//
		if(speed < 0.05 && commandedDrive > 0) {
			speed = 0.05;					/* instant jump up from 0 -- removes delay of wheels spinning towards beginning of joystick
												movement. */
		}

		if(speed > -0.05 && commandedDrive < 0) {
			speed = -0.05;					// instant jump down from 0
		}

		if(speed < commandedDrive) {
			speed+=rampSpeed;                //forward ramping

		}

		if(speed > commandedDrive) {
			speed-=rampSpeed;               //backward ramping
		}



		
//		speed = distancePID.getValue();
		if(speed > maxSpeed)speed = maxSpeed;
		if(speed < -maxSpeed)speed = -maxSpeed;
		turn = directionPID.getValue();
		
		leftValue = speed + turn;
		rightValue = speed - turn;
		
//		if(Robot.tracking){
//			Robot.redLEDSubsystem.set(false);
//			Robot.oi.Psoc5.setOutput(6, false);
//		}
		
		if(Math.abs(distanceError) < 2 && Math.abs(directionError) < 2){
//			if(Robot.autonomousCommand.isRunning()){
//			if(isFinishedTimer.get() == 0)isFinishedTimer.start();
//			if(isFinishedTimer.get() > 0.2){
				isFinished = true;
				leftValue = 0;
				rightValue = 0;
//			}
//			} else {
//				if(Robot.tracking){
//					Robot.redLEDSubsystem.set(true);
//					Robot.oi.Psoc5.setOutput(6, true);
//				}
//			}
		} else {
			isFinishedTimer.stop();
			isFinishedTimer.reset();
		}
		
//		System.out.println("Direction Error: " + directionError + "Target Direction: " + tempTargetDirection);

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

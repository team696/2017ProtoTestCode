package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.PIDControl;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicArcadeDrive extends Command {

	double speed = 0;
	double turn = 0;
	double turnAdjustment = 0;
	double oldTurn = 0;
	double leftValue = 0;
	double rightValue = 0;
	double directionError = 0;
	double alpha = 0.001;
	double tempMaxValue = 0;
	double targetDirection = 0;
	double currentDirection = 0;
	PIDControl directionPIDController = new PIDControl(Robot.kPDirection);
	boolean firstZero;
	
    public BasicArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	firstZero = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = -Robot.oi.arduino.getRawAxis(4);
    	turn = Robot.oi.wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Robot.turnMultipler;
    	
    	directionPIDController.setPID(Robot.kPDirection, Robot.kIDirection, Robot.kDDirection, Robot.alphaDirection);
    	
    	if(Robot.driveStraight){
	    	if(oldTurn != 0 && turn == 0){
	    		targetDirection = Robot.navX.getYaw();
	    	}
	    	oldTurn = turn;
    	
	    	currentDirection = Robot.navX.getYaw();
	    	if(turn == 0){
	        	directionError = targetDirection - currentDirection;
	        	
	        	if(directionError > 180)directionError = directionError - 360;
	        	if(directionError < -180)directionError = directionError + 360;
	        	
	        	directionPIDController.setError(directionError);
	        	turnAdjustment = directionPIDController.getValue();
	    	} else {
	    		turnAdjustment = 0;
	    	}
    	}
    	
    	leftValue = speed + (turn + turnAdjustment);
    	rightValue = speed - (turn + turnAdjustment);
    	
//    	if(Util.signOf(leftValue) == Util.signOf(rightValue)){
//	    	if(Math.abs(leftValue) > 1 && 
//					Math.abs(leftValue) > Math.abs(rightValue)){
//				tempMaxValue = Math.abs(leftValue);
//				leftValue = leftValue / tempMaxValue;
//				rightValue = rightValue / tempMaxValue;
//			}
//			
//			if(Math.abs(rightValue) > 1 &&
//					Math.abs(rightValue) > Math.abs(leftValue)){
//				tempMaxValue = Math.abs(rightValue);
//				rightValue = leftValue / tempMaxValue;
//				leftValue = rightValue / tempMaxValue;
//			}
//    	}
    	
    	Robot.driveTrainSubsystem.setValues(leftValue, rightValue);
    	System.out.println("current: " + currentDirection + "   target: " + targetDirection + "   adjustment: " + turnAdjustment);
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

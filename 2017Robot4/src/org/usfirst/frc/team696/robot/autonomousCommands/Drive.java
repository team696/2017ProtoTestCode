package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.NavXSource;
import org.usfirst.frc.team696.robot.utilities.PIDControl;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	boolean isFinished = false;
	
	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	double directionSetPoint = 0;
	double currentDirection = 0;
	double targetDistance = 0;
	double currentDistance = 0;
	double distanceError = 0;
	double speedTurnScale = 0;
	NavXSource navXSource = new NavXSource(Robot.navX);
	PIDController directionPID = new PIDController(0, 0, 0, navXSource, Robot.driveTrainSubsystem, 0.1);
	PIDControl distancePID = new PIDControl(0, 0, 0, 0);

    public Drive(double targetDistance, double targetDirection) {
        requires(Robot.driveTrainSubsystem);
        directionPID.enable();
        this.directionSetPoint = targetDirection;
        this.targetDistance = targetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leftDriveEncoder.reset();
    	Robot.rightDriveEncoder.reset();
    	currentDirection = Robot.navX.getYaw();
    	directionSetPoint = currentDirection + directionSetPoint;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	navXSource.setSetPoint(directionSetPoint);
    	directionPID.setSetpoint(directionSetPoint);
    	
    	currentDistance = (Robot.leftDriveEncoder.getDistance() + Robot.rightDriveEncoder.getDistance()) / 2;
    	distanceError = targetDistance - currentDistance;
    	distancePID.setError(distanceError);
    	
    	speed = distancePID.getValue();
    	
    	Robot.driveTrainSubsystem.setStick(speed);
    	Robot.driveTrainSubsystem.setTurn(0);
    	
    	if(Math.abs(distanceError) < 2 && Math.abs(directionPID.getError()) < 2)isFinished = true;
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

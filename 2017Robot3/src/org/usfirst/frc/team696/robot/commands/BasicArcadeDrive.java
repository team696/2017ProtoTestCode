package org.usfirst.frc.team696.robot.commands;

import java.io.IOException;
import java.net.InetAddress;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team696.robot.utilities.NavXSource;
import org.usfirst.frc.team696.robot.utilities.PIDControl;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicArcadeDrive extends Command {

	double speed = 0;
	double turn = 0;
	double leftValue = 0;
	double rightValue = 0;
	double tempMaxValue = 0;
	double directionSetPoint = 0;
	double cyclesInZero = 0;
	boolean firstZero = true;
	double speedTurnScale = 0;
	NavXSource navXSource = new NavXSource(Robot.navX);
	PIDController driveStraight = new PIDController(0.12, 0, 0.0, navXSource, Robot.driveTrainSubsystem, 0.1);
	
    public BasicArcadeDrive() {
    	requires(Robot.driveTrainSubsystem);
    	driveStraight.enable();
    }

    protected void initialize() {
    	directionSetPoint = Robot.navX.getYaw();
    }

    protected void execute() {
    	speed = -Robot.oi.arduino.getRawAxis(4);
    	turn = Robot.oi.wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	speedTurnScale = 1/(Math.abs(speed)*2 + 1);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Math.abs((speedTurnScale));
//    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0);
    	
    	if(turn == 0){
//    		if(cyclesInZero > 5){
    		if(firstZero){
    			directionSetPoint = Robot.navX.getYaw();
    			firstZero = false;
    		}
//    		} else {
//    			cyclesInZero++;
//    		}
//    		Robot.driveTrainSubsystem.driveStraightMode(true);
    	} else {
//    		cyclesInZero = 0;
    		firstZero = true;
    		Robot.driveTrainSubsystem.driveStraightMode(false);
    	}
    	
//    	directionSetPoint+=(turn*8.5);
//    	
//    	if(directionSetPoint > 180)directionSetPoint = -180;
//    	else if(directionSetPoint < -180)directionSetPoint = 180;
    	
    	navXSource.setSetPoint(directionSetPoint);
    	driveStraight.setSetpoint(directionSetPoint);
    	
    	Robot.driveTrainSubsystem.setStick(speed);
    	Robot.driveTrainSubsystem.setTurn(turn);
    	
    	System.out.println("target: " + directionSetPoint + "      current: " + Robot.navX.getYaw());
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

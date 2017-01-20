package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSystem extends Subsystem {

	CANTalon shooterMotorOne = new CANTalon(RobotMap.shooterMotorOne);
	Victor shooterMotorTwo = new Victor(RobotMap.shooterMotorTwo);
	double speed = 0;
	
	public ShooterSystem(){
		
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    public void setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	shooterMotorOne.setInverted(invertMotorOne);
    	shooterMotorTwo.setInverted(invertMotorTwo);
    }
    
    private void run(){
    	shooterMotorOne.set(speed);
    	shooterMotorTwo.set(speed);
    }
    
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterMotorTwoPDP);
    }
    
}


package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSystem extends Subsystem {
	
	Victor climberMotorOne = new Victor(RobotMap.climberMotorOne);
	Victor climberMotorTwo = new Victor(RobotMap.climberMotorTwo);
	double speed = 0;
	
	public void climberSystem(){
		
	}

    public void initDefaultCommand() {

    }
    
    /*
     * set the desired speed(-1 to 1)
     */
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    /*
     * set the inversion of all the motors
     */
    public void setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	climberMotorOne.setInverted(invertMotorOne);
    	climberMotorTwo.setInverted(invertMotorTwo);
    }
    
    /*
     * applies the desired speed
     */
    private void run(){
    	climberMotorOne.set(speed);
    	climberMotorTwo.set(speed);
    }
    
    /*
     * get the current from the motors
     */
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.climberMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.climberMotorTwoPDP);
    }
    
}


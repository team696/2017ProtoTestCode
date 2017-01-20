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
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor climberMotorOne = new Victor(RobotMap.climberMotorOne);
	Victor climberMotorTwo = new Victor(RobotMap.climberMotorTwo);
	
	double speed = 0;
	
	public void climberSystem(){
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    public void setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	climberMotorOne.setInverted(invertMotorOne);
    	climberMotorTwo.setInverted(invertMotorTwo);
    }
    
    private void run(){
    	climberMotorOne.set(speed);
    	climberMotorTwo.set(-speed);
    }
    
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.climberMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.climberMotorTwoPDP);
    }
}


package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSystem extends Subsystem {
    
	Victor intakeMotorOne = new Victor(RobotMap.intakeMotorOne);
	Victor intakeMotorTwo = new Victor(RobotMap.intakeMotorTwo);
	double speed = 0;
	
	public void intakeSystem(){
		
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
    public void  setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	intakeMotorOne.setInverted(invertMotorOne);
    	intakeMotorTwo.setInverted(invertMotorTwo);
    }
    
    /*
     * applies the desired speed
     */
    private void run(){
    	intakeMotorOne.set(speed);
    	intakeMotorTwo.set(speed);
    }
    
    /*
     * get the current from the motors
     */
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.intakeMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.intakeMotorTwoPDP);
    }
    
}


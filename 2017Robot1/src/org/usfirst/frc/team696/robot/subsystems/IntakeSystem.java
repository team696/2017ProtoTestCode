package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor intakeMotorOne = new Victor(RobotMap.intakeMotorOne);
	Victor intakeMotorTwo = new Victor(RobotMap.intakeMotorTwo);
	
	double speed = 0;
	
	public void intakeSystem(){
		
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    public void  setInverted(boolean invertMotorOne, boolean invertMotorTwo){
    	intakeMotorOne.setInverted(invertMotorOne);
    	intakeMotorTwo.setInverted(invertMotorTwo);
    }
    
    private void run(){
    	intakeMotorOne.set(speed);
    	intakeMotorTwo.set(speed);
    }
    
    public double getMotorOneCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.intakeMotorOnePDP);
    }
    
    public double getMotorTwoCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.intakeMotorTwoPDP);
    }
}


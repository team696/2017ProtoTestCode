package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterConveyorSystem extends Subsystem {
	
	Victor shooterConveyorMotor = new Victor(RobotMap.shooterConveyorMotor);
	double speed = 0;
	
	public void shooterConveyor(){
		
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
     * set the inversion of the motor
     */
    public void setInverted(boolean invertMotor){
    	shooterConveyorMotor.setInverted(invertMotor);
    }
    
    /*
     * applies the desired speed
     */
    private void run(){
    	shooterConveyorMotor.set(speed);
    }
    
    /*
     * get the current of the motor
     */
    public double getCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterConveyorMotorPDP);
    }
    
}


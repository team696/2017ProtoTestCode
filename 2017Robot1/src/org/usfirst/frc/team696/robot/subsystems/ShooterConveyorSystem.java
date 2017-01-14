package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterConveyorSystem extends Subsystem {
	
	Victor shooterConveyor = new Victor(RobotMap.shooterConveyorMotor);
	
	double speed = 0;
	
	public void shooterConveyor(){}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    private void run(){
    	shooterConveyor.set(speed);
    }
    
    public double getSpeed(){
    	return this.speed;
    }
    
    public double getCurrent(){
    	return Robot.PDP.getCurrent(RobotMap.shooterConveyorMotorPDP);
    }
}


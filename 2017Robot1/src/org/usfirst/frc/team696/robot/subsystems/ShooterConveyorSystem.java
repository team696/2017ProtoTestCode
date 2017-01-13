package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterConveyorSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor shooterConv = new Victor(RobotMap.shooterConveyor);
	
	double speed = 0;
	
	public void shooterConveyor(){
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    public void run(){
    	shooterConv.set(speed);
    }
}


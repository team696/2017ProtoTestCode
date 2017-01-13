package org.usfirst.frc.team696.robot.subsystems;

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
	
	Talon climber1 = new Talon(RobotMap.climber1);
	Talon climber2 = new Talon(RobotMap.climber2);
	
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
    
    public void run(){
    	climber1.set(speed);
    	climber2.set(-speed);
    }
}


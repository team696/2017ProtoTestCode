package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team696.robot.*;

/**
 *
 */
public class ShooterSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon shooter1 = new Talon(RobotMap.shooter1);
	Talon shooter2 = new Talon(RobotMap.shooter2);
	
	double speed = 0;
	
	public void shooterSystem(){
		
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
    	shooter1.set(speed);
    	shooter2.set(-speed);
    	
    }
}


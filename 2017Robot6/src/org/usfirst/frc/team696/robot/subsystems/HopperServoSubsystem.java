package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperServoSubsystem extends Subsystem {
	
	Servo hopperServo1;
	Servo hopperServo2;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public HopperServoSubsystem(int hopperServo1, int hopperServo2){
		
		this.hopperServo1 = new Servo(hopperServo1);
		this.hopperServo2 = new Servo(hopperServo2);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spin(){
    	
    	hopperServo1.set(1);
    	hopperServo2.setAngle(1);
    	
    }
    
    public void stop(){
    	
    	hopperServo1.set(0);
    	hopperServo2.setAngle(0);
    }
}


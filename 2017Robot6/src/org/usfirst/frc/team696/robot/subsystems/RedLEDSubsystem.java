package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RedLEDSubsystem extends Subsystem {
	
	DigitalOutput redLED;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public RedLEDSubsystem(int redLED){
		
		this.redLED = new DigitalOutput(redLED);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(boolean choice){
    	
    	redLED.set(choice);
    	
    }
}



package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionLEDSubsystem extends Subsystem {
	
	DigitalOutput visionLED;
	boolean allowOn = true;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public VisionLEDSubsystem(int visionLED){
		
		this.visionLED = new DigitalOutput(visionLED);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(boolean power){
    	
    	if(!allowOn)power = false;
    	visionLED.set(power);
    	
    }
}


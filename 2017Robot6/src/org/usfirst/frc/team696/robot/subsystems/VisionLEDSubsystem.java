package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionLEDSubsystem extends Subsystem {
	
	AnalogOutput visionLED;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public VisionLEDSubsystem(int visionLED){
		
		this.visionLED = new AnalogOutput(visionLED);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double voltage){
    	
    	visionLED.setVoltage(voltage);
    	
    }
}


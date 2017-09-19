package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GreenLEDSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DigitalOutput greenLED;
	
	public GreenLEDSubsystem(int greenLED) {
		// TODO Auto-generated constructor stub
		this.greenLED = new DigitalOutput(greenLED);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(boolean state){
    	greenLED.set(state);
    }
    
    public boolean get(){
    	return greenLED.get();
    }
}


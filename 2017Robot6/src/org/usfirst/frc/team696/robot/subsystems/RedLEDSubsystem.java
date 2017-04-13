package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RedLEDSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DigitalOutput redLED;
	
	public RedLEDSubsystem(int redLED) {
		// TODO Auto-generated constructor stub
		this.redLED = new DigitalOutput(redLED);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(boolean state){
    	redLED.set(state);
    }
    
    public boolean get(){
    	return redLED.get();
    }
}


package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionLightSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DigitalOutput visionLight;
	DigitalOutput peltier;
	
	public VisionLightSubsystem(int visionLight, int peltier) {
		// TODO Auto-generated constructor stub
		this.visionLight = new DigitalOutput(visionLight);
		this.peltier = new DigitalOutput(peltier);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(boolean on){
    	visionLight.set(on);
    	peltier.set(on);
    }
    
    public boolean getLight(){
    	return visionLight.get();
    }
    
    public boolean getPeltier(){
    	return peltier.get();
    }
    
    
}


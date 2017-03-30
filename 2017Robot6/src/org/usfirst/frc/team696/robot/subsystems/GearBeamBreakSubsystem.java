package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearBeamBreakSubsystem extends Subsystem {
	
	DigitalInput topBeamBreak;
	DigitalInput bottomBeamBreak;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearBeamBreakSubsystem(int topBeamBreak, int bottomBeamBreak){
		
		this.topBeamBreak = new DigitalInput(topBeamBreak);
		this.bottomBeamBreak = new DigitalInput(bottomBeamBreak);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getTop(){
		return topBeamBreak.get();
    	
    }
    
    public boolean getBot(){
    	return bottomBeamBreak.get();
    }
}


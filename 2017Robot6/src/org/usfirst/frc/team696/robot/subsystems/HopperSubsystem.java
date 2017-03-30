package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {
	
	VictorSP Agitator;
	VictorSP sideSwipe;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public HopperSubsystem(int Agitator, int sideSwipe){
		
		this.Agitator = new VictorSP(Agitator);
		this.sideSwipe = new VictorSP(sideSwipe);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
}


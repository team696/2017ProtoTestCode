package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VictorSP roller;
	VictorSP sideSwipe;
	VictorSP conveyor;
	
	public HopperSubsystem(int rollerMotor, int sideSwipeMotor, int conveyorMotor){
		roller = new VictorSP(rollerMotor);
		sideSwipe = new VictorSP(sideSwipeMotor);
		conveyor = new VictorSP(conveyorMotor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setConveyor(double speed){
    	conveyor.set(speed);
    }
    
    public void setHopper(double speed){
    	sideSwipe.set(speed);
    	roller.set(speed);
    }
}


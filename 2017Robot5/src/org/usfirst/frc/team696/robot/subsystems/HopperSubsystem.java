package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VictorSP agitator;
	VictorSP sideSwipe;
	
	public HopperSubsystem(/*int rollerMotor,*/int agitaterMotor, int sideSwipeMotor){
		agitator = new VictorSP(agitaterMotor);
		sideSwipe = new VictorSP(sideSwipeMotor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed){
    	sideSwipe.set(speed);
    	agitator.set(-speed/speed * 0.4);
    }
}


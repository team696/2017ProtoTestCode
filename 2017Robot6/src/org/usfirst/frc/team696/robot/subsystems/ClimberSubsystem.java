package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	
	VictorSP motorA;
	VictorSP motorB;
	
	public ClimberSubsystem(int motorPortA, int motorPortB){
		
		motorA = new VictorSP(motorPortA);
		motorB = new VictorSP(motorPortB);
		
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed){
    	
    	motorA.set(speed);
    	motorB.set(speed);
    	
    }
}


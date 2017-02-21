package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VictorSP climberMotorA;
	VictorSP climberMotorB;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ClimberSubsystem(int climberMotorA, int climberMotorB){
    	this.climberMotorA = new VictorSP(climberMotorA);
    	this.climberMotorB = new VictorSP(climberMotorB);
    }
    
    public void setSpeed(double speed){
    	climberMotorA.set(speed);
    	climberMotorB.set(speed);
    }
    
}


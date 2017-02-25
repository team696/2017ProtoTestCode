package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

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
    	if(!Robot.climberEnabld)speed = 0;
    	climberMotorA.set(speed);
    	climberMotorB.set(speed);
    }
}


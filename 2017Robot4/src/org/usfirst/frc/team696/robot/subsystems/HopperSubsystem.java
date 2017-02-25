package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	VictorSP hopperMotor;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public HopperSubsystem(int hopperMotor){
    	this.hopperMotor = new VictorSP(hopperMotor);
    }
    
    public void setSpeed(double speed){
    	if(!Robot.hopperEnabled || !Robot.shooterAtSpeed)speed = 0;
    	hopperMotor.set(speed);
    }
}


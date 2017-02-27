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
	VictorSP sideSwipeMotor;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public HopperSubsystem(int hopperMotor, int sideSwipeMotor){
    	this.hopperMotor = new VictorSP(hopperMotor);
    	this.sideSwipeMotor = new VictorSP(-sideSwipeMotor);
    }
    
    public void setSpeed(double speed){
//    	if(!Robot.hopperEnabled)speed = 0;
    	sideSwipeMotor.set(speed);
    	hopperMotor.set(speed);
    }
}


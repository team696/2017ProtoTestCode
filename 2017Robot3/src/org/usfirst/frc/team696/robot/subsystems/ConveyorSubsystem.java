package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VictorSP conveyor;
	boolean isEnabled = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ConveyorSubsystem(int conveyorMotor){
    	conveyor = new VictorSP(conveyorMotor);
    }
    
    public void disable(){
    	isEnabled = false;
    }
    
    public void enable(){
    	isEnabled = true;
    }
    
    public void setSpeed(double speed){
    	if(!isEnabled)speed = 0;
    	conveyor.set(speed);
    }
    
}


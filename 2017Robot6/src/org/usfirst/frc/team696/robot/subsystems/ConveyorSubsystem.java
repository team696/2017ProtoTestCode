package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyorSubsystem extends Subsystem {
	
	VictorSP conveyorMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ConveyorSubsystem(int conveyorMotor){
		
		this.conveyorMotor = new VictorSP(conveyorMotor);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed){
    	
    	conveyorMotor.set(speed);
    	
    }
}


package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	VictorSP conveyorMotor;
	
	public ConveyorSubsystem(int conveyorMotor){
		this.conveyorMotor = new VictorSP(conveyorMotor);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	conveyorMotor.set(speed);
    }
}


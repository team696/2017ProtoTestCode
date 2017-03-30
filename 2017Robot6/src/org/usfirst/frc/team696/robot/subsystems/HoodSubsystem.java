package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HoodSubsystem extends Subsystem {
	
	Servo hoodMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public HoodSubsystem(int hoodMotor){
		
		this.hoodMotor = new Servo(hoodMotor);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


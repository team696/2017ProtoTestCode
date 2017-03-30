package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	
	VictorSP intakeMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public IntakeSubsystem(int intakeMotor){
		
		this.intakeMotor = new VictorSP(intakeMotor);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


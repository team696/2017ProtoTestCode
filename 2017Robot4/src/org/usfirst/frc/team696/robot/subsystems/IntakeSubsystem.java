package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	VictorSP intakeMotor;
	double speedValue;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public IntakeSubsystem(int intakeMotor){
    	this.intakeMotor = new VictorSP(intakeMotor);
    	speedValue = 0;
    }
    
    public void setSpeedValue(double speedValue){
    	if(!Robot.intakeEnabled || !Robot.shooterAtSpeed)speedValue = 0;
    	intakeMotor.set(speedValue);
    }
}


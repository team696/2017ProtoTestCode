package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP intakeMotor;
	double speedValue;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public IntakeSubsystem(){
    	intakeMotor = new VictorSP(RobotMap.intakeMotor);
    	speedValue = 0;
    }
    
    public void setSpeedValue(double speedValue){
    	this.speedValue = speedValue;
    	run();
    }
    
    private void run(){
    	intakeMotor.set(speedValue);
    }
}


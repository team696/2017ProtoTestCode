package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyorSubsystem extends Subsystem {

	VictorSP conveyor;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ConveyorSubsystem(int conveyorMotor){
    	conveyor = new VictorSP(conveyorMotor);
    }
    
    public void setSpeed(double speed){
    	if(!Robot.conveyorEnabled || !Robot.shooterAtSpeed)speed = 0;
    	conveyor.set(speed);
    }
}


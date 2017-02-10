package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import Utilities.SixMotorDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SixMotorDrive drive;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public DriveTrainSubsystem(){
    	drive = new SixMotorDrive(RobotMap.frontLeftMotor, 
    							RobotMap.midLeftMotor, 
    							RobotMap.rearLeftMotor, 
    							RobotMap.frontRightMotor, 
    							RobotMap.midRightMotor, 
    							RobotMap.rearRightMotor);
    }
}


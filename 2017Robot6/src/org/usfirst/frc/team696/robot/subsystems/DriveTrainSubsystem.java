package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
	RobotDrive drive;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DriveTrainSubsystem(int leftRear, int leftFront, 
							int rightRear, int rightFront){
		
		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(double leftValue, double rightValue){
    	
    	drive.tankDrive(leftValue, rightValue);
    	
    }
}


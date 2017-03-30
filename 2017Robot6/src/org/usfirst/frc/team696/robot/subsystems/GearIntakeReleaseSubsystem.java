package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntakeReleaseSubsystem extends Subsystem {
	
	Servo leftServo;
	Servo rightServo;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearIntakeReleaseSubsystem(int leftServo, int rightServo){
		
		this.leftServo = new Servo(leftServo);
		this.rightServo = new Servo(rightServo);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(int leftAngle, int rightAngle){
    	
    	leftServo.setAngle(leftAngle);
    	rightServo.setAngle(rightAngle);
    	
    }
}


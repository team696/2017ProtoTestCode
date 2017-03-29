package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearFlapSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Servo leftServo;
	Servo rightServo;
	
	public GearFlapSubsystem(int leftServo, int rightServo){
		this.leftServo = new Servo(leftServo);
		this.rightServo = new Servo(rightServo);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openPos(){
    	leftServo.setAngle(170);
    	rightServo.setAngle(10);
    }
    
    public void closePos(){
    	leftServo.setAngle(40);
    	rightServo.setAngle(130);
    }
}


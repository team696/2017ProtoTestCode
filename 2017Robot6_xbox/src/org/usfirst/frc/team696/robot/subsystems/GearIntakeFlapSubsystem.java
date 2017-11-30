package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntakeFlapSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Servo leftServo;
	public static Servo rightServo;
	
	public GearIntakeFlapSubsystem(int leftServo, int rightServo){
		this.leftServo = new Servo(leftServo);
		this.rightServo = new Servo(rightServo);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openPos(){
    	leftServo.setAngle(175);
    	rightServo.setAngle(5);
    }
    
    public void closePos(){
    	leftServo.setAngle(40);
    	rightServo.setAngle(150);
//    	rightServo.setAngle(130);
    }
    
    public void kill() {
    	
    	leftServo.setDisabled();
    	rightServo.setDisabled();
    	
    }
}


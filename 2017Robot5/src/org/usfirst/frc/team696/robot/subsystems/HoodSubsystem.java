package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HoodSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Servo hoodServo;
	
	public HoodSubsystem(int hoodServo) {
		this.hoodServo = new Servo(hoodServo);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setAngle(double angle){
    	hoodServo.setAngle(angle);
    }
    
    public double getAngle(){
    	return hoodServo.getAngle();
    }
    
    public void disable(){
    	hoodServo.setDisabled();
    }
}


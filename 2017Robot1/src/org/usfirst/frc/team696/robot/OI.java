package org.usfirst.frc.team696.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/*
	 * Joystick Setup and Initiation
	 */
    public Joystick arduino = new Joystick(0);
    public Joystick wheel = new Joystick(1);
    
    
    /*
     * Button setup and initiation
     */
    
    
    Button shootButton = new JoystickButton(arduino, 7);
   //  shootButton.whenPressed(new Shoot());
    
    
    public OI(){
    
    	
    }
}


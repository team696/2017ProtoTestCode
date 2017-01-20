package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick arduino = new Joystick(0);
    public Joystick wheel = new Joystick(1);
    
    public OI(){
    	
    }
}


package org.usfirst.frc.team696.robot;


import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick Psoc5 = new Joystick(0);
	public Joystick wheel = new Joystick(1);
	
	public OI(){
	}
}

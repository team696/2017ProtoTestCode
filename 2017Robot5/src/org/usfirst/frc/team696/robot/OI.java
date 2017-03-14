package org.usfirst.frc.team696.robot;


import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick arduino = new Joystick(0);
	public static Joystick wheel = new Joystick(1);
	public static Joystick gamePad = new Joystick(2);
	
	public OI(){
	}
}
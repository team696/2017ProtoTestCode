package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.SetClimberSpeed;
import org.usfirst.frc.team696.robot.commands.ToggleConveyor;
import org.usfirst.frc.team696.robot.commands.ToggleHopper;
import org.usfirst.frc.team696.robot.commands.ToggleIntake;
import org.usfirst.frc.team696.robot.commands.ToggleShooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick arduino = new Joystick(0);
	public static Joystick wheel = new Joystick(1);
	public static Joystick gamePad = new Joystick(2);
	
	JoystickButton runClimber = new JoystickButton(gamePad, 8);
	
	JoystickButton toggleConveyor = new JoystickButton(gamePad, 6);
	JoystickButton toggleHopper = new JoystickButton(gamePad, 7);
	JoystickButton toggleIntake = new JoystickButton(gamePad, 5);
	
	JoystickButton toggleShooter = new JoystickButton(gamePad, 1);
	
	public OI(){
		runClimber.whenPressed(new SetClimberSpeed(-1));
		runClimber.whenReleased(new SetClimberSpeed(0));
		
		toggleConveyor.whenPressed(new ToggleConveyor());
		toggleHopper.whenPressed(new ToggleHopper());
		
		toggleIntake.whenPressed(new ToggleIntake());
		
		toggleShooter.whenPressed(new ToggleShooter());
	}
}

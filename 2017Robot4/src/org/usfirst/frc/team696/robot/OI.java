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
	
	JoystickButton runClimber = new JoystickButton(gamePad, 9);
	
	JoystickButton toggleConveyorHopper = new JoystickButton(gamePad, 2);
	JoystickButton toggleIntake = new JoystickButton(gamePad, 1);
	
	JoystickButton toggleShooter = new JoystickButton(gamePad, 5);
	
	public OI(){
		runClimber.whenPressed(new SetClimberSpeed(-1));
		runClimber.whenReleased(new SetClimberSpeed(0));
		
		toggleConveyorHopper.whenPressed(new ToggleConveyor(0.8));
		toggleConveyorHopper.whenPressed(new ToggleHopper(0.8));
		
		toggleIntake.whenPressed(new ToggleIntake(0.75));
		
		toggleShooter.whenPressed(new ToggleShooter(2500));
	}
}

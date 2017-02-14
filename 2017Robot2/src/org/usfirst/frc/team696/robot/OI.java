package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team696.robot.commands.ExampleCommand;
import org.usfirst.frc.team696.robot.commands.LockPosition;
import org.usfirst.frc.team696.robot.commands.ToggleDriveStraight;
import org.usfirst.frc.team696.robot.commands.ChangeTurnMultipler;
import org.usfirst.frc.team696.robot.commands.ToggleIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick arduino = new Joystick(0);
	public static Joystick wheel = new Joystick(1);
	public static Joystick gamePad = new Joystick(2);
	
	JoystickButton runIntake = new JoystickButton(gamePad, 5);
	JoystickButton fastTurn = new JoystickButton(wheel, 6);
	JoystickButton toggleDriveStraight = new JoystickButton(wheel, 11);
	JoystickButton lockPosition = new JoystickButton(wheel, 1);
	
	public OI(){
		runIntake.whenActive(new ToggleIntake());
		
		fastTurn.whenPressed(new ChangeTurnMultipler(1.5));
		fastTurn.whenReleased(new ChangeTurnMultipler(0.6));
		
		toggleDriveStraight.whenPressed(new ToggleDriveStraight());
		
		lockPosition.whenPressed(new LockPosition(0.04, 0.1, 0, 0.3));
		lockPosition.whenReleased(new LockPosition(0.012, 0, 0, 0));
	}
}

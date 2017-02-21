package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team696.robot.commands.ExampleCommand;
import org.usfirst.frc.team696.robot.commands.LockPosition;
import org.usfirst.frc.team696.robot.commands.RunClimber;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetTargetRPM;
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
	JoystickButton runFlyWheel = new JoystickButton(gamePad, 1);
	JoystickButton stopFlyWheel = new JoystickButton(gamePad, 2);
	JoystickButton runConveyor = new JoystickButton(gamePad, 3);
	JoystickButton runClimber = new JoystickButton(gamePad, 8);
	JoystickButton stopClimber = new JoystickButton(gamePad, 7);
	
	public OI(){
		runIntake.whenActive(new ToggleIntake());
		
		fastTurn.whenPressed(new ChangeTurnMultipler(1.5));
		fastTurn.whenReleased(new ChangeTurnMultipler(0.6));
		
		toggleDriveStraight.whenPressed(new ToggleDriveStraight());
		
		lockPosition.whenPressed(new LockPosition(0.04, 0.1, 0, 0.3));
		lockPosition.whenReleased(new LockPosition(0.012, 0, 0, 0));
		
		runFlyWheel.whenPressed(new SetTargetRPM(2500));
		stopFlyWheel.whenPressed(new SetTargetRPM(0));
		
		runConveyor.whenPressed(new SetConveyor(0.8));
		runConveyor.whenReleased(new SetConveyor(0));
		
		runClimber.whenPressed(new RunClimber(-1));
		stopClimber.whenPressed(new RunClimber(0));
		
	}
}

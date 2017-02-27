package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	Joystick xbox = new Joystick(3);
	
	RobotDrive driveA = new RobotDrive(8, 9, 2, 3);
	RobotDrive driveB = new RobotDrive(10, 4);
	
	VictorSP intakeMotor = new VictorSP(0);
	
	VictorSP climberMotorA = new VictorSP(5);
	VictorSP climberMotorB = new VictorSP(6);
	
	boolean[] oldButton = new boolean[11];
	
	boolean runIntake = false;
	
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		for(int i = 0; i < 11; i++)oldButton[i] = false;
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if(xbox.getRawButton(4)){
			climberMotorA.set(-1);
			climberMotorB.set(-1);
		} else {
			climberMotorA.set(0);
			climberMotorB.set(0);
		}
		
		if(!oldButton[6] && xbox.getRawButton(6))runIntake = !runIntake;
		if(runIntake)intakeMotor.set(0.7);
		else intakeMotor.set(0);
		
		driveA.arcadeDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(4));
		driveB.arcadeDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(4));
		
		for(int i = 1; i < 11; i++)oldButton[i] = xbox.getRawButton(i);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


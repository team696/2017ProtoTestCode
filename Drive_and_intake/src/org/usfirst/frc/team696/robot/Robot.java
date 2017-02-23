package org.usfirst.frc.team696.robot;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
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
	RobotDrive driveA = new RobotDrive(9, 8, 2, 3);
	RobotDrive driveB = new RobotDrive(7, 4);
//	Joystick arduino = new Joystick(0);
//	Joystick wheel = new Joystick(1);
	Joystick xbox = new Joystick(3);
	VictorSP intake = new VictorSP(0);
	double leftValue = 0;
	double rightValue = 0;
	double turn = 0;
	
	double speed = 0;
	boolean runIntake = false;
	boolean[] oldButton = new boolean[11];
	
	public static IMU navX;
	SerialPort port;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
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
//		speed = -arduino.getRawAxis(4);
//		turn = wheel.getRawAxis(0);
		
		speed = -xbox.getRawAxis(0);
		turn = xbox.getRawAxis(1);
		
		leftValue = (speed + turn);
		rightValue = (speed - turn);
		
		if(!oldButton[6] && xbox.getRawButton(6))runIntake = !runIntake;
		
		if(runIntake)intake.set(0.8);
		else intake.set(0);
		
		for(int i = 1; i < 11; i++)oldButton[i] = xbox.getRawButton(i);
		
//		driveA.tankDrive(leftValue, rightValue);
//		driveB.tankDrive(leftValue, rightValue);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


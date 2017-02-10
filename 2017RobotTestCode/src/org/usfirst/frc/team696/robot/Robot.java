package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
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
	
	/*
	 * Joystick
	 */
	
	Joystick joy = new Joystick(0);
	
	/*
	 * Motors
	 */
	
//	Victor vic1 = new Victor(0);
//	Victor vic2 = new Victor(1);
//	Victor vic3 = new Victor(2);
//	Victor vic4 = new Victor(3);
//	Victor vic5 = new Victor(4);
//	Victor vic6 = new Victor(5);
//	Victor vic7 = new Victor(6);
//	Victor vic8 = new Victor(7);
//	Victor vic9 = new Victor(8);
//	Victor vic10 = new Victor(9);
	
	/*
	 * Encoders
	 */
	
//	Encoder enc1 = new Encoder(1, 1);
	
	/*
	 * Variables
	 */
	
	double leftDrive;
	double rightDrive;
	double stick;
	double wheel;
	boolean[] oldButton = new boolean[11];
	int motor = 0;
	
	/*
	 * Robot Drive Setup
	 */
	
	RobotDrive driveA = new RobotDrive(9, 8, 2, 3);
	RobotDrive driveB = new RobotDrive(7, 4);
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		for(int i = 0; i <= 10; i++)oldButton[i] = false;
		
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
//		boolean nowButton = joy.getRawButton(3);
//    	if(!oldButton[3] && nowButton)motor++;
//    	oldButton[3] = nowButton;
//    	if(motor > 10)motor = 0;
//    	
//    	switch(motor){
//    	case 0: motor = 0;
//    	if(joy.getRawButton(1))vic1.set(0.5);
//    	if(joy.getRawButton(2))vic1.set(0);
//    	break;
//    	
//    	case 1: motor = 1;
//    	if(joy.getRawButton(1))vic2.set(0.5);
//    	if(joy.getRawButton(2))vic2.set(0);
//    	break;
//    	
//    	case 2: motor = 2;
//    	if(joy.getRawButton(1))vic3.set(0.5);
//    	if(joy.getRawButton(2))vic3.set(0);
//    	break;
//    	
//    	case 3: motor = 3;
//    	if(joy.getRawButton(1))vic4.set(0.5);
//    	if(joy.getRawButton(2))vic4.set(0);
//    	break;
//    	
//    	case 4: motor = 4;
//    	if(joy.getRawButton(1))vic5.set(0.5);
//    	if(joy.getRawButton(2))vic5.set(0);
//    	break;
//    	
//    	case 5: motor = 5;
//    	if(joy.getRawButton(1))vic6.set(0.5);
//    	if(joy.getRawButton(2))vic6.set(0);
//    	break;
//    	
//    	case 6: motor = 6;
//    	if(joy.getRawButton(1))vic7.set(0.5);
//    	if(joy.getRawButton(2))vic7.set(0);
//    	break;
//    	
//    	case 7: motor = 7;
//    	if(joy.getRawButton(1))vic8.set(0.5);
//    	if(joy.getRawButton(2))vic8.set(0);
//    	break;
//    	
//    	case 8: motor = 8;
//    	if(joy.getRawButton(1))vic9.set(0.5);
//    	if(joy.getRawButton(2))vic9.set(0);
//    	break;
//    	
//    	case 9: motor = 9;
//    	if(joy.getRawButton(1))vic10.set(0.5);
//    	if(joy.getRawButton(2))vic10.set(0);
//    	break;
//    	
//    	}
    	
    	System.out.println(motor);
    	
    	
		
		
		stick = -joy.getRawAxis(1);
		wheel = joy.getRawAxis(4);
		leftDrive = stick + wheel;
		rightDrive = stick - wheel;
		
		driveA.tankDrive(leftDrive, rightDrive);
		driveB.tankDrive(leftDrive, rightDrive);
		
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}


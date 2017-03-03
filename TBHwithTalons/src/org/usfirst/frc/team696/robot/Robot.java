package org.usfirst.frc.team696.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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

	public static CANTalon masterTalon = new CANTalon(2);
	CANTalon slaveTalon = new CANTalon(1);
	TakeBackHalfController tbh = new TakeBackHalfController(0);
	double gain = 2.7E-7;
	
	VictorSP conveyor = new VictorSP(1);
	VictorSP hopper = new VictorSP(10);
	VictorSP sideSwipe = new VictorSP(12);
	
	Joystick xbox = new Joystick(3);
	
	boolean firstRun = true;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		masterTalon.changeControlMode(TalonControlMode.PercentVbus);
		slaveTalon.changeControlMode(TalonControlMode.PercentVbus);
		
//		masterTalon.reverseOutput(false);
//		slaveTalon.reverseOutput(true);
//		masterTalon.reverseSensor(false);
//		masterTalon.reverseOutput(true);
//		slaveTalon.reverseOutput(true);
		masterTalon.reverseSensor(true);
		
		SmartDashboard.putNumber("gain", 2.7E-7);
		SmartDashboard.putNumber("currentRPM", 0);
		SmartDashboard.putNumber("targetRPM", 0);
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

	@Override
	public void teleopInit() {
		if(firstRun)tbh.start();
		firstRun = false;
	}

	@Override
	public void teleopPeriodic() {
		tbh.setGain(SmartDashboard.getNumber("gain", 0));
		tbh.setTargetRPM(SmartDashboard.getNumber("targetRPM", 0));
		SmartDashboard.putNumber("currentRPM", masterTalon.getSpeed());
		
		masterTalon.set(-tbh.getOutputValue());
		slaveTalon.set(tbh.getOutputValue());
		
		if(xbox.getRawButton(1)){
	    	conveyor.set(0.8);
	    	hopper.set(0.6);
	    	sideSwipe.set(-0.6);
    	} else {
    		conveyor.set(0);
    		hopper.set(0);
    		sideSwipe.set(0);
    	}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


package org.usfirst.frc.team696.robot;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
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
	
	public static IMU navX;
	SerialPort port;
	NavXSource navXSource;
	
	PIDTankDrive driveTrain = new PIDTankDrive(9, 8, 7, 2, 3, 4, 1);
	PIDController driveStraight;
	
	double directionSetPoint = 0,
			turn = 0,
			stick = 0;
	

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
		
		navXSource = new NavXSource(navX);
		
		driveStraight = new PIDController(0.1, 0, 0, navXSource, driveTrain, 0.2);
		SmartDashboard.putNumber("directionSetPoint", 0);
		driveStraight.enable();
	}

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
	public void teleopInit(){
		driveStraight.setSetpoint(navX.getYaw());
		navXSource.setSetPoint(navX.getYaw());
		directionSetPoint = navX.getYaw();
		driveStraight.enable();
		navX.zeroYaw();
	}
	
	@Override
	public void teleopPeriodic() {
		if(Math.abs(stick) > 0.05)stick = 0;
		if(Math.abs(turn) > 0.05)turn = 0;
		directionSetPoint+=turn;
		
		directionSetPoint = SmartDashboard.getNumber("directionSetPoint");
		
		if(directionSetPoint > 179)directionSetPoint = -180;
		if(directionSetPoint < -179)directionSetPoint = 180;
		navXSource.setSetPoint(directionSetPoint);
		driveStraight.setSetpoint(directionSetPoint);
		driveTrain.setStick(stick);
		driveStraight.enable();
		
	}

	@Override
	public void testInit() {
		// TODO Auto-generated method stub
		navX.zeroYaw();
	}
	
	@Override
	public void testPeriodic() {
		navXSource.setSetPoint(179);
		System.out.println(navX.getYaw() + "   " + navX.getYaw() + "   " + navXSource.pidGet() + "   ");
	}
}


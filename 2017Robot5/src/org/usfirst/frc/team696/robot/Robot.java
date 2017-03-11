package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team696.robot.autonomousCommands.Test;
import org.usfirst.frc.team696.robot.commands.RunShooter;
import org.usfirst.frc.team696.robot.commands.TeleopDrive;
import org.usfirst.frc.team696.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team696.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team696.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team696.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSubsystem;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ClimberSubsystem climberSubsystem = new ClimberSubsystem(RobotMap.climberMotorA, RobotMap.climberMotorB);
	public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
	public static HopperSubsystem hopperSubsystem = new HopperSubsystem(RobotMap.rollerMotor, RobotMap.sideSwipeMotor, RobotMap.conveyorMotor);
	public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem(RobotMap.intakeMotor);
	public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
	
	public static OI oi;
	
	public static IMU navX;
	SerialPort port;
	
	public static Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	public static Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static boolean shooterAtSpeed = false;
	public static double targetRPM = 0;
	public static double conveyorSpeed = 0;
	public static double hopperSpeed = 0;
	public static double intakeSpeed = 0;
	
	public static double targetDirection = 0;
	
	public static boolean shooterEnabled = true;
	public static boolean driveStraightEnabled = true;
	public static boolean hopperEnabled = true;
	public static boolean conveyorEnabled = true;
	public static boolean intakeEnabled = true;
	public static boolean hoodEnabled = true;
	public static boolean climberEnabld = true;
	
	public static boolean runConveyor = false;
	public static boolean runIntake = false;
	public static boolean runShooter = false;
	public static boolean runHopper = false;
	
	public static boolean driveStraightTempEnabled = true;
	
	double distancePerPulse = (4*Math.PI)/200;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putData("Auto mode", chooser);
		
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		chooser.addDefault("trash", new Test());
		chooser.addObject("nottrash", new Test());
		
		targetDirection = navX.getYaw();
		
		leftDriveEncoder.setDistancePerPulse(distancePerPulse);
		rightDriveEncoder.setDistancePerPulse(distancePerPulse);
		
		leftDriveEncoder.setReverseDirection(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		driveStraightEnabled = true;
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		driveStraightEnabled = false;
		
		Scheduler.getInstance().add(new TeleopDrive());
		Scheduler.getInstance().add(new RunShooter());
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

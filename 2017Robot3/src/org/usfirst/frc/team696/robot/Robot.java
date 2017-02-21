
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team696.robot.autonomous.DoNothing;
import org.usfirst.frc.team696.robot.commands.BasicArcadeDrive;
import org.usfirst.frc.team696.robot.commands.ExampleCommand;
import org.usfirst.frc.team696.robot.commands.RunShooter;
import org.usfirst.frc.team696.robot.subsystems.ConveyorSubsystem;
import org.usfirst.frc.team696.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ExampleSubsystem;
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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static IMU navX;
	SerialPort port;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static DriveTrainSubsystem driveTrainSubsystem 
			= new DriveTrainSubsystem(RobotMap.frontLeftMotor, 
										RobotMap.midLeftMotor, 
										RobotMap.rearLeftMotor, 
										RobotMap.frontRightMotor, 
										RobotMap.midRightMotor, 
										RobotMap.rearRightMotor);
	public static IntakeSubsystem intakeSubsystem
			= new IntakeSubsystem(RobotMap.intakeMotor);
	public static ShooterSubsystem shooterSubsystem
			= new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
	public static ConveyorSubsystem conveyorSubsystem
			= new ConveyorSubsystem(RobotMap.conveyorBeltMotor);
	
	public static Encoder leftDriveEncoder 
			= new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	public static Encoder rightDriveEncoder
			= new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	double distancePerPulseInches = (4*Math.PI)/200;
	
	public static double turnMultipler = 0.8,
							kPDirection = 0.012,
							kIDirection = 0,
							kDDirection = 0,
							alphaDirection = 0;
	public static boolean driveStraight = true;
	
	public static boolean shooterIsEnabled = false;
	public static double targetRPM = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Do Nothing", new DoNothing());
//		chooser.addObject("DriveTest", new DriveTest());
		SmartDashboard.putData("Auto mode", chooser);
		
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		leftDriveEncoder.setDistancePerPulse(distancePerPulseInches);
		rightDriveEncoder.setDistancePerPulse(distancePerPulseInches);
		
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
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		
		autonomousCommand = (Command) chooser.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();
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
		Scheduler.getInstance().add(new BasicArcadeDrive());
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

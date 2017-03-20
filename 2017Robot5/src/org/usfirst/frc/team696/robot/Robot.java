package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
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
import org.usfirst.frc.team696.robot.utilities.SixMotorDrive;
import org.usfirst.frc.team696.robot.utilities.Util;

import com.ctre.CANTalon;
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
	
	/*
	 * set up and initialize joysticks
	 */
	Joystick arduino = new Joystick(0);
	Joystick wheel = new Joystick(1);
	Joystick gamePad = new Joystick(2);
	

	public static ClimberSubsystem climberSubsystem = new ClimberSubsystem(RobotMap.climberMotorA, RobotMap.climberMotorB);
	public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
	public static HopperSubsystem hopperSubsystem = new HopperSubsystem(RobotMap.rollerMotor, RobotMap.sideSwipeMotor, RobotMap.conveyorMotor);
	public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem(RobotMap.intakeMotor);
	public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
	
	public static OI oi;
	
	public static IMU navX;
	SerialPort port;
	
	/*
	 * set up and initialize intake
	 */
	VictorSP intakeMotor = new VictorSP(RobotMap.intakeMotor);
	/*
	 * set up driving
	 */
	public static SixMotorDrive drive = new SixMotorDrive(RobotMap.frontLeftMotor, 
								RobotMap.midLeftMotor, 
								RobotMap.rearLeftMotor, 
								RobotMap.frontRightMotor, 
								RobotMap.midRightMotor, 
								RobotMap.rearRightMotor);
	
	
	public static Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	public static Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	

	
	/*
	 * Set up and initdialize CAN Talons for shooting
	 */
	CANTalon masterTalon = new CANTalon(RobotMap.masterShooterTalon);
	CANTalon slaveTalon = new CANTalon(RobotMap.slaveShooterTalon);
	
	/*
	 * set up and initialize ball chain from hopper to shooter
	 */
	VictorSP conveyorMotor = new VictorSP(RobotMap.conveyorMotor);
	VictorSP sideSwipeMotor = new VictorSP(RobotMap.sideSwipeMotor);
	VictorSP rollerMotor = new VictorSP(RobotMap.rollerMotor);
	
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
	
	/*
	 * set up climber
	 */
	VictorSP climberMotorA = new VictorSP(RobotMap.climberMotorA);
	VictorSP climberMotorB = new VictorSP(RobotMap.climberMotorB);
	
	/*
	 * setting up servo
	 */
	
	Servo MouseTrapServo = new Servo(RobotMap.hoodServo); 
	/*
	 * servo variable 
	 */
	double target = 0; 
	
	/*
	 * set up oldButton[] for gamepad
	 */
	boolean[] oldButton = new boolean[11];
	
	/*
	 * driving variables
	 */
	double speed = 0;
	double turn = 0;
	double speedTurnScale = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean firstZero = false;
	public static double directionSetPoint = 0;


	
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
		
		/*
		 * Servo angle 
		 */
		MouseTrapServo.setAngle(50);
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < 11; i++)oldButton[i] = false;
		
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
		
		/*
		 * Run intake when button 1 is pushed on gamepad
		 */
		if(gamePad.getRawButton(1) && !oldButton[1])runIntake = !runIntake;
		
		/*
		 * Run hopper and conveyor when button 6 is pushed on gamepad
		 */
		if(gamePad.getRawButton(6) && !oldButton[6]){
			runConveyor = !runConveyor;
			runHopper = !runHopper;
		}

		/*
		 * run shooter when button 5 is pushed on gamepad
		 */
		if(gamePad.getRawButton(5) && !oldButton[5])runShooter = !runShooter;
		
		/*
		 * set intake values
		 */
		if(runIntake)intakeMotor.set(0.7);
		else intakeMotor.set(0);
		
		/*
		 * set conveyor values
		 */
		if(runConveyor)conveyorMotor.set(0.8);
		else conveyorMotor.set(0);
		
		/*
		 * set hopper values
		 */
		if(runHopper){
			sideSwipeMotor.set(-0.5);
			rollerMotor.set(0.5);
		} else {
			sideSwipeMotor.set(0);
			rollerMotor.set(0);
		}
		
		/*
		 * set shooter values
		 */
		if(runShooter){
			masterTalon.setSetpoint(3325);
			masterTalon.enableControl();
			slaveTalon.enableControl();
		} else {
			masterTalon.setSetpoint(0);
			masterTalon.disableControl();
			slaveTalon.disableControl();
		}
		slaveTalon.set(masterTalon.getDeviceID());
		
		/*
		 * set climber values
		 */
		if(gamePad.getRawButton(8)){
			climberMotorA.set(-1);
			climberMotorB.set(-1);
		} else {
			climberMotorA.set(0);
			climberMotorB.set(0);
		}
		
		if(gamePad.getRawButton(9)){
		target+= 15; 
		
	}
		/*
		 * drive control
		 * speed: forward speed
		 * turn: turn rate
		 * speedTurnScael: scale to change turn rate based on speed
		 */
		speed = -arduino.getRawAxis(4);
    	turn = wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	speedTurnScale = 1/(Math.abs(speed)*2 + 1);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Math.abs((speedTurnScale));
    	
    	
		
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	drive.tankDrive(leftValue, rightValue);
    	
    	/*
    	 * get oldButtons from gamepad
    	 */
		for(int i = 1; i < 11; i++)oldButton[i] = gamePad.getRawButton(i);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

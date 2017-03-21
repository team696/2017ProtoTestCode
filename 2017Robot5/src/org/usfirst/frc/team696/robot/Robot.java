package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team696.robot.autonomousCommands.Test;
import org.usfirst.frc.team696.robot.autonomousCommands.RightGearShoot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.PIXYAim;
import org.usfirst.frc.team696.robot.commands.RunShooter;
import org.usfirst.frc.team696.robot.commands.SetClimber;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetIntake;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ConveyorSubsystem;
import org.usfirst.frc.team696.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team696.robot.subsystems.HoodSubsystem;
import org.usfirst.frc.team696.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team696.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team696.robot.utilities.ParsePIXY;
import org.usfirst.frc.team696.robot.utilities.Util;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

public class Robot extends IterativeRobot {
	
	public static ClimberSubsystem climberSubsystem;
	public static ConveyorSubsystem conveyorSubsystem;
	public static DriveTrainSubsystem driveTrainSubsystem;
	public static HoodSubsystem hoodSubsystem;
	public static HopperSubsystem hopperSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static ShooterSubsystem shooterSubsystem;
	
	public static OI oi;
	
	/*
	 * set up navX
	 */
	public static IMU navX;
	SerialPort port;
	
	/*
	 * set up pixy cam
	 */
	I2C pixy;
	public static ParsePIXY parsePIXY;
	
	public static Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	public static Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static boolean shooterAtSpeed = false;
	public static double targetRPM = 0;
	
	public static boolean runConveyor = false;
	public static boolean runIntake = false;
	public static boolean runShooter = false;
	public static boolean runHopper = false;
	public static boolean runPIXY = false;
	public static boolean firstPIXYCycle = true;
	public static boolean tracking = false;
	
	public static double targetDirection = 0;
	
	double distancePerPulse = (4*Math.PI)/200;
	
	/*
	 * set up oldButton[] arrays for different Joysticks
	 */
	boolean[] oldGamePad = new boolean[11];
	boolean[] oldWheel = new boolean[11];
	
	/*
	 * driving variables
	 */
	double speed = 0;
	double turn = 0;
	double speedTurnScale = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean firstZero = false;
	
	@Override
	public void robotInit() {
		oi = new OI();
		
		/*
		 * initialize navX
		 */
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		/*
		 * initialize pixycam
		 */
		pixy = new I2C(Port.kOnboard, 0x54);
		parsePIXY = new ParsePIXY(pixy);
		
		leftDriveEncoder.setDistancePerPulse(distancePerPulse);
		rightDriveEncoder.setDistancePerPulse(distancePerPulse);
		
		leftDriveEncoder.setReverseDirection(true);
		
		chooser.addDefault("trash", new Test());
		chooser.addObject("rightGearShoot", new RightGearShoot());
		SmartDashboard.putData("Auto mode", chooser);
		
		/*
		 * Initialize all subsystems
		 */
		climberSubsystem = new ClimberSubsystem(RobotMap.climberMotorA, RobotMap.climberMotorB);
		conveyorSubsystem = new ConveyorSubsystem(RobotMap.conveyorMotor);
		driveTrainSubsystem = new DriveTrainSubsystem(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
		hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);
		hopperSubsystem = new HopperSubsystem(RobotMap.rollerMotor, RobotMap.sideSwipeMotor);
		intakeSubsystem = new IntakeSubsystem(RobotMap.intakeMotor);
		shooterSubsystem = new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
		
		/*
		 * set off position of hood release
		 */
		hoodSubsystem.setAngle(50);
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < 11; i++)oldGamePad[i] = false;
		for(int i = 0; i < 11; i++)oldWheel[i] = false;
		
		targetDirection = navX.getYaw();
		parsePIXY.start();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		/*
		 * moving to released position at start of teleop
		 */
		hoodSubsystem.setAngle(15);
		
		Scheduler.getInstance().add(new RunShooter());
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		/*
		 * start vision tracking
		 */
		if(oi.wheel.getRawButton(4) && !oldWheel[4])runPIXY = !runPIXY;
		if(!oi.wheel.getRawButton(4) && oldWheel[4])targetDirection = navX.getYaw(); 
		
		/*
		 * Run intake when button 1 is pushed on gamepad
		 */
		if(oi.gamePad.getRawButton(1) && !oldGamePad[1])runIntake = !runIntake;
		
		/*
		 * Run hopper and conveyor when button 6 is pushed on gamepad
		 */
		if(oi.gamePad.getRawButton(6) && !oldGamePad[6]){
			runConveyor = !runConveyor;
			runHopper = !runHopper;
		}

		/*
		 * run shooter when button 5 is pushed on gamepad
		 */
		if(oi.gamePad.getRawButton(5) && !oldGamePad[5])runShooter = !runShooter;
		
		if(runPIXY){
			new PIXYAim();
			new Drive(0, targetDirection);
			runPIXY = false;
		}
		
		/*
		 * set intake values
		 */
		if(runIntake)new SetIntake(0.7);
		else new SetIntake(0);
		
		/*
		 * set conveyor values
		 */
		if(runConveyor)new SetConveyor(0.8);
		else new SetConveyor(0);
		
		/*
		 * set hopper values
		 */
		if(runHopper)new SetHopper(0.5);
		else new SetHopper(0);
		
		/*
		 * set shooter values
		 */
		if(runShooter)new SetShooter(3325);
		else new SetShooter(0);
		
		/*
		 * set climber values
		 */
		if(oi.gamePad.getRawButton(8))new SetClimber(-1);
		else new SetClimber(0);
		
		/*
		 * drive control
		 * speed: forward speed
		 * turn: turn rate
		 * speedTurnScael: scale to change turn rate based on speed
		 */
		speed = -oi.arduino.getRawAxis(4);
    	turn = oi.wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	speedTurnScale = 1/(Math.abs(speed)*2 + 1);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Math.abs((speedTurnScale));
    	
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	if(!oi.wheel.getRawButton(4))Robot.driveTrainSubsystem.tankDrive(leftValue, rightValue);
    	
    	/*
    	 * get oldButtons 
    	 */
		for(int i = 1; i < 11; i++)oldGamePad[i] = oi.gamePad.getRawButton(i);
		for(int i = 1; i < 11; i++)oldWheel[i] = oi.wheel.getRawButton(i);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

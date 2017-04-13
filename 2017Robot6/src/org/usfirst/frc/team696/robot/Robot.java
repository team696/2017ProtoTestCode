package org.usfirst.frc.team696.robot;

//works at LAR

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team696.robot.autonomousCommands.LeftPeg;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePeg;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeaveLeft;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeaveRight;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeftShoot;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeftShootVision;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegRightShoot;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegRightShootVision;
import org.usfirst.frc.team696.robot.autonomousCommands.PixyAimOnly;
import org.usfirst.frc.team696.robot.autonomousCommands.test;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.AutoLightShow;
import org.usfirst.frc.team696.robot.commands.PIXYAim;
import org.usfirst.frc.team696.robot.commands.RunBeamBreak;
import org.usfirst.frc.team696.robot.commands.RunShooter;
import org.usfirst.frc.team696.robot.commands.SetClimber;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ConveyorSubsystem;
import org.usfirst.frc.team696.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team696.robot.subsystems.GearBeamBreakSubsystem;
import org.usfirst.frc.team696.robot.subsystems.GearIntakeFlapSubsystem;
import org.usfirst.frc.team696.robot.subsystems.GreenLEDSubsystem;
import org.usfirst.frc.team696.robot.subsystems.HoodSubsystem;
import org.usfirst.frc.team696.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team696.robot.subsystems.PivotSubsystem;
import org.usfirst.frc.team696.robot.subsystems.RedLEDSubsystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team696.robot.subsystems.VisionLightSubsystem;
import org.usfirst.frc.team696.robot.utilities.ParsePIXY;
import org.usfirst.frc.team696.robot.utilities.Util;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

public class Robot extends IterativeRobot {
	
	public static ClimberSubsystem climberSubsystem = new ClimberSubsystem(RobotMap.climberMotorA, RobotMap.climberMotorB);
	public static ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem(RobotMap.conveyorMotor);
	public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
	public static GearBeamBreakSubsystem gearBeamBreakSubsystem = new GearBeamBreakSubsystem(RobotMap.topBeamBreak, RobotMap.botBeamBreak);
	public static GearIntakeFlapSubsystem gearFlapSubsystem = new GearIntakeFlapSubsystem(RobotMap.leftServo, RobotMap.rightServo);
	public static GreenLEDSubsystem greenLEDSubsystem = new GreenLEDSubsystem(RobotMap.greenLED);
	public static HoodSubsystem hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);
	public static HopperSubsystem hopperSubsystem = new HopperSubsystem(RobotMap.sideSwipeMotor);
	public static PivotSubsystem pivotSubsystem = new PivotSubsystem(RobotMap.gearPivot, RobotMap.gearIntake);
	public static RedLEDSubsystem redLEDSubsystem = new RedLEDSubsystem(RobotMap.RedLED);
	public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
	public static VisionLightSubsystem visionLightSubsystem = new VisionLightSubsystem(RobotMap.visionLight, RobotMap.peltier);
	
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
	
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
	
	public static Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static boolean shooterAtSpeed = false;
	public static double targetRPM = 0;
	
	public static boolean runConveyor = false;
	public static boolean runIntake = false;
	public static boolean runOuttake = false;
	public static boolean runShooter = false;
	public static boolean runHopper = false;
	public static boolean openGearFlap = false;
	public static boolean usePIXYAngle = false;
	
	public static double targetDirection = 0;
	public static double gearIntakeSpeed = 0;
	public static final double gearIntakeSlowSpeed = 0.5;
	public static double gearPivotTarget = 0;
	public static final double gearPivotStowed = 1.1;
	public static final double gearPivotOut = 0.69;
	public static boolean firstRunIntake = true;
	public static boolean firstRunOuttake = true;
	public static boolean gearInGroundPickup = false;
	
	double distancePerPulse = (4*Math.PI)/200;
	
	Timer gearIntakeTimer = new Timer();
	
	/*
	 * set up oldButton[] arrays for different Joysticks
	 */
	boolean[] oldPsoc5 = new boolean[17];
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
	
	Drive drive;
	
	@Override
	public void robotInit() {
		pivotSubsystem.setSetpoint(gearPivotStowed);
		visionLightSubsystem.set(false);
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
		
//		leftDriveEncoder.setReverseDirection(true);//practice
		rightDriveEncoder.setReverseDirection(true);//competition
		
		chooser.addObject("test", new test());
		chooser.addObject("Middle Peg Leve Left", new MiddlePegLeaveLeft());
		chooser.addObject("Middle Peg Leave Right", new MiddlePegLeaveRight());
		chooser.addDefault("Middle Peg", new MiddlePeg());
		chooser.addObject("Pixy Aim Only", new PixyAimOnly());
		chooser.addObject("left Peg", new LeftPeg());
		chooser.addObject("Middle Peg Left Shoot", new MiddlePegLeftShoot());
		chooser.addObject("Middle Peg Right Shoot", new MiddlePegRightShoot());
		chooser.addObject("Middle Peg Left Shoot Vision", new MiddlePegLeftShootVision());
		chooser.addObject("Middle Peg Right Shoot Vision", new MiddlePegRightShootVision());
		SmartDashboard.putData("Auto mode", chooser);
		
		/*
		 * Initialize all subsystems
		 */
//		climberSubsystem = new ClimberSubsystem(RobotMap.climberMotorA, RobotMap.climberMotorB);
//		conveyorSubsystem = new ConveyorSubsystem(RobotMap.conveyorMotor);
//		driveTrainSubsystem = new DriveTrainSubsystem(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
//		gearBeamBreakSubsystem = new GearBeamBreakSubsystem(RobotMap.beamBreak);
//		gearFlapSubsystem = new GearFlapSubsystem(RobotMap.leftServo, RobotMap.rightServo);
//		greenLEDSubsystem = new GreenLEDSubsystem(RobotMap.greenLED);
//		hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);
//		hopperSubsystem = new HopperSubsystem(RobotMap.rollerMotor, RobotMap.sideSwipeMotor);
//		intakeSubsystem = new IntakeSubsystem(RobotMap.intakeMotor);
//		redLEDSubsystem = new RedLEDSubsystem(RobotMap.RedLED);
//		shooterSubsystem = new ShooterSubsystem(RobotMap.masterShooterTalon, RobotMap.slaveShooterTalon);
		
		/*
		 * set off position of hood release
		 */
		hoodSubsystem.setAngle(150);
		
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < oldPsoc5.length; i++)oldPsoc5[i] = false;
		for(int i = 0; i < oldWheel.length; i++)oldWheel[i] = false;
		
		targetDirection = navX.getYaw();
		parsePIXY.start();
	}

	@Override
	public void disabledInit() {
		visionLightSubsystem.set(false);
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
		
		Scheduler.getInstance().add(new AutoLightShow());
		Scheduler.getInstance().add(new RunShooter());
		
		gearPivotTarget = pivotSubsystem.getPosition();
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
//		Scheduler.getInstance().add(new TeleopLightShow());
//		Scheduler.getInstance().add(new Drive());
//		Scheduler.getInstance().add(new PIXYAim());
		Scheduler.getInstance().add(new RunBeamBreak());
		Scheduler.getInstance().add(new RunShooter());
	}

	@Override
	public void teleopPeriodic() {
		System.out.println(navX.getYaw() + "    " + leftDriveEncoder.getDistance() + "     " + rightDriveEncoder.getDistance());
		
		Scheduler.getInstance().run();
		if(oi.Psoc5.getRawButton(11)){
			hoodSubsystem.setAngle(100);
		}
		
		/*
		 * set gear flap open
		 */
		if(oi.Psoc5.getRawButton(14) && !oldPsoc5[14])openGearFlap = true;
		if(oi.Psoc5.getRawButton(14)){
			runOuttake = true;
		} else {
			runOuttake = false;
		}
		if(oi.Psoc5.getRawButton(15))openGearFlap = false;
		
		/*
		 * Run intake when button 1 is pushed on gamepad
		 */
		if(oi.Psoc5.getRawButton(13) && !oldPsoc5[13])runIntake = true;
		if(!oi.Psoc5.getRawButton(13) && oldPsoc5[13]) runIntake = false;
		
		/*
		 * Run hopper and conveyor when button 6 is pushed on gamepad
		 */
		if(oi.Psoc5.getRawButton(1)){
			runConveyor = true;
			runHopper = true;
		} else {
			runConveyor = false;
			runHopper = false;
		}

		/*
		 * run shooter when button 5 is pushed on gamepad
		 */
		if(oi.Psoc5.getRawButton(2))runShooter = true;
		else runShooter = false;
		
		if(openGearFlap)gearFlapSubsystem.openPos();
		else gearFlapSubsystem.closePos();
		
		/*
		 * set conveyor values
		 */
		if(runConveyor)conveyorSubsystem.set(0.8);
		else conveyorSubsystem.set(0);
		
		/*
		 * set hopper values
		 */
		if(runHopper)hopperSubsystem.set(0.7);
		else hopperSubsystem.set(0);
		
		/*
		 * set shooter values
		 */
//		if(runShooter)targetRPM = 3325;
		if(runShooter)targetRPM = 2900;
		else targetRPM = 0;
		
		/*
		 * set climber values
		 */
		if(oi.Psoc5.getRawButton(12))climberSubsystem.set(-1);
		else climberSubsystem.set(0);
		
		if(runIntake){
			if(PDP.getCurrent(RobotMap.gearIntakePDPChannel) > 55){
				gearIntakeTimer.start();
				gearPivotTarget = gearPivotStowed;
				gearIntakeSpeed = gearIntakeSlowSpeed;
				gearInGroundPickup = true;
			} else if(gearIntakeTimer.get() > 0.5){
				gearIntakeTimer.stop();
				gearIntakeSpeed-=0.01;
				if(gearIntakeSpeed < 0.05){
					runIntake = false;
					gearIntakeTimer.stop();
					gearIntakeTimer.reset();
				}
			} else {
				if(firstRunIntake){
					gearIntakeSpeed = 1;
					pivotSubsystem.constrainOutput(12, -12);
					gearPivotTarget = gearPivotOut;
					firstRunIntake = false;
				}
			}
		} else if(runOuttake){
			gearInGroundPickup = false;
			if(firstRunIntake){
				gearIntakeTimer.start();
				firstRunIntake = false;
				gearIntakeSpeed = -0.4;
			}
			if(gearIntakeTimer.get() > 0.5){
				gearIntakeSpeed = -0.2;
				pivotSubsystem.constrainOutput(3, -12);
				gearPivotTarget = gearPivotOut;
				gearIntakeTimer.stop();
				gearIntakeTimer.reset();
			}
		} else {
			gearIntakeSpeed = 0;
			pivotSubsystem.constrainOutput(12, -12);
			gearPivotTarget = gearPivotStowed;
			firstRunIntake = true;
			gearIntakeTimer.stop();
			gearIntakeTimer.reset();
		}
		
		pivotSubsystem.setSetpoint(gearPivotTarget);
		pivotSubsystem.setIntake(gearIntakeSpeed);
		
		/*
		 * drive control
		 * speed: forward speed
		 * turn: turn rate
		 * speedTurnScael: scale to change turn rate based on speed
		 */
		speed = -oi.Psoc5.getRawAxis(0);
    	turn = oi.wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.1, 0.1, -1, 1, 0);
    	speedTurnScale = 1/(Math.abs(speed)*2 + 1);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Math.abs((speedTurnScale));
    	
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	Robot.driveTrainSubsystem.tankDrive(leftValue, rightValue);
//    	Robot.driveTrainSubsystem.tankDrive(0, 0);
    	
    	/*
    	 * get oldButtons 
    	 */
		for(int i = 1; i < oldPsoc5.length; i++)oldPsoc5[i] = oi.Psoc5.getRawButton(i);
		for(int i = 1; i < oldWheel.length; i++)oldWheel[i] = oi.wheel.getRawButton(i);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

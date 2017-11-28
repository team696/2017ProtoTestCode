package org.usfirst.frc.team696.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.CameraServer;

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
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team696.robot.autonomousCommands.LeftPeg;
import org.usfirst.frc.team696.robot.autonomousCommands.LeftPegActive;
import org.usfirst.frc.team696.robot.autonomousCommands.LeftPegLeave;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePeg;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegActive;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeaveLeft;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeaveRight;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeftShoot;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegLeftShootVision;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegRightShoot;
import org.usfirst.frc.team696.robot.autonomousCommands.MiddlePegRightShootVision;
import org.usfirst.frc.team696.robot.autonomousCommands.NoEncResetTest;
import org.usfirst.frc.team696.robot.autonomousCommands.RightPeg;
import org.usfirst.frc.team696.robot.autonomousCommands.RightPegActive;
import org.usfirst.frc.team696.robot.autonomousCommands.RightPegLeave;
import org.usfirst.frc.team696.robot.autonomousCommands.test;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Aim;
import org.usfirst.frc.team696.robot.commands.AutoLightShow;
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
	public static boolean useCamera = false;
	public static boolean stopMotion = false;
	
	public static double targetDirection = 0;
	public static double gearIntakeSpeed = 0;
	public static final double gearIntakeSlowSpeed = 0.5;
	public static double gearPivotTarget = 0;
	public static final double gearPivotStowed = PivotSubsystem.pivot.get() + 0.46;
	public static final double gearPivotOut = PivotSubsystem.pivot.get();
	public static boolean firstRunIntake = true;
	public static boolean firstRunOuttake = true;
	public static boolean gearInGroundPickup = false;
	public static boolean servoHopper = false;
	
	double distancePerPulse = (4*Math.PI)/200;
	
	public static Timer gearIntakeTimer = new Timer();
	public static Timer gearJamIntake = new Timer();
	
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
	
	public static NetworkTable table;
	public final double targetX = 0;
	
	double[] PDPCurrents = new double[16];
	
	
	@Override
	public void robotInit() {
		table = NetworkTable.getTable("SmartDashboard");
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
		
		leftDriveEncoder.setDistancePerPulse(distancePerPulse);
		rightDriveEncoder.setDistancePerPulse(distancePerPulse);
		
    	leftDriveEncoder.setReverseDirection(true);//practice
//		rightDriveEncoder.setReverseDirection(true);//competition
		
		chooser.addObject("Middle Peg Leave Left", new MiddlePegLeaveLeft());
		chooser.addObject("Middle Peg Leave Right", new MiddlePegLeaveRight());
//		chooser.addObject("Middle Peg Left Shoot", new MiddlePegLeftShoot());
//		chooser.addObject("Middle Peg Right Shoot", new MiddlePegRightShoot());
//		chooser.addObject("Middle Peg Left Shoot Vision", new MiddlePegLeftShootVision());
//		chooser.addObject("Middle Peg Right Shoot Vision", new MiddlePegRightShootVision());
//		chooser.addObject("Middle Peg Active", new MiddlePegActive());
		chooser.addObject("Left Peg", new LeftPeg());
//		chooser.addObject("Left Peg Active", new LeftPegActive());
		chooser.addObject("Left Peg Leave", new LeftPegLeave());
		chooser.addObject("Right Peg", new RightPeg());
//		chooser.addObject("Right Peg Active", new RightPegActive());
		chooser.addObject("Right Peg Leave", new RightPegLeave());
		chooser.addDefault("Middle Peg", new MiddlePeg());
		SmartDashboard.putData("Auto mode", chooser);
//		CameraServer.getInstance().addAxisCamera("10.6.96.3");
		
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
		hoodSubsystem.setAngle(139);
		
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < oldPsoc5.length; i++)oldPsoc5[i] = false;
		for(int i = 0; i < oldWheel.length; i++)oldWheel[i] = false;
		
		targetDirection = navX.getYaw();
		
		CameraServer.getInstance().addAxisCamera("10.6.96.3");
		
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
		Robot.navX.zeroYaw();
		

		if (autonomousCommand != null)
			autonomousCommand.start();
		
		Scheduler.getInstance().add(new AutoLightShow());
		Scheduler.getInstance().add(new RunShooter());
		
//		gearPivotTarget = pivotSubsystem.getPosition();
	}

	@Override
	public void autonomousPeriodic() {
		for(int i = 0; i < 16; i++){
			PDPCurrents[i] = PDP.getCurrent(i);
		}
		table.putNumberArray("PDPCurrents", PDPCurrents);
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
//		Scheduler.getInstance().add(new Aim());
//		Scheduler.getInstance().add(new RunBeamBreak());
//		Scheduler.getInstance().add(new RunShooter());
	}

	@Override
	public void teleopPeriodic() {
		
		for(int i = 0; i < 16; i++){
			PDPCurrents[i] = PDP.getCurrent(i);
		}
		table.putNumberArray("PDPCurrents", PDPCurrents);
		
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
		if(oi.xbox.getRawButton(4) && !oldWheel[4])runIntake = true;
		if(!oi.xbox.getRawButton(4) && oldWheel[4]) runIntake = false;
		
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
		if(oi.xbox.getRawButton(2))runShooter = true;
		else runShooter = false;
//		
		if(oi.Psoc5.getRawButton(14) && !oldPsoc5[14])gearFlapSubsystem.closePos();
		if(oi.Psoc5.getRawButton(15)) gearFlapSubsystem.openPos();
		
		/*
		 * Break active gear motion
		 */
		
//		if(oi.Psoc5.getRawButton(2))stopMotion = true;

		
		/*
		 * Run Hopper Servo
		 */
		
		if(oi.Psoc5.getRawButton(1)){
			servoHopper = true;
		}else{
			servoHopper = false;
		}
		
		
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

    //	if(runShooter)targetRPM = 2900;  

	//	if(runShooter)targetRPM = 3325;
//		if(runShooter)targetRPM = 3325;
//		if(runShooter)targetRPM = 2900;
// 		if(runShooter)targetRPM = 892.5;

		if(runShooter)targetRPM = 4000;
		else targetRPM = 0;
		
		/*
		 * set climber values
		 */
		if(oi.xbox.getRawButton(6))climberSubsystem.set(-1);
		else climberSubsystem.set(0);
		
		if(runIntake){
			System.out.println(PDP.getCurrent(RobotMap.gearIntakePDPChannel));
			if(PDP.getCurrent(RobotMap.gearIntakePDPChannel) > 55 && gearJamIntake.get() == 0)gearJamIntake.start();
			if(gearJamIntake.get() > 0.25 && !gearInGroundPickup){
				gearJamIntake.stop();
				gearJamIntake.reset();
			}else if(PDP.getCurrent(RobotMap.gearIntakePDPChannel) > 40 && gearJamIntake.get() > 0.2){
				gearJamIntake.stop();
				gearJamIntake.reset();
				gearIntakeTimer.start();
				gearPivotTarget = gearPivotStowed;
				gearIntakeSpeed = gearIntakeSlowSpeed;
				gearInGroundPickup = true;
			} else if(gearIntakeTimer.get() > 0.5 && gearInGroundPickup){
				gearIntakeTimer.stop();
				gearIntakeSpeed-=0.01;
				if(gearIntakeSpeed < 0.05){
					runIntake = false;
					gearIntakeTimer.stop();
					gearIntakeTimer.reset();
				}
			} else {
				if(firstRunIntake){
					gearIntakeSpeed = 0.9;
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
		
//		if(stopMotion){
//			PivotSubsystem.stopMotion();
//		}
		
		/*
		 * drive control
		 * speed: forward speed
		 * turn: turn rate
		 * speedTurnScael: scale to change turn rate based on speed
		 */
		speed = -oi.xbox.getRawAxis(1);
    	turn = oi.xbox.getRawAxis(4) *0.75;
    	speed = Util.smoothDeadZone(speed, -0.1, 0.1, -1, 1, 0);
    	speed = Util.deadZone(speed, -0.1, 0.1, 0);
//    	speedTurnScale = 1/(Math.abs(speed)*1.2 + 1.5);
    	turn = Util.smoothDeadZone(turn, -0.15, 0.15, -1, 1, 0);
//    	turn = Util.deadZone(turn, -0.2, 0.2, 0) * Math.abs((speedTurnScale));
    	
//    	System.out.println("Left Servo: " + gearFlapSubsystem.leftServo.getAngle() + "Right Servo: " + gearFlapSubsystem.rightServo.getAngle() + "    " + openGearFlap);
    	System.out.println(Robot.navX.getYaw());
    	
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	Robot.driveTrainSubsystem.tankDrive(leftValue, rightValue);
//    	Robot.driveTrainSubsystem.tankDrive(0, 0);
    	
    	/*
    	 * get oldButtons 
    	 */
		for(int i = 1; i < oldPsoc5.length; i++)oldPsoc5[i] = oi.Psoc5.getRawButton(i);
		for(int i = 1; i < oldWheel.length; i++)oldWheel[i] = oi.xbox.getRawButton(i);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

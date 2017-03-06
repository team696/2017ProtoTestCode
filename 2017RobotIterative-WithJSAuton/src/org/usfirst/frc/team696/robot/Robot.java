package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.utilities.SixMotorDrive;
import org.usfirst.frc.team696.robot.utilities.Util;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	/*
	 * set up and initialize joysticks
	 */
	Joystick arduino = new Joystick(0);
	Joystick wheel = new Joystick(1);
	Joystick gamePad = new Joystick(2);
	
	/*
	 * set up navX
	 */
	public static IMU navX;
	SerialPort port;
	
	/*
	 * set up driving
	 */
	public static SixMotorDrive drive = new SixMotorDrive(RobotMap.frontLeftMotor, 
								RobotMap.midLeftMotor, 
								RobotMap.rearLeftMotor, 
								RobotMap.frontRightMotor, 
								RobotMap.midRightMotor, 
								RobotMap.rearRightMotor);
	
	/*
	 * Set up and initialize encoders
	 */
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
	VictorSP conveyorMotor = new VictorSP(RobotMap.conveyorBeltMotor);
	VictorSP sideSwipeMotor = new VictorSP(RobotMap.sideSwipeMotor);
	VictorSP hopperMotor = new VictorSP(RobotMap.hopperMotor);
	
	/*
	 * set up and initialize intake
	 */
	VictorSP intakeMotor = new VictorSP(RobotMap.intakeMotor);
	
	/*
	 * set up climber
	 */
	VictorSP climberMotorA = new VictorSP(RobotMap.climberMotorA);
	VictorSP climberMotorB = new VictorSP(RobotMap.climberMotorB);
	
	/*
	 * Set up Gear pickup
	 */
//	VictorSP gearPivotMotor = new VictorSP(RobotMap.gearPivotMotor);
//	VictorSP gearIntakeMotor = new VictorSP(RobotMap.gearIntakeMotor);
//	DigitalInput gearTopLimit = new DigitalInput(RobotMap.topLimit);
//	DigitalInput gearBottomLimit = new DigitalInput(RobotMap.bottomLimit);
	
	/*
	 * state of mechanisms
	 */
	boolean runShooter = false;
	boolean runIntake = false;
	boolean runConveyor = false;
	boolean runHopper = false;
	boolean runsideSwipeMotor = false; 
	boolean runhopperMotor = false; 
	boolean autonFirstRun = true;
	
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
	double distancePerPulse = (4*Math.PI)/200;
	
	/*
	 * set up Script Engine for JS interpreter
	 */
//	static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	String autonScript = "print('failed');";
	
	/*
	 * set up oldButton[] for gamepad
	 */
	boolean[] oldButton = new boolean[11];
	
	@Override
	public void robotInit() {
		leftDriveEncoder.setReverseDirection(true);
		SmartDashboard.putString("autonScript", "print('failed');");
		
		/*
		 * Set up masterTalonz
		 */
		masterTalon.reverseOutput(true);
		masterTalon.reverseSensor(false);
		masterTalon.enable();
		masterTalon.changeControlMode(TalonControlMode.Speed);
		masterTalon.set(0);
		masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		masterTalon.setP(0.1);
        masterTalon.setI(0);
        masterTalon.setD(0.0);
        masterTalon.setF(0.024);
        masterTalon.enableControl();
		
        /*
         * Set up slaveTalon
         */
		slaveTalon.changeControlMode(TalonControlMode.Follower);
        slaveTalon.set(0);
        slaveTalon.reverseOutput(true); 
        slaveTalon.enableControl();
		
        /*
         * Initialize NavX
         */
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		leftDriveEncoder.setDistancePerPulse(distancePerPulse);
		rightDriveEncoder.setDistancePerPulse(distancePerPulse);
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < 11; i++)oldButton[i] = false;
	}

	@Override
	public void autonomousInit() {
		autonScript = SmartDashboard.getString("autonScript", "print('failed');");
//		autonFirstRun = true;
	}

	int autoMode = 0;
	Drive autonDrive;
	Timer autoTimer = new Timer();
	
	@Override
	public void autonomousPeriodic() {
		switch(autoMode){
		case 0:
			autonDrive = new Drive(70, 0, 0.75);
			autonDrive.start();
			autoMode = 1;
			break;
		case 1:
			if(autonDrive.isFinished())autoMode = 2;
			autonDrive.update();
			break;
		case 2:
			autoTimer.start();
			drive.tankDrive(-0.4, -0.4);
			autoMode = 3;
			break;
		case 3:
			if(autoTimer.get() > 3){
				autoTimer.stop();
				autoTimer.reset();
				drive.tankDrive(0, 0);
				autoMode = 4;
			}
			break;
		case 4:
			autonDrive = new Drive(0, 65, 0.75);
			autonDrive.start();
			autoMode = 5;
			break;
		case 5:
			if(autonDrive.isFinished())autoMode = 6;
			autonDrive.update();
			break;
		case 6:
//			autonDrive = new Drive(35, 0, 0.3);
//			autonDrive.start();
//			autoMode = 7;
			break;
		case 7:
			if(autonDrive.isFinished())autoMode = 8;
			autonDrive.update();
			break;
		}
		System.out.println(autoMode);
		
	}
	
	public static void runAuto(){
//		try {
//			engine.eval("var test = Java.type('org.usfirst.frc.team696.robot.commands.TestRun()');");
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		} 
	}

	@Override
	public void teleopInit(){
	}
	
	@Override
	public void teleopPeriodic() {

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
			hopperMotor.set(0.5);
		} else {
			sideSwipeMotor.set(0);
			hopperMotor.set(0);
		}
		
		/*
		 * set shooter values
		 */
		if(runShooter){
			masterTalon.setSetpoint(100);
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

	@Override
	public void testPeriodic() {
	}
}


package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.utilities.DriveTrainOutput;
import org.usfirst.frc.team696.robot.utilities.NavXSource;
import org.usfirst.frc.team696.robot.utilities.Util;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	/*
	 * set up and initialize autonomous chooser
	 */
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
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
	 * set up and initialize drive straight
	 */
	NavXSource navXSource = new NavXSource();
	DriveTrainOutput drive = new DriveTrainOutput(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);
	PIDController directionPID = new PIDController(0.1, 0, 0, navXSource, drive, 0.01);
	
	/*
	 * Set up and initialize encoders
	 */
	Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	
	/*
	 * Set up and initialize CAN Talons for shooting
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
	 * state of mechanisms
	 */
	boolean runShooter = false;
	boolean runIntake = false;
	boolean runConveyor = false;
	boolean runHopper = false;
	boolean runsideSwipeMotor = false; 
	boolean runhopperMotor = false; 
	
	/*
	 * driving variables
	 */
	double speed = 0;
	double turn = 0;
	double speedTurnScale = 0;
	double leftValue = 0;
	double rightValue = 0;
	boolean firstZero = false;
	double directionSetPoint = 0;
	public static boolean driveStraightTempEnabled = true;

	/*
	 * set up oldButton[] for gamepad
	 */
	boolean[] oldButton = new boolean[11];
	
	@Override
	public void robotInit() {
		/*
		 * put chooser onto SmartDashboard
		 */
		chooser = new SendableChooser<>();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		/*
		 * Set up masterTalon
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
		
		/*
		 * Initialize oldButton[]
		 */
		for(int i = 0; i < 11; i++)oldButton[i] = false;
	}

	@Override
	public void autonomousInit() {
		/*
		 * Get which auto was selected
		 */
		autoSelected = chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			//custom code
			break;
		case defaultAuto:
			autoSelected = "customAuto";
		default:
			break;
		}
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
		
    	/*
    	 * enable and disable drive straight for when turning and not turning
    	 */
    	if(turn == 0){
    		if(firstZero){
    			directionSetPoint = Robot.navX.getYaw();
    			firstZero = false;
    			driveStraightTempEnabled = true;
    		}
    	} else {
    		firstZero = true;
    		driveStraightTempEnabled = false;
    	}
    	
    	/*
    	 * set drive straight directions
    	 */
    	navXSource.setSetPoint(directionSetPoint);
    	directionPID.setSetpoint(directionSetPoint);

    	/*
    	 * set speed and turn values to drive
    	 */
    	drive.setSpeed(speed);
    	drive.setTurn(turn);
   
    	/*
    	 * get oldButtons from gamepad
    	 */
		for(int i = 1; i < 11; i++)oldButton[i] = gamePad.getRawButton(i);
	}

	@Override
	public void testPeriodic() {
	}
}


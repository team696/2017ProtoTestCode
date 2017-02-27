package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.utilities.DriveCmd;
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
	
	Joystick arduino = new Joystick(0);
	Joystick wheel = new Joystick(1);
	Joystick gamePad = new Joystick(2);
	
	public static IMU navX;
	SerialPort port;
	
	Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	
	CANTalon masterTalon = new CANTalon(RobotMap.masterShooterTalon);
	CANTalon slaveTalon = new CANTalon(RobotMap.slaveShooterTalon);
	
	VictorSP intakeMotor = new VictorSP(RobotMap.intakeMotor);
	VictorSP climberMotorA = new VictorSP(RobotMap.climberMotorA);
	VictorSP climberMotorB = new VictorSP(RobotMap.climberMotorB);
	VictorSP sideSwipeMotor = new VictorSP(RobotMap.sideSwipeMotor);
	VictorSP hopperMotor = new VictorSP(RobotMap.hopperMotor);
	VictorSP conveyorMotor = new VictorSP(RobotMap.conveyorBeltMotor);
	
	DriveCmd drive = new DriveCmd(RobotMap.frontLeftMotor, RobotMap.midLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.midRightMotor, RobotMap.rearRightMotor);

	boolean runShooter = false;
	boolean runIntake = false;
	boolean runConveyor = false;
	boolean runHopper = false;
	
	double speed = 0;
	double turn = 0;
	double speedTurnScale = 0;
	double leftValue = 0;
	double rightValue = 0;
	
	boolean[] oldButton = new boolean[11];
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		masterTalon.reverseOutput(false);
		masterTalon.reverseSensor(true);
		masterTalon.enable();
		masterTalon.changeControlMode(TalonControlMode.Speed);

		masterTalon.set(0);
        
		masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        slaveTalon.changeControlMode(TalonControlMode.Follower);
        slaveTalon.set(0);
        slaveTalon.reverseOutput(true);

        masterTalon.setP(0.33);
        masterTalon.setI(0);
        masterTalon.setD(0.07);
        masterTalon.setF(0.024);
        
        masterTalon.enableControl();
        slaveTalon.enableControl();
		
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		for(int i = 0; i < 11; i++)oldButton[i] = false;
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

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if(gamePad.getRawButton(1) && !oldButton[1])runIntake = !runIntake;
		if(gamePad.getRawButton(6) && !oldButton[6]){
			runConveyor = !runConveyor;
			runHopper = !runHopper;
		}
		if(gamePad.getRawButton(5) && !oldButton[5])runShooter = !runShooter;
		
		if(runIntake)intakeMotor.set(0.7);
		else intakeMotor.set(0);
		if(runConveyor)conveyorMotor.set(0.8);
		else conveyorMotor.set(0);
		if(runHopper){
			sideSwipeMotor.set(-0.5);
			hopperMotor.set(0.5);
		} else {
			sideSwipeMotor.set(0);
			hopperMotor.set(0);
		}
		if(runShooter){
			masterTalon.setSetpoint(2500);
			masterTalon.enableControl();
			slaveTalon.enableControl();
		} else {
			masterTalon.setSetpoint(0);
			masterTalon.disableControl();
			slaveTalon.disableControl();
		}
		if(gamePad.getRawButton(8)){
			climberMotorA.set(-1);
			climberMotorB.set(-1);
		} else {
			climberMotorA.set(0);
			climberMotorB.set(0);
		}
		
		slaveTalon.set(masterTalon.getDeviceID());
		
		speed = -arduino.getRawAxis(4);
    	turn = wheel.getRawAxis(0);
    	speed = Util.smoothDeadZone(speed, -0.01, 0.1, -1, 1, 0);
    	speedTurnScale = 1/(Math.abs(speed)*2 + 1);
    	turn = Util.smoothDeadZone(turn, -0.2, 0.2, -1, 1, 0) * Math.abs((speedTurnScale));
		
    	leftValue = speed + turn;
    	rightValue = speed - turn;
    	
    	drive.tankDrive(leftValue, rightValue);
    	
		for(int i = 1; i < 11; i++)oldButton[i] = gamePad.getRawButton(i);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


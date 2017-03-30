package org.usfirst.frc.team696.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int frontLeftMotor = 9;
//	public static int midLeftMotor = 8;
//	public static int rearLeftMotor = 7;
	public static int rearLeftMotor = 10;
	public static int frontRightMotor = 2;
//	public static int midRightMotor = 3;
	public static int rearRightMotor = 4;
	
	public static int intakeMotor = 0;
//	public static int rollerMotor = 10;
//	public static int sideSwipeMotor = 12;
	public static int agitatorMotor = 11;
	public static int sideSwipeMotor = 7;
	
	public static int leftDriveEncoderA = 2;
	public static int leftDriveEncoderB = 3;
	public static int rightDriveEncoderA = 0;
	public static int rightDriveEncoderB = 1;
	
	public static int conveyorMotor = 1;
	
	public static int masterShooterTalon = 2;
	public static int slaveShooterTalon = 1;
	
	public static int climberMotorA = 5;
	public static int climberMotorB = 6;
	
	public static int hoodServo = 13; 
	
	public static int leftFlapServo = 14;
	public static int rightFlapServo = 15;
	
	public static int leftReleaseServo = 16;
	public static int rightReleaseServo = 17;
	
	public static int greenLED = 4;
	public static int RedLED = 5;
	public static int visionLED = 8;
	public static int botBeamBreak = 6;
	public static int topBeamBreak = 7;
}

package org.usfirst.frc.team696.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int frontLeftMotor = 9;
	public static int midLeftMotor = 8;
	public static int rearLeftMotor = 7;
	public static int frontRightMotor = 2;
	public static int midRightMotor = 3;
	public static int rearRightMotor = 4;
	
	public static int intakeMotor = 0;
	
	public static int leftDriveEncoderA = 2;
	public static int leftDriveEncoderB = 3;
	public static int rightDriveEncoderA = 0;
	public static int rightDriveEncoderB = 0;
}

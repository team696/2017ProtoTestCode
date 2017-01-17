package org.usfirst.frc.team696.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/*
	 * Intake Ports Needed
	 */
	public static int intakeMotorOne = 2;
	public static int intakeMotorTwo = 3;
	public static int intakeMotorOnePDP = 0;
	public static int intakeMotorTwoPDP = 0;
	
	/*
	 * Shooter Converyor Ports Needed
	 */
	public static int shooterConveyorMotor = 4;
	public static int shooterConveyorMotorPDP = 0;
	
	/*
	 * Shooter Ports Needed
	 */
	public static int shooterMotorOne = 0;
	public static int shooterMotorTwo = 1;
	public static int shooterEncoderOneA = 0;
	public static int shooterEncoderOneB = 0;
	public static int shooterMotorOnePDP = 0;
	public static int shooterMotorTwoPDP = 0;

	/*
	 * Climber Ports Needed
	 */
	public static int climberMotorOne = 5;
	public static int climberMotorTwo = 6;
	public static int climberMotorOnePDP = 0;
	public static int climberMotorTwoPDP = 0;
	
}

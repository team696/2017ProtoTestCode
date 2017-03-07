package org.usfirst.frc.team696.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
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

	Joystick gamePad = new Joystick(2);
    Victor conveyor = new Victor(1);
    Victor hopper = new Victor(10);
    Victor sideSwipe = new Victor(12);
    public static CANTalon masterTalon = new CANTalon(2);
    public static CANTalon slaveTalon = new CANTalon(1);
    
    double speed = 0;
    
    /*
     * READ!!!!
     * 2 masses 2 motors
     * works ok
     */
    public static double targetRPM = 2700;
    public static double minThreshold = 2700;
    public static double outputValue = 0;
    public static double powerForFlywheel = 0.65;
	
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
        masterTalon.changeControlMode(TalonControlMode.PercentVbus);

        masterTalon.set(0);
        
    	masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        slaveTalon.changeControlMode(TalonControlMode.PercentVbus);
        slaveTalon.set(0);
        slaveTalon.reverseOutput(true);
        
        SmartDashboard.putNumber("minThreshold", minThreshold);
        SmartDashboard.putNumber("currentRPM", 0);
        SmartDashboard.putNumber("targetRPM", 2700);
        SmartDashboard.putNumber("masterTalon current", 0);
        SmartDashboard.putNumber("slaveTalon current", 0);
        SmartDashboard.putNumber("powerForFlywheel", 0);
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
	public static double velocity = -masterTalon.getEncVelocity()/1024*60;

	
	public void teleopInit(){
		new BangBang().start();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
//		velocity = -masterTalon.getEncVelocity()*0.1464829974811083;
//		minThreshold = SmartDashboard.getNumber("minThreshold", minThreshold);
//        SmartDashboard.putNumber("currentRPM", velocity);
//        targetRPM = SmartDashboard.getNumber("targetRPM", 2700);
//        powerForFlywheel = SmartDashboard.getNumber("powerForFlywheel", 0);
//        
//        if(velocity > targetRPM)outputValue = 0;
//        if(velocity < minThreshold)outputValue = powerForFlywheel;
//        
//        SmartDashboard.putNumber("masterTalon current", masterTalon.getOutputCurrent());
//        SmartDashboard.putNumber("slaveTalon current", slaveTalon.getOutputCurrent());
//        
//        masterTalon.set(outputValue);
//        slaveTalon.set(-outputValue);
        
        if(gamePad.getRawButton(1)){
	    	conveyor.set(0.8);
	    	hopper.set(0.6);
	    	sideSwipe.set(-0.6);
    	} else {
    		conveyor.set(0);
    		hopper.set(0);
    		sideSwipe.set(0);
    	}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


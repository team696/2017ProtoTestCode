package org.usfirst.frc.team696.robot;


import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANExceptionFactory;
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
//    SendableChooser chooser;
    
    Joystick xbox = new Joystick(3);
    Victor vic = new Victor(1);
    Victor hopper = new Victor(10);
    Victor sideSwipe = new Victor(12);
    CANTalon talon = new CANTalon(2);
    CANTalon talon1 = new CANTalon(1);
    
    double speed = 0;
    boolean[] oldButton = new boolean[11];
    
    double p; 
    double i;
    double d; 
    double f;
    
    	
    
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", defaultAuto);`
//        chooser.addObject("My Auto", customAuto);
//        SmartDashboard.putData("Auto choices", chooser);
        
        talon.reverseOutput(true);
        talon.reverseSensor(true);
        talon.enable();
        talon.changeControlMode(TalonControlMode.Speed);

        talon.set(0);
        
    	talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        talon1.changeControlMode(TalonControlMode.Follower);
        talon1.set(0);
        talon1.reverseOutput(true);
        
        for(int i = 0; i <= 10; i++)oldButton[i] = false;
        
        vic.setInverted(true);
        

        
        SmartDashboard.putNumber("p", 0.1);
        SmartDashboard.putNumber("i", 0);
    	SmartDashboard.putNumber("d", 0.0);
        SmartDashboard.putNumber("f", 0.02);
        SmartDashboard.putNumber("ramp rate", 0);
        SmartDashboard.putNumber("currentRPM", 0);
        SmartDashboard.putNumber("targetRPM", 2700);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
   // 	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically dur
     * ing autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
//    Timer timer = new Timer();
//    boolean firstRun = true;
    
//    public double rampUp(double targetTime, double targetSpeed){
//    	if(firstRun){
//    		timer.start();
//    		firstRun = false;
//    	}
//    	double output = (timer.get() * (1/targetTime)) * targetSpeed;
//    	if(output > targetSpeed)output = targetSpeed;
//    	if(targetSpeed == 0){
//    		timer.stop();
//    		timer.reset();
//    		firstRun = true;
//    	}
//    	return 0;
//    }
    
    public void teleopInit(){
    }
//    
    public void teleopPeriodic() {
    	
    	talon.setSetpoint(SmartDashboard.getNumber("targetRPM", 2700));
    	talon1.set(talon.getDeviceID());
    	
    	//talon.get();
    	
    	talon.setAllowableClosedLoopErr(10);
    	SmartDashboard.putNumber("currentRPM", talon.get());
    	talon.setP(SmartDashboard.getNumber("p", 0.1));
    	talon.setI(SmartDashboard.getNumber("i", 0));
    	talon.setD(SmartDashboard.getNumber("d", 0.0));
    	talon.setF(SmartDashboard.getNumber("f", 0.02)); 
    	
    	talon.enableControl();
    	talon1.enableControl();
    	
    	if(xbox.getRawButton(1)){
	    	vic.set(-0.8);
	    	hopper.set(0.6);
	    	sideSwipe.set(-0.6);
    	} else {
    		vic.set(0);
    		hopper.set(0);
    		sideSwipe.set(0);
    	}
    	System.out.println(talon.getPulseWidthVelocity() + "     " + talon.get());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	System.out.println(talon.getEncPosition());
    
    }
    
}


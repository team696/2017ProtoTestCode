package org.usfirst.frc.team696.robot;


import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.TalonSRX;
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
    SendableChooser chooser;
    
    Joystick joy = new Joystick(0);
    Victor vic = new Victor(4);
    CANTalon talon = new CANTalon(1);
    CANTalon talon2 = new CANTalon(2);
    
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
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        SmartDashboard.putNumber("targetRPM", 0);
        
        talon.reverseSensor(false);
        talon.reverseOutput(true);
        talon.enable();
        talon.changeControlMode(TalonControlMode.Speed);
        talon.set(0);
        
    	talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        talon.reset();
        
        talon2.reverseSensor(true);
        talon2.reverseOutput(false);
        talon2.enable();
        talon2.changeControlMode(TalonControlMode.Speed);
        talon2.set(0);
        
    	talon2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        talon2.reset();
        
        
        for(int i = 0; i <= 10; i++)oldButton[i] = false;
        
        vic.setInverted(true);
        
        SmartDashboard.putNumber("f", 0);
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
    	autoSelected = (String) chooser.getSelected();
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
    
    public void teleopPeriodic() {
    	
    	talon.setSetpoint(SmartDashboard.getNumber("targetRPM"));
    	talon.setAllowableClosedLoopErr(10);
//    	talon.ClearIaccum(); 
    	SmartDashboard.putNumber("currentRPM", talon.get()); 
    	
    	talon.setP(SmartDashboard.getNumber("p"));
    	talon.setI(SmartDashboard.getNumber("i"));
    	talon.setD(SmartDashboard.getNumber("d"));
    	talon.setF(SmartDashboard.getNumber("f")); 
    	
    	talon2.setP(SmartDashboard.getNumber("p"));
    	talon2.setI(SmartDashboard.getNumber("i"));
    	talon2.setD(SmartDashboard.getNumber("d"));
    	talon2.setF(SmartDashboard.getNumber("f")); 
    	
    	
    	SmartDashboard.putNumber("output voltage", talon.getOutputVoltage());
    	SmartDashboard.putNumber("output current", talon.getOutputCurrent());
    	SmartDashboard.putNumber("output voltage", talon2.getOutputVoltage());
    	SmartDashboard.putNumber("output current", talon2.getOutputCurrent());
    	
    	if(!oldButton[1] && joy.getRawButton(1))speed+=0.1;
    	else if(!oldButton[2] && joy.getRawButton(2))speed-=0.1;
    	else if(!oldButton[6] && joy.getRawButton(6))speed = 0;
    	
    	if(!oldButton[3] && joy.getRawButton(3))speed-=0.8;
    	else if(!oldButton[4] && joy.getRawButton(4))speed+=0.8;
    	
    	for(int i = 1; i <= 10; i++)oldButton[i] = joy.getRawButton(i);
    	
    	
    	
    	System.out.println("speed: " + speed);
    	talon.enableControl();
    	talon2.enableControl(); 
    	vic.set(speed);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}


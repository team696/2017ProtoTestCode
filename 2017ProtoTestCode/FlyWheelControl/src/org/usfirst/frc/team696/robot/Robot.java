
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
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
    SendableChooser chooser;
    
    Joystick xbox = new Joystick(0);
    boolean[] oldButton = new boolean[11];
    
    double targetRPM;
    double p;
    double i;
    double d;
    
    Encoder enc = new Encoder(0,1);
    Victor vic = new Victor(1);
    PIDController PID = new PIDController(p, i, d, 0.7, enc, vic);
    
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	enc.setDistancePerPulse(1/64);
    	enc.setPIDSourceType(PIDSourceType.kRate);
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        targetRPM = 0;
        p = 0;
        i = 0;
        d = 0;
        
        for(int i = 0; i <= 10; i++)oldButton[i] = false;
        
        vic.setInverted(false);
        PID.enable();
        SmartDashboard.putNumber("Target RPM", targetRPM);
        SmartDashboard.putNumber("P", p);
        SmartDashboard.putNumber("I", i);
        SmartDashboard.putNumber("D", d);
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
     * This function is called periodically during autonomous
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
    	if(xbox.getRawButton(7) && !oldButton[7])PID.enable();
    	if(xbox.getRawButton(8) && !oldButton[8])PID.disable();
    	
    	PID.setAbsoluteTolerance(50);
    	
    	if(xbox.getRawButton(1) && !oldButton[1])targetRPM-=100;
    	if(xbox.getRawButton(2) && !oldButton[2])targetRPM+=100;
    	if(xbox.getRawButton(6) && !oldButton[6])targetRPM = 0;
    	PID.setSetpoint(targetRPM/60);
    	
        PID.setOutputRange(-1, 1);
        
        System.out.println("target: " + targetRPM + "   current: " + enc.getRate() + p + i + d);
        
        for(int i = 1; i<=10; i++)oldButton[i] = xbox.getRawButton(i);
        
        targetRPM = SmartDashboard.getNumber("Target RPM");
        SmartDashboard.putNumber("Current RPM", enc.getRate());
        p = SmartDashboard.getNumber("P");
        i = SmartDashboard.getNumber("I");
        d = SmartDashboard.getNumber("D");
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

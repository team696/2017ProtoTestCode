
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SampleRobot;
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
    
    CANTalon talon = new CANTalon(0);
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
        
        talon.reverseSensor(true);
        talon.reverseOutput(true);
        
        talon.enable();
        talon.changeControlMode(TalonControlMode.Speed);
        talon.set(0);
        
    	talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        talon.reset();
        
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
    	talon.setSetpoint(SmartDashboard.getNumber("targetRPM"));
    	talon.setAllowableClosedLoopErr(2);
//    	talon.ClearIaccum();
    	SmartDashboard.putNumber("currentRPM", talon.get());
    	talon.setP(SmartDashboard.getNumber("p"));
    	talon.setI(SmartDashboard.getNumber("i"));
    	talon.setD(SmartDashboard.getNumber("d"));
    	talon.setF(SmartDashboard.getNumber("f")); 
    	SmartDashboard.putNumber("output voltage", talon.getOutputVoltage());
    	SmartDashboard.putNumber("output current", talon.getOutputCurrent());
    	
    	talon.enableControl();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

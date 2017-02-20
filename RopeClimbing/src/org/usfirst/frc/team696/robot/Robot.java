
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
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
    SendableChooser chooser;
    
    Victor victorOne;
    Victor victorTwo;
    Joystick xbox;
    
    RobotDrive driveA = new RobotDrive(9,7,2,4);
    RobotDrive driveB = new RobotDrive(8,3);
    
    double speed,
    		goodSpeed;
    boolean[] oldButton;
    
    double leftDrive;
    double rightDrive;
    double stick;
    double wheel;
   
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        speed = 0;
        goodSpeed = 1;
        
        victorOne = new Victor(5);
        victorTwo = new Victor(6);
        xbox = new Joystick(3);
        oldButton = new boolean[11];
        
        for(int i = 1; i <= 10; i++)oldButton[i] = false;
        
        victorOne.setInverted(true);
        victorTwo.setInverted(true);
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
    	stick = xbox.getRawAxis(1);
    	wheel = xbox.getRawAxis(4);
    	leftDrive = stick + wheel;
    	rightDrive = stick - wheel;
    	
    	
        if(xbox.getRawButton(1) && !oldButton[1])speed-=0.05;
        if(xbox.getRawButton(2) && !oldButton[2])speed+=0.05;
        if(xbox.getRawButton(3) && !oldButton[3])speed-=1;
        if(xbox.getRawButton(4) && !oldButton[4])speed+=0.1;
    	if(xbox.getRawButton(6) && !oldButton[6])speed = 0;
    	if(xbox.getRawButton(5) && !oldButton[5])goodSpeed = speed;
    	if(xbox.getRawAxis(3) > 0.7)speed = goodSpeed;
        
        victorOne.set(speed);
        victorTwo.set(speed);
        
    	for(int i = 1; i <= 10; i++)oldButton[i] = xbox.getRawButton(i);
    	driveA.arcadeDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(4));
    	driveB.arcadeDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(4));
    	
    	System.out.println(speed);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

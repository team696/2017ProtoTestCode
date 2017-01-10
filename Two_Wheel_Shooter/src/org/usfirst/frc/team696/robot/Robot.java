
package org.usfirst.frc.team696.robot;

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
    SendableChooser chooser;
    
    Joystick afterGlow = new Joystick(3);
    Victor motorOne = new Victor(5);
    Victor motorTwo = new Victor(6);
    
    double speedOne = 0;
    double speedTwo = 0;
    boolean[] oldButton = new boolean[11];
    String mode = "sameSpeed";
    int modeNum = 0;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        for(int i = 0; i <= 10; i++)oldButton[i] = false;
        
        motorOne.setInverted(false);
        motorTwo.setInverted(true);
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
        switch(modeNum){
        case 0:
        	mode = "sameSpeed";
        	if(!oldButton[1] && afterGlow.getRawButton(1)){
        		speedOne-=0.05;
        		speedTwo = speedOne;
        	} 
        	if(!oldButton[2] && afterGlow.getRawButton(2)){
        		speedOne+=0.05;
        		speedTwo = speedOne;
        	}
        	break;
        case 1:
        	mode = "differentSpeed";
        	if(!oldButton[1] && afterGlow.getRawButton(1))speedTwo-=0.05;
        	if(!oldButton[2] && afterGlow.getRawButton(2))speedTwo+=0.05;
        	if(!oldButton[3] && afterGlow.getRawButton(3))speedOne-=0.05;
        	if(!oldButton[4] && afterGlow.getRawButton(4))speedOne+=0.05;
        	break;
        default:
    		modeNum = 0;
    		break;
        }
        if(!oldButton[5] && afterGlow.getRawButton(5))modeNum++;
        
        if(!oldButton[6] && afterGlow.getRawButton(6)){
        	speedOne = 0;
        	speedTwo = 0;
        }
        
        motorOne.set(speedOne);
        motorTwo.set(speedTwo);
        
        for(int i = 0; i <= 10; i++)oldButton[i] = afterGlow.getRawButton(i);
        
        System.out.println("mode: " + mode + "   speedOne: " + speedOne + "   speedTwo: " + speedTwo);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

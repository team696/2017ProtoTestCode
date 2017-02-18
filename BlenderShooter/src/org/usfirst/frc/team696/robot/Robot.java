package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	SendableChooser<String> chooser = new SendableChooser<>();
	 Joystick afterGlow = new Joystick(0);
	    VictorSP vic1 = new VictorSP(0); // belt
	    VictorSP vic2 = new VictorSP(1); // speed 2, shooter
	    VictorSP vic3 = new VictorSP(4); // blender
	    VictorSP vic4 = new VictorSP(3); // output
	   
	    double speed1 = 0;
	    double speed2 = 0; 
	    double speed3 = 0; 
	    double speed4 = 0; 
	    boolean[] oldButton = new boolean[11];
	    
	    PowerDistributionPanel current = new PowerDistributionPanel();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		for(int i = 1; i <= 10; i++)oldButton[i] = false;
		
		vic1.setInverted(false);
		vic2.setInverted(true);
		vic3.setInverted(false); 
	    vic4.setInverted(true);
	

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
	 for(int i = 0; i <= 10; i++)oldButton[i] = false;
       
	}
    
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

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if(!oldButton[1] && afterGlow.getRawButton(1))speed1+=0.1; 
    	if(!oldButton[2] && afterGlow.getRawButton(2))speed2+=0.1;
    	if(!oldButton[4] && afterGlow.getRawButton(4))speed3+=0.5;//blender
    	if(!oldButton[3] && afterGlow.getRawButton(3))speed4+=0.85;//output
    	if(!oldButton[6] && afterGlow.getRawButton(6)){
    		speed1=0;
    		speed2=0;
    	    speed3=0;
    	    speed4=0;
    	}
         	
         	vic1.set(speed1);
         	vic2.set(speed2);
       	    vic3.set(speed3);
        	vic4.set(speed4);
        	
        	
         	
    	for(int i = 1; i <= 10; i++)oldButton[i] = afterGlow.getRawButton(i);
    	
    	
    	
    	System.out.println("speed1: " + speed1 +  "  speed2:  "  + speed2 + "  speed3:  " + speed3 + "    speed4: " + speed4 + "           current: " + current.getCurrent(6) );
    	
    	 }
		
	

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


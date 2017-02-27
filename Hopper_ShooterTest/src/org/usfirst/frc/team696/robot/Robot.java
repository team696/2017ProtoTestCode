package org.usfirst.frc.team696.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
	/*
	 * joysticks r amazing ;) 
	 */
	Joystick hard = new Joystick(0); 
	
	
	 boolean[] oldButton = new boolean[11];
	 
	 /*
	  * motor controllers controlling ur emotions ;)
	  */
      VictorSP	vic1 = new VictorSP(12); // hopper
      VictorSP  vic3 = new VictorSP(10); // shaft 
      VictorSP vic2 = new VictorSP(1);  // belt 
      CANTalon tal1 = new CANTalon(1);  // shooter
  	  CANTalon tal2 = new CANTalon(2);  // shooter 
      
      /*
       * it has to have speed or else it's boring 
       */
        double speed1 = 0; // hopper 
        double speed2 = 0; 
        double speed3 = 0;
        double speed4 = 0; 
        double speed5 = 0; // shaft 
        
        
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		 for(int i = 0; i <= 10; i++)oldButton[i] = false;
	        
	        vic1.setInverted(false);  // hopper 
	        vic2.setInverted(false);  // belt 
	        vic3.setInverted(false);  // shaft 
	        
	        tal1.changeControlMode(TalonControlMode.PercentVbus);
	        tal2.changeControlMode(TalonControlMode.PercentVbus);
	   
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

	/**
	 * This function is called periodically during operator control
	 */
	 // boolean run = false;
	
	@Override
	public void teleopPeriodic() {
		
		/*
		 * Hopper and shaft  
		 */
		
		 if(!oldButton[1] && hard.getRawButton(1)){
	        	speed1+= 0.5; // belt 
	        	speed5+= 0.5;  // shaft 
	        }
    	
    	
    	
    	
    	if(!oldButton[6] && hard.getRawButton(6)){
    		speed1 = 0; 
    		speed2 = 0; 
    		speed3 = 0; 
    		speed4 = 0; 
    		speed5 = 0; 
    	}
    	
    	/*
    	 * belt 
    	 */
    	if(!oldButton[2] && hard.getRawButton(2))speed2+=0.7;
    	
    	
    	
    	/*
    	 * shooter 
    	 */
    	if(!oldButton[4] && hard.getRawButton(4)){
        	speed3+= 0.7; // shooter 
        	speed4+= 0.7;  // shooter
        }
	
    	
    	
    
    	 vic1.set(speed1);  // hopper
    	 vic2.set(speed2); // belt 
    	 vic3.set(speed5);  // shaft
    	 
    	 tal1.set(speed3); // shooter  
    	 tal2.set(speed4);   //shooter 
    	 
         
         for(int i = 0; i <= 10; i++)oldButton[i] = hard.getRawButton(i);
         
         
         System.out.println("speed1: " + speed1 +  "  speed2:  "  + speed2 + "  speed3:  " + speed3 + "    speed4: " + speed4  +   "    speed5:   " + speed5 );
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


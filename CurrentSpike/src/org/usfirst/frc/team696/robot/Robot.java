package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer.Interface;
import edu.wpi.first.wpilibj.Timer.StaticInterface;

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
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	double current;
	VictorSP vic = new VictorSP(2);
	VictorSP vic2 = new VictorSP(3); 
	boolean[] oldButton = new boolean[11];
	boolean back = false;
	Joystick joy = new Joystick(0);
	double speed;
	double speed2 = 0;  
	Timer timer = new Timer();
	int mode = 0;
	double targetOutput = 0;
	double targetTime = 0.5;
	double currentTime;
	double currentOutput;
	
//	double currentTime;
//	double targetTime = 0.5;
//	double currentOutput;
//	double targetOutput = 0;

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
		
		vic2.setInverted(true);
		
		
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
	
	double temp = 0;
	double currentTest = 13; 
	@Override
	public void teleopPeriodic() {
		if(!oldButton[1] && joy.getRawButton(1))targetOutput+=0.5;
		if(!oldButton[2] && joy.getRawButton(2))targetOutput=0.5;
		if(!oldButton[6] && joy.getRawButton(6)){
			targetOutput = 0;
			speed2 = 0;
		}
		
		if(!oldButton[3] && joy.getRawButton(3))speed2+=0.85;//output
		if(!oldButton[5] && joy.getRawButton(5))speed2=0; 
		
		current = pdp.getCurrent(12);
		
	switch(mode){
			case 0:
				speed = targetOutput;
				if(current > currentTest)mode = 1;
				break;
			case 1:
				timer.start();
				speed = -0.5;
				mode = 2;
				break;
			case 2:
				if(timer.get()>=0.5){
					timer.stop();
					timer.reset();
					mode = 3;
					} break; 
			case 3:
				timer.start();
				mode = 4;
				break;
			case 4:
				if(timer.get() < targetTime){
					speed = timer.get() * (1/targetTime) * targetOutput;
					mode = 5;
				}
				break;
			case 5:
				if(timer.get() >= targetTime){
					timer.stop();
					timer.reset();
					mode = 0;
				} break; 
				
			default:
				mode = 0;
				break;
				
		}
		
		System.out.println("mode: " + mode + "    " + current + "                 " + speed + "             " + back + "                " + timer.get());
		vic.set(speed);
		vic2.set(speed2);
		for(int i = 1; i <= 10; i++)oldButton[i] = joy.getRawButton(i);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


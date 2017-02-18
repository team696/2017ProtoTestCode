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
	VictorSP vic = new VictorSP(11);
	boolean[] oldButton = new boolean[11];
	boolean back = false;
	Joystick joy = new Joystick(0);
	double speed;
	Timer timer = new Timer();
	int mode = 0;

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
	double currentTest = 10;
	@Override
	public void teleopPeriodic() {
		if(!oldButton[1] && joy.getRawButton(1))temp+=0.1;
		if(!oldButton[2] && joy.getRawButton(2))temp-=0.1;
		if(joy.getRawButton(6))temp=0;
		
		current = pdp.getCurrent(9);
		
		
		//Idea 1
		
//		if(current>=4){
//			speed=-0.3;
//			Timer.delay(3);
//			speed=0.3;
//		}
		
		
		//Idea 2
		
//		if(current > 20){
//			back = true;
//			timer.start();
//		}
//		
//		if(back == true && timer.get() < 2){
//			speed = -0.5;
//		}else{
//			back = false;
//			speed = temp;
//			timer.reset();
//		}
//		
		
		
		switch(mode){
			case 0:
				speed = temp;
				if(current > currentTest)mode = 1;
				break;
			case 1:
				timer.start();
				speed = -0.05;
				mode = 2;
				break;
			case 2:
				speed = -0.1;
				Timer.delay(0.1);
				mode = 3;
				break;
			case 3:
				speed = -0.2;
				Timer.delay(0.1);
				mode = 4;
				break;
			case 4:
				speed = -0.3;
				Timer.delay(0.1);
				mode = 5;
				break;
			case 5:
				speed = -0.4;
				Timer.delay(0.1);
				mode = 6;
				break;
			case 6:
				speed = -0.5;
				Timer.delay(0.1);
				mode = 7;
				break;
			case 7:
				if(timer.get() >= 2){
					timer.stop();
					timer.reset();
					mode = 8;
					
				}
				break;
			case 8:
				timer.start();
				speed = 0.05;
				Timer.delay(0.1);
				mode = 9;
				break;
			case 9:
				speed = 0.1;
				Timer.delay(0.1);
				mode = 10;
				break;
			case 10:
				speed = 0.15;
				Timer.delay(0.1);
				mode = 11;
				break;
			case 11:
				speed = 0.2;
				Timer.delay(0.1);
				mode = 12;
				break;
			case 12:
				speed = 0.25;
				Timer.delay(0.1);
				mode = 13;
				break;
			case 13:
				speed = 0.3;
				Timer.delay(0.1);
				mode = 14;
				break;
			case 14:
				if(timer.get() >= 2){
					timer.stop();
					timer.reset();
					mode = 0;
				}
				break;
			default:
				mode = 0;
				break;
				
		}
		
		//Idea 3
		
//		if(current > 4){
//			speed = -0.5;
//			Timer.delay(1);
//		}
//		
//		else if(current > 2 && current < 3){
//			return;
//		}else{
//			speed = -0.5;
//			Timer.delay(1);
//		}
		
		
		
		System.out.println("mode: " + mode + "    " + current + "                 " + speed + "             " + back + "                " + timer.get());
		vic.set(speed);
		for(int i = 1; i <= 10; i++)oldButton[i] = joy.getRawButton(i);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


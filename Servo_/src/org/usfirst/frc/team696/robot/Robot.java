package org.usfirst.frc.team696.robot;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * @param <V>
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser = new SendableChooser();
	public static IMU navX;
	SerialPort port;
	
	Joystick joy = new Joystick(1);
    Servo ser1 = new Servo(16);
    Servo ser2 = new Servo(17);
    Servo ser3 = new Servo(18);
    Servo ser4 = new Servo(19);
    VictorSP intake = new VictorSP(0);
    double speed = 0;
    double target1 = 0;
    double target3 = 0;
    double target2 = 0;
    double target4 = 0;
    boolean[] oldButton = new boolean[11];
    public static Timer time = new Timer();
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		for(int i = 1; i <= 10; i++)oldButton[i] = false;
//		ser2.setAngle(50);
//		ser2.setAngle(ser2.getAngle());

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
		autoSelected = (String) chooser.getSelected();
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
	double goodTarget = 1;
	
	
	@Override
	public void teleopInit(){
		ser2.setAngle(1);
		ser4.setAngle(1);
		ser1.setAngle(179); // bottom left
		ser3.setAngle(1); // bottom right
		target1 = ser1.getAngle();
		target2 = ser2.getAngle();
		target3 = ser3.getAngle();
		target4 = ser4.getAngle();
	}
	
	@Override
	public void teleopPeriodic() {
		
//		if(!oldButton[1] && joy.getRawButton(1))target =1;
//    	if(!oldButton[2] && joy.getRawButton(2))target =180;
//    	if(target < 1)target = 1;
//    	if(target > 180)target = 180;
//    	if(joy.getRawButton(5))speed = 0.8;
//    	else speed = 0;
		
		// upper flap thingy thingy 
		if(!oldButton[1] && joy.getRawButton(1)){
			time.start();
//			target1 -= 90;
			target3 += 90;
		}
		
		if(!oldButton[2] && joy.getRawButton(2)){
			target1 += 90;
			target3 -= 90; //negative 
		}
		
		if(!oldButton[3] && joy.getRawButton(3)){
			time.start();
			target2 += 10;
			target4 += 10;
			time.stop();
		}
		
		if(time.get() >= 0.15){
			target1 -= 90;
			time.stop();
			time.reset();
		}
		// down flaps thingy thingy that goes wwooosh and open up 
		if(!oldButton[4] && joy.getRawButton(4)){
			target2 -= 10;
			target4 -=10;
		}
		
		
    	
    	ser1.setAngle(target1);
    	ser2.setAngle(target2);
    	ser3.setAngle(target3);
    	ser4.setAngle(target4);
    	
    	for(int i = 1; i <= 10; i++)oldButton[i] = joy.getRawButton(i);
    	System.out.println("servo 1: " + target1 + "    " + ser1.getAngle() +"   servo 2: " + target2 + "   " + ser2.getAngle() + "   servo 3: " + target3 + "   " + ser3.getAngle() + "   servo 4: " + target4 + ser4.getAngle());
        
    }
	

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


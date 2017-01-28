
package org.usfirst.frc.team696.robot;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import java.time.temporal.IsoFields;

import org.usfirst.frc.team696.PIDControl; 
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
    public static IMU navX;
    SerialPort port;
    
    Joystick joy = new Joystick(0);
    AnalogInput ultra = new AnalogInput(1);
    Solenoid shift = new Solenoid(4);
    double range; 
    double LeftDrive = 0; 
    double RightDrive = 0;
    double error = 0; 
    double direction = 0;
    double directionError = 0;
    double desiredDirection;
    double currentDirection; 
    double KP = 0.0175; 
    double KI = 0.0002; 
    double KD = 0.0003; 
    double alpha = 0.95; 
	PIDControl directionPID = new PIDControl(KP, KI, KD, alpha);
	
    boolean isFinished = false;
	double left = -0.5;
	double right = -0.5;
    double speed = 10;
    double speed1 = 0; 
    double wheel = 0; 
    double vcc = 5;
    double vm = ultra.getVoltage();
    double vi = vcc/512;
    double ri = vm/vi;
    RobotDrive drive = new RobotDrive(0,1,6,7); 
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		
		
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
		
		desiredDirection = navX.getYaw();
		

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
    		vm = ultra.getVoltage(); 
    		vi = vcc/512; 
    		ri = vm/vi; 
    		range = ri; 
    		
        	directionError = currentDirection - desiredDirection;
        	
        	if(directionError > 180)directionError = directionError - 360;
        	if(directionError < -180)directionError = directionError + 360;
        
        	
        	directionPID.setError(directionError);
        	
        	
        	System.out.println("Range: " + range + "         " + "Current Direction: " + currentDirection + "           Desired Direction: " + desiredDirection + "                 Error: " + directionError); 
        	
        	
        	
        	if (range >= 10) {
        		shift.set(true);
    			drive.tankDrive(left, right); } 
    		
        	if (range <= 20) {
    			isFinished = true;
//    			drive.tankDrive(1, 1);
    			drive.tankDrive(0, 0);
    			isFinished = true;
    			
    		if(isFinished = true){
    				break;
    			}
    		}
       	
            break; }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	speed1 = joy.getRawAxis(1); 
    	wheel = joy.getRawAxis(4); 
    	LeftDrive = (speed1 - wheel); 
    	RightDrive = (wheel + speed1); 
    	
        range = ri;
        vm = ultra.getVoltage();
        vi = vcc/512;
        ri = vm/vi;
//      ultra.getDistanceUnits(); 
      
      if(range <= 10){
     	 LeftDrive = 0;
     	 RightDrive = 0;
     	// Timer.delay(0.4);
     	 
      }
      
      if(joy.getRawButton(1))shift.set(true);
      if(joy.getRawButton(2))shift.set(false);
      
//      if(speed <= 10){
//     	 speed = 10;
//      }
//      
        
      
     System.out.println("Range: " + range + "      Current Direction: " + navX.getYaw()); 
     drive.tankDrive(LeftDrive, RightDrive);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

package org.usfirst.frc.team696.robot;


import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANExceptionFactory;
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
//    SendableChooser chooser;
    
    Joystick gamePad = new Joystick(2);
    Victor conveyor = new Victor(1);
    Victor hopper = new Victor(10);
    Victor sideSwipe = new Victor(12);
    CANTalon masterTalon = new CANTalon(2);
    CANTalon slaveTalon = new CANTalon(1);
    
    double speed = 0;
    boolean[] oldButton = new boolean[11];
    
    double p; 
    double i;
    double d; 
    double f;
    
    	
    
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        masterTalon.reverseOutput(false);
        masterTalon.reverseSensor(true);
        masterTalon.enable();
        masterTalon.changeControlMode(TalonControlMode.Speed);

        masterTalon.set(0);
        
    	masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        
        slaveTalon.changeControlMode(TalonControlMode.Follower);
        slaveTalon.set(0);
        slaveTalon.reverseOutput(true);
        
        for(int i = 0; i <= 10; i++)oldButton[i] = false;
        
        SmartDashboard.putNumber("p", 0.0);
        SmartDashboard.putNumber("i", 0);
    	SmartDashboard.putNumber("d", 0.0);
        SmartDashboard.putNumber("f", 0.0);
        SmartDashboard.putNumber("ramp rate", 0);
        SmartDashboard.putNumber("currentRPM", 0);
        SmartDashboard.putNumber("targetRPM", 2700);
        SmartDashboard.putNumber("masterTalon current", 0);
        SmartDashboard.putNumber("slaveTalon current", 0);
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
   // 	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically dur
     * ing autonomous
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
//    Timer timer = new Timer();
//    boolean firstRun = true;
    
//    public double rampUp(double targetTime, double targetSpeed){
//    	if(firstRun){
//    		timer.start();
//    		firstRun = false;
//    	}
//    	double output = (timer.get() * (1/targetTime)) * targetSpeed;
//    	if(output > targetSpeed)output = targetSpeed;
//    	if(targetSpeed == 0){
//    		timer.stop();
//    		timer.reset();
//    		firstRun = true;
//    	}
//    	return 0;
//    }
    
    public void teleopInit(){
    }

    public void teleopPeriodic() {
    	
    	masterTalon.setSetpoint(SmartDashboard.getNumber("targetRPM", 2700));
    	slaveTalon.set(masterTalon.getDeviceID());
    	
    	masterTalon.setAllowableClosedLoopErr(10);
    	SmartDashboard.putNumber("currentRPM", masterTalon.get());
    	masterTalon.setP(SmartDashboard.getNumber("p", 0.0));
    	masterTalon.setI(SmartDashboard.getNumber("i", 0));
    	masterTalon.setD(SmartDashboard.getNumber("d", 0.0));
    	masterTalon.setF(SmartDashboard.getNumber("f", 0.00)); 
    	
    	masterTalon.enableControl();
    	slaveTalon.enableControl();
    	
    	SmartDashboard.putNumber("masterTalon current", masterTalon.getOutputCurrent());
    	SmartDashboard.putNumber("slaveTalon current", slaveTalon.getOutputCurrent());
    	
    	if(gamePad.getRawButton(1)){
	    	conveyor.set(0.8);
	    	hopper.set(0.6);
	    	sideSwipe.set(-0.6);
    	} else {
    		conveyor.set(0);
    		hopper.set(0);
    		sideSwipe.set(0);
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
}


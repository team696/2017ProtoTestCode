
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team696.robot.subsystems.Chasis;
import org.usfirst.frc.team696.robot.subsystems.ClimberSystem;
import org.usfirst.frc.team696.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterConveyorSystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSystem;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	/*
	 * Input Setup
	 */
	public static OI oi;
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
	public static IMU navX;
	SerialPort port;

	/*
	 * Autonomous Chooser Setup
	 */
    Command autonomousCommand;
    SendableChooser chooser;

    /*
     * Setup Subsystems
     */
    Chasis chasis = new Chasis();
    ClimberSystem climberSystem = new ClimberSystem();
    IntakeSystem intakeSystem = new IntakeSystem();
    ShooterConveyorSystem shooterConveyorSystem = new ShooterConveyorSystem();
    ShooterSystem shooterSystem = new ShooterSystem();
    
    public void robotInit() {
    	/*
    	 * Initiate setup
    	 */
		oi = new OI();
        chooser = new SendableChooser();
        SmartDashboard.putData("Auto mode", chooser);
        
        /*
         * initiating NavX MXP
         */
        try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
//		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
//		switch(autoSelected) {
//		case "My Auto":
//			autonomousCommand = new MyAutoCommand();
//			break;
//		case "Default Auto":
//		default:
//			autonomousCommand = new ExampleCommand();
//			break;
//		} 
    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}

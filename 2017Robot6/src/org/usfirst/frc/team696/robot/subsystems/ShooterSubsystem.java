package org.usfirst.frc.team696.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team696.robot.Robot;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import javax.naming.ldap.Control;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	public static TalonSRX masterShooter;
	public static TalonSRX slaveShooter;
	Timer timer = new Timer();
	
	double kP = 0.11,  // was 0.075
			kI = 0.00005,
			kD = 5,
			kF = 0.032; // 0.03
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ShooterSubsystem(int masterShooterAddress, int slaveShooterAddress){
    	masterShooter = new TalonSRX(masterShooterAddress);
    	slaveShooter = new TalonSRX(slaveShooterAddress);

		masterShooter.setSensorPhase(true);
		masterShooter.setInverted(true);
		slaveShooter.setSensorPhase(true);

//    	masterShooter.changeControlMode(TalonControlMode.Speed);
		masterShooter.set(ControlMode.Velocity, 0);
//    	masterShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		masterShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 20);
//    	masterShooter.set(0);
    	
//    	slaveShooter.changeControlMode(TalonControlMode.Follower);
//    	slaveShooter.set(0);
        slaveShooter.set(ControlMode.Follower, masterShooter.getDeviceID());
    	
//    	masterShooter.reverseOutput(true);
//    	masterShooter.reverseSensor(false);
//    	slaveShooter.reverseOutput(true);

//    	masterShooter.enableControl();
//    	slaveShooter.enableControl();
    	
    	masterShooter.config_kP(0, kP, 20);
    	masterShooter.config_kI(0, kI, 20);
    	masterShooter.config_kD(0, kD, 20);
    	masterShooter.config_kF(0, kF, 20);
    }
    
    public void run(){
    	if(Robot.targetRPM == 0)disable();
    	else enable();
    	masterShooter.set(ControlMode.Velocity, Robot.targetRPM);
    	slaveShooter.set(ControlMode.Follower, masterShooter.getDeviceID());
        Robot.shooterAtSpeed = Math.abs(masterShooter.getSelectedSensorVelocity(0) - Robot.targetRPM) < 50 && Robot.targetRPM != 0;
    	
//    	if(Robot.targetRPM == 0){
//    		Robot.greenLEDSubsystem.set(false);
//    		Robot.oi.Psoc5.setOutput(8, false);
//    		Robot.oi.Psoc5.setOutput(7, false);
//    	}
//    	else if(Math.abs(Robot.targetRPM - masterShooter.getSelectedSensorVelocity(0)) < 50){
//    		Robot.greenLEDSubsystem.set(true);
//    		Robot.oi.Psoc5.setOutput(8, true);
//    		Robot.oi.Psoc5.setOutput(7, false);
//    	}
//    	else {
//    		if(timer.get() == 0)timer.start();
//    		if(timer.get() > (Robot.targetRPM/masterShooter.getSelectedSensorVelocity(0))/3325){
//    			Robot.greenLEDSubsystem.set(!Robot.greenLEDSubsystem.get());
//    			timer.stop();
//    			timer.reset();
//    		}
//    		Robot.oi.Psoc5.setOutput(8, false);
//    		Robot.oi.Psoc5.setOutput(7, true);
//    	}
    }
    
    public void enable(){
//    	masterShooter.enableControl();
//    	slaveShooter.enableControl();
		masterShooter.set(ControlMode.Velocity, Robot.targetRPM);
		slaveShooter.set(ControlMode.Follower, masterShooter.getDeviceID());
    }
    
    public void disable(){
//    	masterShooter.disableControl();
//    	slaveShooter.disableControl();
//		masterShooter.set(ControlMode.Disabled, 0);
//		slaveShooter.set(ControlMode.Disabled, 0);
		masterShooter.neutralOutput();
		slaveShooter.neutralOutput();
    }
}


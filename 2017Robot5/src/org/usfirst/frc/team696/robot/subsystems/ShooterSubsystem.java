package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	CANTalon masterShooter;
	CANTalon slaveShooter;
	Timer timer = new Timer();
	
	double kP = 0.077, 
			kI = 0, 
			kD = 0.0, 
			kF = 0.03;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ShooterSubsystem(int masterShooterAddress, int slaveShooterAddress){
    	masterShooter = new CANTalon(masterShooterAddress);
    	slaveShooter = new CANTalon(slaveShooterAddress);
    	
    	masterShooter.changeControlMode(TalonControlMode.Speed);
    	masterShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	masterShooter.set(0);
    	
    	slaveShooter.changeControlMode(TalonControlMode.Follower);
    	slaveShooter.set(0);
    	
    	masterShooter.reverseOutput(true);
    	masterShooter.reverseSensor(false);
    	slaveShooter.reverseOutput(true);
    	
    	masterShooter.enableControl();
    	slaveShooter.enableControl();
    	
    	masterShooter.setP(kP);
    	masterShooter.setI(kI);
    	masterShooter.setD(kD);
    	masterShooter.setF(kF);
    }
    
    public void run(){
    	if(Robot.targetRPM == 0)disable();
    	else enable();
    	masterShooter.setSetpoint(Robot.targetRPM);
    	slaveShooter.set(masterShooter.getDeviceID());
    	if(Math.abs(masterShooter.get() - Robot.targetRPM) < 50 && Robot.targetRPM != 0)Robot.shooterAtSpeed = true;
    	else Robot.shooterAtSpeed = false;
    	
    	if(Robot.targetRPM == 0){
    		Robot.greenLEDSubsystem.set(false);
    		Robot.oi.Psoc5.setOutput(8, false);
    		Robot.oi.Psoc5.setOutput(7, false);
    	}
    	else if(Math.abs(Robot.targetRPM - masterShooter.get()) < 50){
    		Robot.greenLEDSubsystem.set(true);
    		Robot.oi.Psoc5.setOutput(8, true);
    		Robot.oi.Psoc5.setOutput(7, false);
    	}
    	else {
    		if(timer.get() == 0)timer.start();
    		if(timer.get() > (Robot.targetRPM/masterShooter.get())/3325){
    			Robot.greenLEDSubsystem.set(!Robot.greenLEDSubsystem.get());
    			timer.stop();
    			timer.reset();
    		}
    		Robot.oi.Psoc5.setOutput(8, false);
    		Robot.oi.Psoc5.setOutput(7, true);
    	}
    	
    }
    
    public void enable(){
    	masterShooter.enableControl();
    	slaveShooter.enableControl();
    }
    
    public void disable(){
    	masterShooter.disableControl();
    	slaveShooter.disableControl();
    }
}


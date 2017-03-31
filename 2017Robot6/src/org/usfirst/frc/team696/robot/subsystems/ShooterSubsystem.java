package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	
	CANTalon masterShooterTalon;
	CANTalon slaveShooterTalon;
	Timer timer = new Timer();
	
	double kP = 0.077, 
			kI = 0, 
			kD = 0.0, 
			kF = 0.03;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ShooterSubsystem(int masterShootersTalon, int slaveShootersTalon){
		
		masterShooterTalon = new CANTalon(masterShootersTalon);
		slaveShooterTalon = new CANTalon(slaveShootersTalon);
		masterShooterTalon.changeControlMode(TalonControlMode.Speed);
    	masterShooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	masterShooterTalon.set(0);
    	
    	slaveShooterTalon.changeControlMode(TalonControlMode.Follower);
    	slaveShooterTalon.set(0);
    	
    	masterShooterTalon.reverseOutput(true);
    	masterShooterTalon.reverseSensor(false);
    	slaveShooterTalon.reverseOutput(true);
    	
    	masterShooterTalon.enableControl();
    	slaveShooterTalon.enableControl();
    	
    	masterShooterTalon.setP(kP);
    	masterShooterTalon.setI(kI);
    	masterShooterTalon.setD(kD);
    	masterShooterTalon.setF(kF);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void run(){
    	if(Robot.targetRPM == 0)disable();
    	else enable();
    	masterShooterTalon.setSetpoint(Robot.targetRPM);
    	slaveShooterTalon.set(masterShooterTalon.getDeviceID());
    	if(Math.abs(masterShooterTalon.get() - Robot.targetRPM) < 50 && Robot.targetRPM != 0)Robot.shooterAtSpeed = true;
    	else Robot.shooterAtSpeed = false;
    	
    	if(Robot.targetRPM == 0){
    		Robot.greenLEDSubsystem.set(false);
    		Robot.oi.Psoc5.setOutput(8, false);
    		Robot.oi.Psoc5.setOutput(7, false);
    	} 	
    	else if(Math.abs(Robot.targetRPM - masterShooterTalon.get()) < 50){
    		Robot.greenLEDSubsystem.set(true);
    		Robot.oi.Psoc5.setOutput(8, true);
    		Robot.oi.Psoc5.setOutput(7, false);
    	}
    	else {
    		if(timer.get() == 0)timer.start();
    		if(timer.get() > (Robot.targetRPM/masterShooterTalon.get())/3325){
    			Robot.greenLEDSubsystem.set(!Robot.greenLEDSubsystem.get());
    			timer.stop();
    			timer.reset();
    		}
    		Robot.oi.Psoc5.setOutput(8, false);
    		Robot.oi.Psoc5.setOutput(7, true);
    	}
    	
    	System.out.println("RPM: " + masterShooterTalon.get());
    	
    }
    
    public void enable(){
    	masterShooterTalon.enableControl();
    	slaveShooterTalon.enableControl();
    }
    
    public void disable(){
    	masterShooterTalon.disableControl();
    	slaveShooterTalon.disableControl();
    }
    
    public void get(){
    	
    	masterShooterTalon.get();
    	slaveShooterTalon.get();
    	
    }
}


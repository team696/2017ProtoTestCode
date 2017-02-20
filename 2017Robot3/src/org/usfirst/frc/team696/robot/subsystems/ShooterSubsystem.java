package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon masterShooter;
	CANTalon slaveShooter;
	
	double targetRPM = 0;
	
	double kP = 0.35, 
			kI = 0, 
			kD = 0.02, 
			kF = 0.027;
	
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
    	
    	masterShooter.reverseSensor(true);
    	slaveShooter.reverseOutput(true);
    	
    	masterShooter.enableControl();
    	slaveShooter.enableControl();
    }
    
    public void setTargetRPM(double targetRPM){
    	this.targetRPM = targetRPM;
    }
    
    public void enable(){
    	masterShooter.enableControl();
    	slaveShooter.enableControl();
    }
    
    public void disable(){
    	masterShooter.disable();
    	slaveShooter.disable();
    }
    
    
}


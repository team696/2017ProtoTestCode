package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
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
	
	double kP = 0.33, 
			kI = 0, 
			kD = 0.07, 
			kF = 0.024;
	
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


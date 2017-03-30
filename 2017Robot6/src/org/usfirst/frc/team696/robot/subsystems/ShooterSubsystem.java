package org.usfirst.frc.team696.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	
	CANTalon masterShooterTalon;
	CANTalon slaveShooterTalon;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ShooterSubsystem(int masterShooterTalon, int slaveShooterTalon){
		
		this.masterShooterTalon = new CANTalon(masterShooterTalon);
		this.slaveShooterTalon = new CANTalon(slaveShooterTalon);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed){
    	
    	masterShooterTalon.set(speed);
    	slaveShooterTalon.set(speed);
    	
    }
    
    public void get(){
    	
    	masterShooterTalon.get();
    	slaveShooterTalon.get();
    	
    }
}


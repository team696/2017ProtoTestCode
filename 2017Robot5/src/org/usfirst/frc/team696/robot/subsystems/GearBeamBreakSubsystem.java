package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearBeamBreakSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DigitalInput beamBreak;
	Timer timer = new Timer();
	
	public GearBeamBreakSubsystem(int beamBreak) {
		// TODO Auto-generated constructor stub
		this.beamBreak = new DigitalInput(beamBreak);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    public void run(){
//    	if(!beamBreak.get()){
//    		Robot.oi.Psoc5.setOutput(5, true);
//    		gearLED.set(true);
//    		Robot.closeGearFlap = false;
//    	}
//    	else {
//    		Robot.oi.Psoc5.setOutput(5, false);
//    		if(Robot.closeGearFlap){
//    			if(timer.get() == 0)timer.start();
//    			if(timer.get() > 0.1){
//    				gearLED.set(!gearLED.get());
//    				timer.stop();
//    				timer.reset();
//    			}
//    		} else gearLED.set(false);
//    	}
//    }
    
    public boolean get(){
    	return beamBreak.get();
    }
}


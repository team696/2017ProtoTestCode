package org.usfirst.frc.team696.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearBeamBreakSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static DigitalInput botBeamBreak;
	public static DigitalInput topBeamBreak;
	
	Timer timer = new Timer();
	
	public GearBeamBreakSubsystem(int topBeamBreak, int botBeamBreak) {
		GearBeamBreakSubsystem.botBeamBreak = new DigitalInput(botBeamBreak);
		GearBeamBreakSubsystem.topBeamBreak = new DigitalInput(topBeamBreak);
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
    
    public boolean getTop(){
//    	System.out.println(topBeamBreak.get());
    	return topBeamBreak.get();
    }
    
    public boolean getBot(){
    	return botBeamBreak.get();
    }
}


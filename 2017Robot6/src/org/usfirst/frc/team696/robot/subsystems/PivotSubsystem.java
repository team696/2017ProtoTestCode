package org.usfirst.frc.team696.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon pivot;
	VictorSP victor = new VictorSP(10);
	
	public PivotSubsystem(int pivot) {
		// TODO Auto-generated constructor stub
		this.pivot = new CANTalon(pivot);
		
		this.pivot.changeControlMode(TalonControlMode.Position);
		this.pivot.reverseOutput(true);
		this.pivot.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		this.pivot.set(0);
		
		this.pivot.setP(1);
		
		this.pivot.enableControl();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSetpoint(double setpoint){
    	pivot.setSetpoint(setpoint);
    }
    
    public void constrainOutput(int forwardVoltage, int reverseVoltage){
    	pivot.configPeakOutputVoltage(forwardVoltage, reverseVoltage);
    }
    
    public double getPosition(){
    	return pivot.get();
    }
    
    public void setPID(double p, double i, double d){
    	pivot.setPID(p, i, d);
    }
    
    public void setIntake(double speed){
    	victor.set(speed);
    }
}


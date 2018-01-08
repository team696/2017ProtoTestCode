package org.usfirst.frc.team696.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

	public static TalonSRX pivot;
	VictorSP intakeRoller;
	double kP = 0.5;
	int pidIdx = 1;
	
	public PivotSubsystem(int pivot, int intakeRoller) {
		// TODO Auto-generated constructor stub
		this.pivot = new TalonSRX(pivot);
		this.intakeRoller = new VictorSP(intakeRoller);
		
//		this.pivot.changeControlMode(TalonControlMode.Position);
		this.pivot.set(ControlMode.Position, 0);

//		this.pivot.reverseOutput(true);
//		this.pivot.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		this.pivot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, pidIdx, 500);
//		this.pivot.set(0);
		
		this.pivot.config_kP(1, kP, 500);
		
//		this.pivot.disableControl();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSetpoint(double setpoint){

//		pivot.setSetpoint(setpoint);
		pivot.set(ControlMode.Position, setpoint);
    }
    
    public void constrainOutput(int forwardVoltage, int reverseVoltage){
//    	pivot.configPeakOutputVoltage(forwardVoltage, reverseVoltage);
    }
    
    public double getPosition(){

//		return pivot.get();
		return pivot.getSelectedSensorPosition(pidIdx);
    }
    
    public void setPID(double p, double i, double d){

//		pivot.setPID(p, i, d);
		pivot.config_kP(0, p, 500);
		pivot.config_kI(0, i, 500);
		pivot.config_kD(0, d, 500);
    }
    
    public static void stopMotion() {
//    	pivot.setP(0);
		pivot.config_kP(0, 0, 500);
//    	pivot.disableControl();
		pivot.set(ControlMode.Disabled, 0);
    }
    
    public void setIntake(double speed){

		intakeRoller.set(speed);
    }
}


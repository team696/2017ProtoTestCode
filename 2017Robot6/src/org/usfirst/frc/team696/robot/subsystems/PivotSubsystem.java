package org.usfirst.frc.team696.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team696.robot.Robot;

/**
 *
 */
public class PivotSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static TalonSRX pivot;
	VictorSP intakeRoller;
	double kP = 0;
	int pidIdx = 0;
	
	public PivotSubsystem(int pivot, int intakeRoller) {
		// TODO Auto-generated constructor stub
		PivotSubsystem.pivot = new TalonSRX(pivot);
		this.intakeRoller = new VictorSP(intakeRoller);
		
//		this.pivot.changeControlMode(TalonControlMode.Position);
		PivotSubsystem.pivot.set(ControlMode.Disabled, Robot.gearPivotTarget);

//		this.pivot.reverseOutput(true);
//		this.pivot.configReverseSoftLimitEnable(false, 20);
//		this.pivot.configNominalOutputReverse(100, 20);
//		this.pivot.configPeakOutputReverse(100, 20);
//		this.pivot.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		this.pivot.setSensorPhase(true);
		PivotSubsystem.pivot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, pidIdx, 20);
		this.pivot.setInverted(true);
//		this.pivot.set(0);
		
		PivotSubsystem.pivot.config_kP(0, kP, 20);
		
//		this.pivot.disableControl();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSetpoint(double setpoint){

//		pivot.setSetpoint(setpoint);
//		pivot.set(ControlMode.Position, setpoint);
		this.pivot.set(ControlMode.Disabled, setpoint);
    }
    
    public void constrainOutput(int forwardVoltage, int reverseVoltage){
//    	pivot.configPeakOutputVoltage(forwardVoltage, reverseVoltage);
		pivot.configPeakOutputForward(forwardVoltage, 20);
		pivot.configPeakOutputReverse(reverseVoltage, 20);
    }
    
    public int getPosition(){

//		return pivot.get();
		return pivot.getSelectedSensorPosition(pidIdx);
    }
    
    public void setPID(double p, double i, double d){

//		pivot.setPID(p, i, d);
		pivot.config_kP(0, p, 20);
		pivot.config_kI(0, i, 20);
		pivot.config_kD(0, d, 20);
    }
    
    public static void stopMotion() {
//    	pivot.setP(0);
		pivot.config_kP(0, 0, 20);
//    	pivot.disableControl();
		pivot.set(ControlMode.Disabled, 0);
    }
    
    public void setIntake(double speed){

		intakeRoller.set(speed);
    }
}


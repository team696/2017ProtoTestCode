package org.usfirst.frc.team696.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	double kP = 0;
	double kI = 0;
	double kD = 0;
	double alpha = 0;
	
	FlywheelPID flywheelPID = new FlywheelPID(kP, kI, kD);

	Joystick gamePad = new Joystick(2);
    Victor conveyor = new Victor(1);
    Victor hopper = new Victor(10);
    Victor sideSwipe = new Victor(12);
	
	CANTalon masterTalon = new CANTalon(2);
	CANTalon slaveTalon = new CANTalon(1);
	
	double targetRPM = 2700;
	double currentRPM = 0;
	double speed = 0;
	double velocity = 0;
	
	
	@Override
	public void robotInit() {
		masterTalon.changeControlMode(TalonControlMode.PercentVbus);
		masterTalon.set(0);
		masterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		slaveTalon.changeControlMode(TalonControlMode.PercentVbus);
		slaveTalon.set(0);
		
		SmartDashboard.putNumber("targetRPM", 0);
		SmartDashboard.putNumber("currentRPM", 0);
		SmartDashboard.putNumber("masterTalon current", 0);
		SmartDashboard.putNumber("slaveTalon current", 0);
		SmartDashboard.putNumber("p", 0);
        SmartDashboard.putNumber("i", 0);
        SmartDashboard.putNumber("d", 0);
        SmartDashboard.putNumber("alpha", 0);
        SmartDashboard.putBoolean("run hopper system", false);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	
	public void teleopInit(){
		flywheelPID.start();
	}
	
	@Override
	public void teleopPeriodic() {
		velocity = -masterTalon.getEncVelocity()*0.1464829974811083;
		
		targetRPM = SmartDashboard.getNumber("targetRPM", 0);
		SmartDashboard.putNumber("currentRPM", velocity);
		SmartDashboard.putNumber("masterTalon current", masterTalon.getOutputCurrent());
		SmartDashboard.putNumber("slaveTalon current", slaveTalon.getOutputCurrent());
		SmartDashboard.putNumber("flywheel value", flywheelPID.getValue());
		
		kP = SmartDashboard.getNumber("p", 0);
		kI = SmartDashboard.getNumber("i", 0);
		kD = SmartDashboard.getNumber("d", 0);
		alpha = SmartDashboard.getNumber("alpha", 0);
		
		flywheelPID.setError(targetRPM - velocity);
		flywheelPID.setPID(kP, kI, kD);
		
		masterTalon.set(flywheelPID.getValue());
		slaveTalon.set(-flywheelPID.getValue());
		
		if(SmartDashboard.getBoolean("run hopper system", false)){
	    	conveyor.set(0.8);
	    	hopper.set(0.6);
	    	sideSwipe.set(-0.6);
    	} else {
    		conveyor.set(0);
    		hopper.set(0);
    		sideSwipe.set(0);
    	}
	}

	@Override
	public void testPeriodic() {
	}
}


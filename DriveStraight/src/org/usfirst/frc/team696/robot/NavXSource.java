package org.usfirst.frc.team696.robot;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NavXSource implements PIDSource {

	double targetAngle = 0,
			currentAngle = 0,
			error = 0;
	
	public static IMU navX;
	
	public NavXSource(IMU navX) {
		// TODO Auto-generated constructor stub
		this.navX = navX;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}
	
	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		currentAngle = navX.getYaw();
		error = currentAngle - targetAngle;
		if(error > 180)currentAngle = currentAngle - 360;
		if(error < -180)currentAngle = currentAngle + 360;
		return currentAngle;
	}

}

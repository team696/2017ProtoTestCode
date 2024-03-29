package org.usfirst.frc.team696.robot.utilities;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NavXSource implements PIDSource {

	double setPoint = 0,
			currentAngle = 0,
			error = 0;
	
	public IMU navX;
	
	public NavXSource(IMU navX) {
		// TODO Auto-generated constructor stub
		this.navX = navX;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}
	
	public void setSetPoint(double setPoint) {
		this.setPoint = setPoint;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		currentAngle = navX.getYaw();
		error = setPoint - currentAngle;
		if(error > 180)currentAngle = currentAngle + 360;
		if(error < -180)currentAngle = currentAngle - 360;
		return currentAngle;
	}

}

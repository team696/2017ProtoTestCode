//package org.usfirst.frc.team696.robot;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//public class Distance implements Runnable {
//	
//	I2C LIDAR = new I2C(Port.kOnboard, 0x62);
//	byte[] distance = new byte[2];
//	Timer updater = new Timer();
//	
//
//	public Distance(I2C LIDAR) {
//		// TODO Auto-generated constructor stub
//		this.LIDAR = LIDAR;
//	}
//
//	@Override
//	public void run() {
//			while(true) {
//					update();
//					if(getDistance() < 90 && getDistance() > 84){
//						SmartDashboard.putBoolean("Correct distance from human feeder", true);
//					}
//					else{
//						SmartDashboard.putBoolean("Correct distance from human feeder", false);
//					}
//					
//					if(getDistance() < 80 && getDistance() > 70){
//						SmartDashboard.putBoolean("Correct distance to stacks", true);
//					}
//					else{
//						SmartDashboard.putBoolean("Correct distance to stacks", false);
//					}
//					SmartDashboard.putNumber("LIDAR distance Inches", (getDistance() / 2.54));
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//	}
//	
//	
//
//}

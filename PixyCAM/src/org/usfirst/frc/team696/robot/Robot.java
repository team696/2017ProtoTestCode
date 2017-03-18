package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	int bufferLength = 40;
	I2C pixy;
	byte[] longBuffer = new byte[bufferLength];
	byte[] shortBuffer = new byte[1];
	int pos = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		pixy = new I2C(Port.kOnboard, 0x54);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	
	@Override
	public void teleopPeriodic() {
			if(pos == 4){
				pos = 0;
				pixy.readOnly(longBuffer, bufferLength);
				for(int i = 0; i < longBuffer.length; i++){
					System.out.print(longBuffer[i] + "   ");
				}
				System.out.println();
			} else {
				pixy.readOnly(shortBuffer, 1);
				
				if((shortBuffer[0] == 85 && pos%2 == 00 || (shortBuffer[0] == -86 && pos%2 == 1))){
					pos++;
				} else {
					pos = 0;
				}
			}
			
//		pixy.readOnly(longBuffer, bufferLength);
//		
//		if(longBuffer[0] == 85 && longBuffer[2] == 85 && longBuffer[1] == -86 && longBuffer[3] == -86){
//			for(int i = 0;i < longBuffer.length; i++){
//				System.out.print(longBuffer[i] + "      ");
//			}
//			System.out.println();
//		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


#include <iostream>
#include <memory>
#include <string>
#include <wpilib.h>
#include <IterativeRobot.h>
#include <LiveWindow/LiveWindow.h>
#include <SmartDashboard/SendableChooser.h>
#include <SmartDashboard/SmartDashboard.h>

class Robot: public frc::IterativeRobot {

public:

	 frc::Joystick stick;

     frc::VictorSP LeftTopDrive;
     frc::VictorSP LeftMiddleDrive;
    frc::VictorSP LeftRearDrive;

     frc::VictorSP RightTopDrive;
     frc::VictorSP RightMiddleDrive;
     frc::VictorSP RightRearDrive;

     frc::Timer time;

     frc:: RobotDrive myRobot;





/*
 * variables
 */
double speed = 0;
double wheel = 0;
double LeftDrive = 1;
double RightDrive = 1;
double LeftDriveZero = 0;
double RightDriveZero = 0;


  Robot() :
	stick(0),
	LeftTopDrive(9),
	LeftMiddleDrive(8),
	LeftRearDrive(7),
	RightTopDrive(2),
	RightMiddleDrive(3)
	//RightRearDrive(4)

{

}
//  frc:: RobotDrive drive = new RobotDrive(LeftTopDrive,LeftMiddleDrive,RightTopDrive,RightMiddleDrive);

	void RobotInit() {
//		chooser.AddDefault(autoNameDefault, autoNameDefault);
//		chooser.AddObject(autoNameCustom, autoNameCustom);
//		frc::SmartDashboard::PutData("Auto Modes", &chooser);


	}

	/*
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * GetString line to get the auto name from the text box below the Gyro.
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * if-else structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	void AutonomousInit() override {
	//	autoSelected = chooser.GetSelected();
		// std::string autoSelected = SmartDashboard::GetString("Auto Selector", autoNameDefault);
		std::cout << "Auto selected: " << autoSelected << std::endl;

		if (autoSelected == autoNameCustom) {
			// Custom Auto goes here
			time.Start();
		} else {
			// Default Auto goes here
		}
	}

	void AutonomousPeriodic() {
		if (autoSelected == autoNameCustom) {
			// Custom Auto goes here

			if(time.Get() < 9){
						    			myRobot.TankDrive(LeftDrive, RightDrive, false);
						    		}else{
						    			myRobot.TankDrive(LeftDriveZero, RightDriveZero, false);

						    		}

		//	myRobot.TankDrive(1, 1);






		} else {
			// Default Auto goes here
	}

	}
	void TeleopInit() {

	}

	void TeleopPeriodic() {



	}

	void TestPeriodic() {
		lw->Run();
	}

private:
	frc::LiveWindow* lw = LiveWindow::GetInstance();
//	frc::SendableChooser<std::string> chooser;
	const std::string autoNameDefault = "Default";
	const std::string autoNameCustom = "My Auto";
	std::string autoSelected;
//	int drive;
	int wait;
};

START_ROBOT_CLASS(Robot)

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
	void RobotInit() {
		chooser.AddDefault(autoNameDefault, autoNameDefault);
		chooser.AddObject(autoNameCustom, autoNameCustom);
		frc::SmartDashboard::PutData("Auto Modes", &chooser);
		  frc::Joystick stick { 0 };
				//Joystick stick = new Joystick(0);
		        frc::VictorSP LeftTopDrive {1};
		        frc::VictorSP LeftMiddleDrive {2};
		        frc::VictorSP LeftRearDrive { 3 };

		        frc::VictorSP RightTopDrive { 4 };
		        frc::VictorSP RightMiddleDrive { 5 };
		        frc::VictorSP RightRearDrive { 6 };

		        frc::Encoder enc {1,2};
		        frc::Encoder enc2 {3,4};


		        /*
		         * variables
		         */
		        double speed = 0;
		        double wheel = 0;
                double time = 0;
                double LeftDrive = 0;
                double RightDrive = 0;
                frc:: RobotDrive drive = new RobotDrive(0,1,2,3);


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
		autoSelected = chooser.GetSelected();
		// std::string autoSelected = SmartDashboard::GetString("Auto Selector", autoNameDefault);
		std::cout << "Auto selected: " << autoSelected << std::endl;

		if (autoSelected == autoNameCustom) {
			// Custom Auto goes here
		frc::Timer();
		} else {
			// Default Auto goes here
		}
	}

	void AutonomousPeriodic() {
		if (autoSelected == autoNameCustom) {
			// Custom Auto goes here

			if((-enc->Get() + enc2->Get()) /2 < 30){
			    			drive.tankDrive(0.5,0.5);
			    			}else{
			    			drive.tankDrive(0, 0);

			    		}

		} else {
			// Default Auto goes here
		}
	}

	void TeleopInit() {

	}

	void TeleopPeriodic() {

		drive->tankdrive(LeftDrive, RightDrive);

	}

	void TestPeriodic() {
		lw->Run();
	}

private:
	frc::LiveWindow* lw = LiveWindow::GetInstance();
	frc::SendableChooser<std::string> chooser;
	const std::string autoNameDefault = "Default";
	const std::string autoNameCustom = "My Auto";
	std::string autoSelected;
};

START_ROBOT_CLASS(Robot)

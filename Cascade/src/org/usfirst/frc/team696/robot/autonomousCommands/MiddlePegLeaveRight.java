package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddlePegLeaveRight extends CommandGroup {

    public MiddlePegLeaveRight() {
    	addSequential(new Drive(83, 0), 0.7);
//    	addSequential(new VoltageDrive(0.4, 2));
		addSequential(new WaitForGear(), 10);
		addSequential(new Drive(-50, 0), 0.4);
		addSequential(new Wait(0.5));
		addSequential(new Drive(0, 90), 1);
		addSequential(new Wait(0.5));
		addSequential(new Drive(80, 0), 1);
		addSequential(new Wait(0.5));
		addSequential(new Drive(0, -90), 1);
		addSequential(new Wait(0.5));
		addSequential(new Drive(200, 0), 4);
    }
}

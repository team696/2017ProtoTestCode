package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddlePegLeaveLeft extends CommandGroup {

    public MiddlePegLeaveLeft() {
    	addSequential(new Drive(83, 0), 0.7);
//    	addSequential(new VoltageDrive(0.4, 2));
		addSequential(new WaitForGear(), 5);
		addSequential(new Drive(-50, 0), 0.4);
		addSequential(new Wait(0.5));
		addSequential(new Drive(0, -90), 1);
		addSequential(new Wait(0.5));
		addSequential(new Drive(50, 0), 0.5);
		addSequential(new Wait(0.5));
		addSequential(new Drive(0, 90), 1);
		addSequential(new Wait(0.5));
		addSequential(new Drive(100, 0), 2);
    }
}

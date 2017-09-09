package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddlePeg extends CommandGroup {

    public MiddlePeg() {
    	addSequential(new Drive(83, 0), 0.5);
//    	addSequential(new VoltageDrive(0.5, 2.5));
		addSequential(new WaitForGear(), 10);
		addSequential(new Drive(-50, 0), 0.5);
		addSequential(new Drive(0, 90), 0.5);
    }
}

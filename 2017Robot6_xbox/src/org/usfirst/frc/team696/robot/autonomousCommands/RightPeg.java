package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPeg extends CommandGroup {

    public RightPeg() {
    	addSequential(new Drive(100, 0), 2);
		addSequential(new Drive(0, -60), 1);
		addSequential(new Wait(1.5));
		addSequential(new Drive(50, -60), 1);
		addSequential(new VoltageDrive(0.4, 15));
		addSequential(new WaitForGear(), 10);
    }
}

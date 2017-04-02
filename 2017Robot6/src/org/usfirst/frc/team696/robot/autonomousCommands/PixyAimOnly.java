package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.PIXYAim;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PixyAimOnly extends CommandGroup {

    public PixyAimOnly() {
        addSequential(new PIXYAim());
        addSequential(new Drive(0, 0));
        addSequential(new PIXYAim());
        addSequential(new Drive(0, 0));
    }
}

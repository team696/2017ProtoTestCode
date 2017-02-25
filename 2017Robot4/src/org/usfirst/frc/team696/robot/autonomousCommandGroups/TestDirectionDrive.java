package org.usfirst.frc.team696.robot.autonomousCommandGroups;

import org.usfirst.frc.team696.robot.autonomousCommands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestDirectionDrive extends CommandGroup {

    public TestDirectionDrive() {
        addSequential(new Drive(0, 0));
    }
}

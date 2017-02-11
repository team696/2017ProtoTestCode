package org.usfirst.frc.team696.robot.autonomous;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTest extends CommandGroup {

    public DriveTest() {
        addSequential(new Drive(0, 90));
    }
}

package org.usfirst.frc.team696.robot.autonomous;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.utilities.Util;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTest extends CommandGroup {

    public DriveTest() {
//        addSequential(new Drive(Util.toInches(8, 0)));
        addSequential(new Drive(Util.toInches(8, 0), 0, 0.5));
        addSequential(new Drive(0, 90));
        addSequential(new Drive(Util.toInches(4, 0)));
        addSequential(new Drive(-Util.toInches(4, 0)));
    }
}

package org.usfirst.frc.team696.robot.autonomous;

import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothing extends CommandGroup {

    public DoNothing() {
        addSequential(new Wait(15));
    }
}

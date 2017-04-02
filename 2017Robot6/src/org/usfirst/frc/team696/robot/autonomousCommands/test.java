package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.SetVisionLight;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class test extends CommandGroup {

    public test() {
        addSequential(new SetVisionLight(true));
        addSequential(new Wait(2));
        addSequential(new SetVisionLight(false));
    }
}

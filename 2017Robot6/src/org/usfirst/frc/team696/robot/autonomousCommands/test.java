package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.PIXYAim;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.commands.SetVisionLight;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class test extends CommandGroup {

    public test() {
        addSequential(new SetVisionLight(true));
        addSequential(new Wait(0.5));
        addSequential(new PIXYAim());
        addSequential(new SetVisionLight(false));
        addSequential(new Drive(0, 0));
        addSequential(new SetShooter(2900));
        addSequential(new Wait(0.1));
        addSequential(new SetConveyor(0.5));
        addSequential(new SetHopper(0.8));
    }
}

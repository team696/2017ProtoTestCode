package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.ZeroYaw;

public class CenterLeft extends CommandGroup {

    public CenterLeft() {

        addSequential(new ZeroYaw());
        addSequential(new Drive(40, 0), 0.5);
        addSequential(new Drive(100, -45), 1);
        addSequential(new Drive(30, 0));

    }
}

package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.GroundIntakeClose;
import org.usfirst.frc.team696.robot.commands.GroundIntakeOuttake;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPegActive extends CommandGroup {

    public LeftPegActive() {
    	addSequential(new GroundIntakeClose());
        addSequential(new Drive(-65, 0), 2.5);
        addSequential(new Wait(0.3));
        addSequential(new Drive(0, 49), 1);
        addSequential(new Drive(-64, 0));
        addSequential(new Wait(0.3));
        addSequential(new GroundIntakeOuttake());
        addSequential(new Wait(1.3));
        addSequential(new Drive(50, 0), 1.5);
        addSequential(new GroundIntakeClose());
    }
}

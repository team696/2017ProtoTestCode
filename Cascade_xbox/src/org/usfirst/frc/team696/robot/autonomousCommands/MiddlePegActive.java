package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.GroundIntakeClose;
import org.usfirst.frc.team696.robot.commands.GroundIntakeOuttake;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddlePegActive extends CommandGroup {

    public MiddlePegActive() {
    	addSequential(new GroundIntakeClose());
        addSequential(new Drive(-70, 0), 0.5);
        addSequential(new Wait(2));
        addParallel(new GroundIntakeOuttake());
        addSequential(new Wait(1.3));
        addSequential(new Drive(50, 0), 0.4);
        addSequential(new GroundIntakeClose());
    }
}

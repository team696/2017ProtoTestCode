package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class leftGearShoot extends CommandGroup {

    public leftGearShoot() {
    	addSequential(new Drive(40, 0));
		addSequential(new Drive(0, 90));
		addSequential(new Drive(40, 0));
		addSequential(new Drive(0, -90));
		addSequential(new Drive(13.3, 0));
		//vision tracking happens here
		addParallel(new SetShooter(3325));
		addSequential(new Wait(0.5));
		addSequential(new SetConveyor(0.5));
		addSequential(new Wait(0.1));
		addSequential(new SetHopper(0.5));
    }
}

package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddlePegLeftShoot extends CommandGroup {

    public MiddlePegLeftShoot() {
    	addSequential(new Drive(83, 0), 2);
    	addSequential(new VoltageDrive(0.4, 2));
		addSequential(new WaitForGear(), 10);
		addSequential(new Drive(-50, 0), 1);
		addSequential(new Drive(0, -95), 1);
		addSequential(new Drive(58, 0), 2.5);
        addSequential(new SetShooter(3325));
        addSequential(new SetConveyor(0.8));
        addSequential(new SetHopper(0.7));
    }
}

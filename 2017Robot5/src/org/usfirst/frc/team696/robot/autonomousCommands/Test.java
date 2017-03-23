package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.PIXYAim;
import org.usfirst.frc.team696.robot.commands.SetConveyor;
import org.usfirst.frc.team696.robot.commands.SetHopper;
import org.usfirst.frc.team696.robot.commands.SetShooter;
import org.usfirst.frc.team696.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Test extends CommandGroup {

    public Test() {
    	addSequential(new PIXYAim());
        addSequential(new Drive(0, 0), 1);
        addSequential(new PIXYAim());
        addSequential(new Drive(0, 0), 1);
        addSequential(new PIXYAim());
        addSequential(new Drive(0, 0), 1);
        addSequential(new PIXYAim());
        addSequential(new Drive(0, 0), 1);
        addSequential(new SetShooter(3325));
        addSequential(new Wait(0.2));
        addSequential(new SetConveyor(0.8));
        addSequential(new SetHopper(0.7));
    }
    
}

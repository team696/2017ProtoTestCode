package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Aim;
import org.usfirst.frc.team696.robot.commands.Drive;
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
        addSequential(new Aim());
        addSequential(new Drive(0, 0));
        addSequential(new Aim());
        addSequential(new Drive(0, 0));
        
    }
}

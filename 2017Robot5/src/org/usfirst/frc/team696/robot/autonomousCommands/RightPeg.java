package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightPeg extends CommandGroup {
	
	public RightPeg(){
		addSequential(new Drive(95, 0), 2);
		addSequential(new Drive(0, -58), 0.5);
		addSequential(new Wait(1));
		addSequential(new Drive(50, 0), 0.2);
		addSequential(new WaitForGear(), 5);
		addSequential(new Drive(-60, 0));
		addSequential(new Drive(0, 30));
	}

}

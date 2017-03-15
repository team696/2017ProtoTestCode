package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddlePeg extends CommandGroup {

	public MiddlePeg() {
		
		addSequential(new Drive(70, 0));
		addSequential(new Drive(0, -90));
		addSequential(new Drive(10, 0));
		addSequential(new Drive(0, 90));
		addSequential(new Drive(22.3, 0));
//		addSequential(new Drive(-10, 0));
		
	}
	
}

package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftPeg extends CommandGroup {
	
	public LeftPeg() {
		addSequential(new Drive(187.3, 0));
		addSequential(new Drive(0, 75));
		addSequential(new Drive(5, 0));
//		addSequential(new Drive(-10, 0));
	}
	

}

package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightPeg extends CommandGroup {
	
	public RightPeg(){
		addSequential(new Drive(187.3, 0));
		addSequential(new Drive(0, -75));
		addSequential(new Drive(5, 0));
//		addSequential(new Drive(-10, 0));
	}

}

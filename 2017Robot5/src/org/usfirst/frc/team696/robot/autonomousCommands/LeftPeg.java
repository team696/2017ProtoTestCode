package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.WaitForGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftPeg extends CommandGroup {
	
	public LeftPeg() {
		/*
		 * WORKED!!!!!******
		 */
		addSequential(new Drive(95, 0), 2);
		addSequential(new Drive(0, 50), 0.5);
		addSequential(new Wait(1.5));
		addSequential(new Drive(50, 0), 0.2);
		addSequential(new WaitForGear(), 5);
		addSequential(new Drive(-60, 0), 1.5);
		addSequential(new Drive(0, -50));
		
//		addSequential(new Drive(92, 0), 2);
//		addSequential(new Drive(50, 58), 0.3);
////		addSequential(new Wait(1.5));
////		addSequential(new Drive(50, 0), 0.2);
//		addSequential(new WaitForGear(), 5);
//		addSequential(new Drive(-60, 0), 1.5);
//		addSequential(new Drive(0, -50));
	}
	

}

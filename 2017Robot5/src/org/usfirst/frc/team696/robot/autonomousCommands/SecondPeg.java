package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SecondPeg extends CommandGroup {
	
	public SecondPeg() {
		
	}
	
	public void leftSide(){
		
	}
	
	public void middleSide(){
		
		addSequential(new Drive(90, -10));
		addSequential(new Drive(100, 10));
		addSequential(new Drive(0, 150));
		addSequential(new Drive(4.7, 0));
//		addSequential(new Drive(-4.7, 0));
		
	}
	
	public void rightSide(){
		
	}

}

package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FirstPeg extends CommandGroup {

	public FirstPeg() {
		
	}
	
	public void leftSide(){
		
		addSequential(new Drive(40, 0));
		addSequential(new Drive(0, 90));
		addSequential(new Drive(40, 0));
		addSequential(new Drive(0, -90));
		addSequential(new Drive(13.3, 0));
//		addSequential(new Drive(-10, 0));	
	}
	
	public void middleSide() {
		
//		addSequential(new Drive(90, -10));
//		addSequential(new Drive(100, 10));
//		addSequential(new Drive(0, 150));
//		addSequential(new Drive(4.7, 0));
//		addSequential(new Drive(-4.7, 0));
		
		addSequential(new Drive(70, 0));
		addSequential(new Drive(0, -90));
		addSequential(new Drive(10, 0));
		addSequential(new Drive(0, 90));
		addSequential(new Drive(22.3, 0));
//		addSequential(new Drive(-10, 0));
		
		
	}
	
	public void rightSide(){
		
		addSequential(new Drive(0, -90));
		addSequential(new Drive(100, 0));
		addSequential(new Drive(0, 90));
		addSequential(new Drive(91.3, 0));
//		addSequential(new Drive(-10, 0));
		
	}
	
}

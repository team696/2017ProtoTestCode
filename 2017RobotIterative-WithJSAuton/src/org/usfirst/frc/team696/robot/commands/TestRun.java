package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.utilities.JSCommand;

public class TestRun extends JSCommand {
	int i = 0;
	
	@Override
	public void update(){
		System.out.println("this is: " + i + " cycles");
		i++;
		if(i > 10)JSCommand.isFinished = false;
	}
}

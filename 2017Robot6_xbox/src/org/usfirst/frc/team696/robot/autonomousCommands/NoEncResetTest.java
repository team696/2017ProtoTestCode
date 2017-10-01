package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoEncResetTest extends CommandGroup {
	
	public NoEncResetTest() {
//		addSequential(new ZeroYaw());
		addSequential(new Drive(0, 90), 3);
		addSequential(new Wait(3));
		addSequential(new Drive(0, 0), 3);
		addSequential(new Wait(3));
	}

}

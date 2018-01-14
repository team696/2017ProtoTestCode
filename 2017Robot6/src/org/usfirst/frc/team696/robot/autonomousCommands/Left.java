package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.ZeroYaw;

public class Left extends CommandGroup {

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    public Left() {

        if(gameData.charAt(0) == 'L'){

            addSequential(new ZeroYaw());
            addSequential(new Drive(160, 0, 1, 0), 4);
            addSequential(new Drive(20, 30, 0.5, 0.5), 1);

        }

    }

}

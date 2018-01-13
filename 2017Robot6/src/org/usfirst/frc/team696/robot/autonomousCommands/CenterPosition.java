package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;

public class CenterPosition extends CommandGroup {


    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    public CenterPosition(){

        if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {

            addSequential(new Drive(83, 0), 4);
            addSequential(new Drive(0, 12), 4);
            addSequential(new Drive());
        }

        




        }


}

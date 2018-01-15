package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.ZeroYaw;

public class Left extends CommandGroup {

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    public Left() {

        if(gameData.charAt(0) == 'L') {

            addSequential(new ZeroYaw());
            addSequential(new Drive(40, 0, 0.75, 0), 1);
            addSequential(new Drive(40, -15, 0.5, 0.2));
            addSequential(new Wait(5));
            addSequential(new Drive(100, 0, 0.9, 0.05));
            addSequential(new Drive(50, 0, 0.75, 0.05));
////            addSequential(new Drive(50, -5, 0.5, 0.75), 0.75);
////            addSequential(new Drive( 20, 0,0.75,0.0), .25);
//            addSequential(new Drive(50, 0, 0.5,0.0), 1.0);
//            addSequential(new Drive(40, -15, 0, 0.5), 1.0);
//            addSequential(new Drive(230, 0, 1.0, 0.0), 2.5);
//            addSequential(new Drive(40, 5, 0.75,0.5), 1.5);
//            addSequential(new Drive(50, 0, 0.75, 0.5));
////            addSequential(new Drive( 50, ))

        }
        

    }

}

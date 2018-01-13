package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.Wait;

public class CenterPos extends CommandGroup {

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    public CenterPos(){
//        if(gameData.charAt(0) == 'L'){
//
//            addSequential(new Drive(10, 0, 0.5, 0.015));
//            addSequential(new Drive(90, -30, 1, 0.1));
////            addSequential(new Drive(30, 0, 0.5, 0.015));
//
//        }
    }

}

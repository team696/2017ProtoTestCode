package org.usfirst.frc.team696.robot.autonomousCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.VoltageDrive;
import org.usfirst.frc.team696.robot.commands.Wait;
import org.usfirst.frc.team696.robot.commands.ZeroYaw;

public class Center extends CommandGroup {

    String gameData = DriverStation.getInstance().getGameSpecificMessage();


    /*

    CENTER TO LEFT SWITCH

            addSequential(new ZeroYaw());
            addSequential(new Drive(40, 0, 0.75, 0), 0.5);
            addSequential(new Drive(100, -45, 0.75, 0.5), 1.5);
            addSequential(new Drive(40, 0, 0.75, 0.4));
            addSequential(new VoltageDrive(0.4, 2));


     CENTER TO RIGHT SWITCH
            addSequential(new ZeroYaw());
            addSequential(new Drive(40, 0, 0.75, 0), 0.5);
            addSequential(new Wait(0));
            addSequential(new Drive(90, 35, 0.75, 0.5), 2);
            addSequential(new Drive(30, 0, 0.75, 0.5));
            addSequential(new Wait(0.5));
            addSequential(new VoltageDrive(0.4, 2));

     */

    public Center() {

        if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R'){

            addSequential(new ZeroYaw());
            addSequential(new Drive(40, 0, 0.75, 0), 0.5);
            addSequential(new Wait(0));
            addSequential(new Drive(90, -50, 0.75, 0.5), 1.2);
            addSequential(new Drive(50, 0, 0.5, 0.4), 1);

            addSequential(new Wait(0.5));
            addSequential(new VoltageDrive(0.4, 1));
//            addSequential(new Drive(-60, 90, 0.3, 0.4), 2);
//            addSequential(new Wait(0.3));
//            addSequential(new Drive(140, 0, 0.5, 1), 2);
//            addSequential(new Wait(0.3));
//            addSequential(new Drive(50, 90, 0.5, 0.75), 2);

            addSequential(new Drive(-20, -90, 0.5, 0.75), 1);
            addSequential(new Drive(50, -90, 0.75, 0), 1);
            addSequential(new Drive(120, 0, 0.9, 0.4), 1.5);
            addSequential(new Drive(80, 90, 0.75, 0.5), 1.5);
            addSequential(new Drive(10, 180, 0.5, 0.75), 1.5);

        }

        if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L'){

            addSequential(new ZeroYaw());
            addSequential(new Drive(40, 0, 0.75, 0), 0.5);
            addSequential(new Wait(0));
            addSequential(new Drive(90, 35, 0.75, 0.5), 1.5);
            addSequential(new Wait(0.1));
            addSequential(new Drive(30, 0, 0.5, 0.4));
            addSequential(new Wait(0.5));
            addSequential(new VoltageDrive(0.4, 1));
            addSequential(new Drive(-20, 90, 0.5, 0.75 ), 1.5);
            addSequential(new Drive(50, 90, 0.75, 0 ), 1.5);
            addSequential(new Drive(120, 0, 0.9, 0.4), 1.5);
            addSequential(new Drive(80, -90, 0.75, 0.5 ), 1.5);
            addSequential(new Drive(10, -180, 0.5, 0.75 ), 1.5);

        }
//
//        if(gameData.charAt(0) == "L" && gameData.charAt(1) == 'L');
//
//            addSequential(new ZeroYaw());
//            addSequential(new Drive(30, 90, 0.5, 0.75), 1.5);
//            addSequential(new Drive( 30, 0, 0.5, 0.75), 1.5);

    }
}

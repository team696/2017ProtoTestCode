package frc.team696.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;



public class TimerTest extends Subsystem {

    int loopNumber = 0;

    Timer time = new Timer();


    boolean firstTimeRun = true;

    public TimerTest(){


    }

    public void testRun(){

        if(firstTimeRun){
            firstTimeRun = false;
            time.stop();
            time.reset();
            time.start();
        }

        System.out.println("time               " + time.get());
        loopNumber++;
        System.out.println("loopNumber                              " + loopNumber);





    }

    public void initDefaultCommand() {

    }
}


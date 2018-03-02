package frc.team696.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;



public class SideSwipe extends Subsystem {

    VictorSP sideSwipe;


     public int loopNumber = 0;

     boolean isDeployed = false;
     boolean afterDeployed =false;



    public SideSwipe(int sideSwipe){

        this.sideSwipe = new VictorSP(sideSwipe);

    }

    public void fakeHookDeploy(){

        isDeployed = true;

    }
    public void setSideSwipe(double speed){

        loopNumber++;

        System.out.println(loopNumber);

        fakeHookDeploy();

        if(loopNumber >= 300 && isDeployed){

            sideSwipe.set(speed);
            afterDeployed = true;


        }

        if(isDeployed && afterDeployed == true){

            sideSwipe.set(speed);
        }


    }



    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}


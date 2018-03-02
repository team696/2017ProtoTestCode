package frc.team696.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;



public class pidPivot extends Subsystem {

    TalonSRX pivot;


    public pidPivot(int pivot){

        this.pivot = new TalonSRX(pivot);
        this.pivot.set(ControlMode.Position, 0);
        this.pivot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

        this.pivot.config_kP(0,0,20);
        this.pivot.config_kI(0,0,20);
        this.pivot.config_kD(0,0,20);
        this.pivot.config_kF(0,0,20);
    }

    public void ismailIsAnIdiot(){

        System.out.println("                              " + pivot.getSelectedSensorPosition(0));

    }


    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());


    }
}


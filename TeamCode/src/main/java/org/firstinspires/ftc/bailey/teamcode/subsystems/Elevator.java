package org.firstinspires.ftc.bailey.teamcode.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;
import org.firstinspires.ftc.bailey.teamcode.hardware.BTouchSensor;

/**
 * Created by unbun on 12/12/2017
 */

public class Elevator {


    //need simple P to optimize elevator control
    private final double kP = 1.0;

    //Harwade declared
    private BMotor winchMotor;
    private Intake liftIntake; // dis getting really meta w/ a subsystem in a subsystem.
    private BTouchSensor limitSwitch;

    private final double WINCH_INCREMENT = 8.0; // in inches
    private final double MAX_HEIGHT = 32.0;
    private final double MIN_HEIGHT = 0.0;
    private final double POS_TOLELRANCE = 0.1;

    private double relativeClicks = 0.;

    //Timing for control
    ElapsedTime timer = new ElapsedTime();

    public Elevator(BMotor winch, BTouchSensor touch, Intake intake) {
        this.winchMotor = winch;
        this.limitSwitch = touch;
        this.liftIntake = intake;
    }

    /**
     *@param power power to run winch
     */
    public void run(double power){
        this.winchMotor.setPower(power);
    }

    private double getRelativeEncoderPos() {
        return winchMotor.getCurrentPos() + relativeClicks;
    }

    /**
     * @param up true --> move up; false --> move down
     */
    public boolean changePos(boolean up){
        double setpoint; // in feet


        if(up){
            setpoint = Math.min(MAX_HEIGHT, winchMotor.getCurrentPos() + WINCH_INCREMENT);
        }else{
            setpoint = Math.max(MIN_HEIGHT, winchMotor.getCurrentPos() - WINCH_INCREMENT);
        }

        //ticks = inches * (ticks /rev ) * (rev/inches)
        double circ = this.liftIntake.getWheelDiameterInches() * Math.PI;
        setpoint = setpoint * winchMotor.getCountsPerRev() * (1 / circ);

        double diff = setpoint - winchMotor.getCurrentPos();
        double pow = kP * diff;

        if(limitSwitch.isPressed() ){
            pow = 0;
        }

        run(pow);
        return POS_TOLELRANCE > Math.abs(diff); // return true when diff is w/in tolerance
    }

    public void resetWinch() {

        relativeClicks = winchMotor.getCurrentPos();
    }

    public double getPosition(){
        return winchMotor.getCurrentPos();
    }

    public double getPositionInches(){
        return winchMotor.getCurrentPos() * liftIntake.getWheelDiameterInches() * winchMotor.getCountsPerRev();
    }

    public boolean getSwtichStatus(){
        return limitSwitch.isPressed();
    }

    public Intake getLiftIntake(){
        return this.liftIntake;
    }
}


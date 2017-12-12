package org.firstinspires.ftc.bailey.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.TouchSensor;
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
    private BTouchSensor bottomLimit;

    private final double WINCH_INCREMENT = 8.0;
    private final double MAX_HEIGHT = 32.0;
    private final double MIN_HEIGHT = 0.0;
    private final double POS_TOLELRANCE = 0.01;

    private double relativeClicks = 0.;

    //Timing for control
    ElapsedTime timer = new ElapsedTime();

    public Elevator(BMotor winch, BTouchSensor touch, Intake intake) {
        this.winchMotor = winch;
        this.bottomLimit = touch;
        this.liftIntake = intake;
    }

    /**
     *@param power power to run winch
     */
    public void run(double power){
        if(this.bottomLimit.isPressed()){
            this.winchMotor.setPower(0);
        }else {
            this.winchMotor.setPower(power);
        }
    }

    private double getRelativeEncoderPos() {
        return winchMotor.getCurrentPos() + relativeClicks;
    }

    /**
     * @param up true --> move up; false --> move down
     */
    public boolean changePos(boolean up){
        double setpoint;

        if(up){
            setpoint = Math.max(MAX_HEIGHT, winchMotor.getCurrentPos() + WINCH_INCREMENT);
        }else{
            setpoint = Math.min(MIN_HEIGHT, winchMotor.getCurrentPos() - WINCH_INCREMENT);
        }

        double diff = setpoint - winchMotor.getCurrentPos();
        double pow = kP * diff;
        run(pow);
        return POS_TOLELRANCE > Math.abs(diff); // return true when diff is w/in tolerance
    }

    /**
     * @param position 0-3
     */
    public boolean changePos(int position){
        double setpoint = position * WINCH_INCREMENT;

        double diff = setpoint - winchMotor.getCurrentPos();
        double pow = kP * diff;
        run(pow);
        return POS_TOLELRANCE > Math.abs(diff); // return true when diff is w/in tolerance
    }

    public void resetWinch() {
        relativeClicks = winchMotor.getCurrentPos();
    }

    public double getPosition(){
        return winchMotor.getCurrentPos();
    }

    public boolean getSwtichStatus(){
        return bottomLimit.isPressed();
    }

    public Intake getLiftIntake(){
        return this.liftIntake;
    }
}


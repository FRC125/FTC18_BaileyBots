package org.firstinspires.ftc.bailey.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by JYoung413 on 10/12/2017.
 * unbun on 12/12/2017
 */

public class BMotor {

    private DcMotor m;

    private MotorType motorType;

    private int countsPerRev;

    /**
     *
     * @param m DCMotor.
     * @param motorType Motor Type.
     */
    public BMotor (DcMotor m, MotorType motorType) {
        this.m = m;
        setMotorType(motorType);
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Sets the motor type given the desire motor type.
     * @param motorType Motor Type.
     */
    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
        this.countsPerRev = motorType.getCountsPerRev();
    }

    /**
     * Specifys weather or not the motor should be reversed.
     * @param reversed is it reversed?
     */
    public void setReversed(boolean reversed) {
        if (reversed) {
            m.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        else {
            m.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    /**
     * Returns the target position of the BBMotor.
     * @return target position.
     */
    public double getTargetPosition() { return m.getTargetPosition(); }

    /**
     * Returns the power being supplied to the BBMotor.
     * @return power being supplied to motor.
     */
    public double getPower() { return m.getPower(); }

    /**
     * Returns the set motor type of this motor.
     * @return motor type.
     */
    public MotorType getMotorType() {
        return motorType;
    }

    /**
     *
     *
     * Gets counts per revolution of the motor.
     * @return counts per revolution.
     */
    public int getCountsPerRev() {
        return countsPerRev;
    }

    public void setMode(DcMotor.RunMode runMode) {
        m.setMode(runMode);
    }

    public int getCurrentPos() {
        return m.getCurrentPosition();
    }

    /**
     * Sets motor power.
     * @param power amount of power.
     */
    public void setPower(double power) {
        m.setPower(power);
    }

    private double getRelativeTarget(double targetCounts) {
        return getCurrentPos() + targetCounts;
    }

    /**
     * Distance Running and Turning Methods.
     */

    /**
     * Turns motor a certain amount of revolutions.
     * @param revolutions desired revolutions, should be used hand in hand with distances basaed on wheel circumfrence.
     */
    public void revolutionRun(double revolutions, double speed) {
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setTargetPosition((int)getRelativeTarget(revolutions * getCountsPerRev()));
        m.setPower(speed);
    }

    public enum MotorType {
        RevHDHex(2240), NeveRest(1120), CoreHex(288);

        private int countsPerRev;

        MotorType(int countsPerRev){
            this.countsPerRev = countsPerRev;
        }

        public int getCountsPerRev(){
            return this.countsPerRev;
        }

    }
}

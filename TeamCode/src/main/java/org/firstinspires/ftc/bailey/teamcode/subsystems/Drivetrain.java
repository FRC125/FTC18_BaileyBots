package org.firstinspires.ftc.bailey.teamcode.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;
import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;

/**
 * Created by Josh on 11/6/2017.
 * unbun on 12/12/2017
 */

public class Drivetrain {

    //PD constants for control g is for Gyro, k is for Drivetrain
    private final double kP = 1.0;
    private final double kD = 0.5;
    private final double gP = 1.0;
    private final double gD = 0.5;


    //Motors declared
    private BMotor leftFrontDrive;
    private BMotor leftBackDrive;
    private BMotor rightFrontDrive;
    private BMotor rightBackDrive;

    //Gyro declared
    private BGyro gyro;


    //Declaring drivetrain specifics for driving distance
    private double wheelCircumfrence;
    private double ticksPerRev;
    private double feetPerTick;

    //Timing for control
    ElapsedTime timer = new ElapsedTime();

    //Auto Constants
    private double lastError = 0.;
    private double lastTime = 0.;
    private double relativeClicks = 0.;
    private double headingToHold = 0.;
    private double driveDistanceTolerance = 20;
    private double turnToAngleTolerance = 5;


    /**
     * Drivetrain class used for controlling BB18 robot drivebase
     *
     * @param lF   left front motor of the drivetrain
     * @param rF   right front motor of the drivetrain
     * @param gyro gyro used for heading
     */
    public Drivetrain(BMotor lF, BMotor lB, BMotor rF, BMotor rB, BGyro gyro) {
        this.leftFrontDrive = lF;
        this.rightFrontDrive = rF;
        this.leftBackDrive = lB;
        this.rightBackDrive = rB;

        //Drivetrain specifics initialized
        wheelCircumfrence = Math.PI * (1 / 3); // Feet
        ticksPerRev = leftFrontDrive.getCountsPerRev();
        feetPerTick = wheelCircumfrence / ticksPerRev;
    }

    private double getRelativeEncoderPos() {
        return leftFrontDrive.getCurrentPos() + relativeClicks;
    }

    public boolean distanceDrive(double ftDistance) { // REQUIRES HOLD HEADING!!!
        double setpoint = ftDistance / feetPerTick;
        double error = setpoint - getRelativeEncoderPos();
        double now = timer.seconds();
        double dt = now - lastTime;
        lastTime = now;
        double output = (kP * error) + ((kD * (error - lastError)) / dt);
        tankDrive(output, output, true);
        lastError = error;
        error = setpoint - getRelativeEncoderPos();
        return driveDistanceTolerance > error;
    }

    public void resetDriveDistance() {
        relativeClicks = leftFrontDrive.getCurrentPos();
        lastError = 0.;
        lastTime = 0.;
        timer.reset();
    }

    public void setAngleToHold(double angleToHold) {
        headingToHold = angleToHold;
    }

    /**
     * Turns robot to specified angle using simple P loop
     *
     * @param angleSetpoint specified angle
     */
    public boolean turnToAngle(double angleSetpoint) {
        double gyroHeading = gyro.getAngle();
        double angleDifference = angleSetpoint - gyroHeading;
        double turn = gP * angleDifference;
        tankDrive(turn, -turn, false);
        return turnToAngleTolerance > angleDifference;
    }

    /**
     * Sets power to the drivetrain thus driving it
     *
     * @param lPower power to the left side of the drivetrain
     * @param rPower power to the right side of the drivetrain
     * @param holdHeading true --> don't turm; false --> use gyro & input to turn
     */
    public void tankDrive(double lPower, double rPower, boolean holdHeading) {
        if (holdHeading) {// want to turn
            double gyroHeading = gyro.getAngle();
            double angleDifference = headingToHold - gyroHeading;
            double turn = gP * angleDifference;
            lPower += turn;
            rPower -= turn;
        }
        leftFrontDrive.setPower(lPower);
        leftBackDrive.setPower(lPower);
        rightFrontDrive.setPower(-rPower); //do you need to negate if the motor is set to reversed?
        rightBackDrive.setPower(-rPower);
    }

    public void tankDrive(double lPower, double rPower){
        this.tankDrive(lPower, rPower, false);
    }
}

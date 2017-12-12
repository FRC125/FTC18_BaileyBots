package org.firstinspires.ftc.bailey.teamcode;
/**
 * Created by Josh on 10/12/2017.
 * Created by unbun on 12/12/2017.
 */

import android.text.method.Touch;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * The purpose of the BB18Map class is to work together with a Robot class to add easy implementation across op-modes and auto.
 * Holds named concurrent to that of the robot controller.
 */
public class BaileyMap {

    //Drivetrain
    DcMotor leftFrontDrive;
    DcMotor leftBackDrive;
    DcMotor rightFrontDrive;
    DcMotor rightBackDrive;

    //Intake
    DcMotor leftIntake;
    DcMotor rightIntake;

    //Elevator
    DcMotor liftWinch;
    TouchSensor liftSwitch;
    DcMotor liftIntakeL;
    DcMotor liftIntakeR;

    //IMU
    BNO055IMU gyro; // pronouncecd " ji - row"

    /**
     * Map class used for easy implementation of custom motor classes and BB18 robot class
     */
    public BaileyMap() {
        //Empty on purpose
    }

    /**
     * Initializes custom map class with passed through hardwareMap from op-mode class
     * @param hardwareMap hardware map from op-mode class
     */
    public void init(HardwareMap hardwareMap) {

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back");

        leftIntake = hardwareMap.get(DcMotor.class, "left_intake");
        rightIntake = hardwareMap.get(DcMotor.class, "right_intake");

        liftWinch = hardwareMap.get(DcMotor.class, "lift_winch");
        liftSwitch = hardwareMap.get(TouchSensor.class, "lift_bottomLimit");
        liftIntakeL = hardwareMap.get(DcMotor.class, "lift_intake_L");
        liftIntakeR = hardwareMap.get(DcMotor.class, "lift_intake_R");

        gyro = hardwareMap.get(BNO055IMU.class, "gyro"); //IMU
    }

}
package org.firstinspires.ftc.bailey.teamcode;
/**
 * Created by Josh on 10/12/2017.
 * Created by unbun on 12/12/2017.
 */

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * The purpose of the BB18Map class is to work together with a Robot class to add easy implementation across op-modes and auto.
 * Holds named concurrent to that of the robot controller.
 *
 * Basically, the phones know the right-side motor as "right_front", but the code knows the right-side motor as "leftDrive". So this class sets them equal to each other.
 */
public class BaileyMap {

    //Drivetrain
    DcMotor leftDrive;
    DcMotor rightDrive;
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

        leftDrive = hardwareMap.get(DcMotor.class, "left_front"); // drivetrain - neverrest 40
        rightDrive = hardwareMap.get(DcMotor.class, "right_front"); // drivetrain - neverrest 40
        gyro = hardwareMap.get(BNO055IMU.class, "imu"); //IMU
    }

}
package org.firstinspires.ftc.bailey.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;
import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Drivetrain;


/**
 * Created by Unnas on 12/12/2017.
 *
 */

public class BaileyBot {

    //Declaring drivetrain motors and object
    public BMotor leftFrontDrive;
    public BMotor rightFrontDrive;
    public Drivetrain Drivetrain;

    //IMU
    public BGyro gyro;


    public BaileyMap robotMap;

    /**
     * BaileyBot Robot class
     *
     * @param hardwareMap hardwareMap from op-mode class
     */
    public BaileyBot(HardwareMap hardwareMap) {

        robotMap = new BaileyMap(); //Declaring map class
        robotMap.init(hardwareMap); //Receiving passed through map class for creating custom hardware

        //Assigning Drivetrain Motors and Object
        leftFrontDrive = new BMotor(robotMap.leftFrontDrive, BMotor.MotorType.RevHDHex);
        rightFrontDrive = new BMotor(robotMap.rightFrontDrive, BMotor.MotorType.RevHDHex);
        gyro = new BGyro(robotMap.gyro); // Assigning IMU
        Drivetrain = new Drivetrain(leftFrontDrive, rightFrontDrive, gyro);

    }
}
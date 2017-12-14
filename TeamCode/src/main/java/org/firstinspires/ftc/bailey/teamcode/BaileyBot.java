package org.firstinspires.ftc.bailey.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;
import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Drivetrain;


/**
 * Created by Unnas on 12/12/2017.
 *
 * This class is where all of the subsystems are initalized and given their electronics from the BaileyMap.
 */

public class BaileyBot {

    //Declaring drivetrain motors and object
    public BMotor leftFrontDrive; // Motor objects
    public BMotor rightFrontDrive;
    public Drivetrain Drivetrain; // The drivetrain object (remember the Drivetrain.java class that was written?)

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
        leftFrontDrive = new BMotor(robotMap.leftDrive, BMotor.MotorType.RevHDHex); // creating the motors object
        rightFrontDrive = new BMotor(robotMap.rightDrive, BMotor.MotorType.RevHDHex);
        gyro = new BGyro(robotMap.gyro); // Assigning IMU (creating the gyro object)
        Drivetrain = new Drivetrain(leftFrontDrive, rightFrontDrive, gyro); //creating the Drivetrain class

    }
}
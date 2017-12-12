package org.firstinspires.ftc.bailey.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;
import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;
import org.firstinspires.ftc.bailey.teamcode.hardware.BTouchSensor;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Intake;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Elevator;


/**
 * Created by Unnas on 12/12/2017.
 */

public class BaileyBot {

    //Declaring drivetrain motors and object
    public BMotor leftFrontDrive;
    public BMotor leftBackDrive;
    public BMotor rightFrontDrive;
    public BMotor rightBackDrive;
    public Drivetrain Drivetrain;

    //Declaring intake motors and object
    public BMotor leftTake;
    public BMotor rightTake;
    public Intake Intake;

    //Declaring elevator motors, sensors, and object
    public BMotor liftWinch;
    public BTouchSensor liftSwitch;
    public Elevator Elevator;

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
        leftBackDrive = new BMotor(robotMap.leftBackDrive, BMotor.MotorType.RevHDHex);
        rightFrontDrive = new BMotor(robotMap.rightFrontDrive, BMotor.MotorType.RevHDHex);
        rightBackDrive = new BMotor(robotMap.rightBackDrive, BMotor.MotorType.RevHDHex);
        gyro = new BGyro(robotMap.gyro); // Assigning IMU
        Drivetrain = new Drivetrain(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive, gyro);

        //Assigning Intake Motors and Object
        leftTake = new BMotor(robotMap.leftIntake, BMotor.MotorType.RevHDHex);
        rightTake = new BMotor(robotMap.rightIntake, BMotor.MotorType.RevHDHex);
        Intake = new Intake(leftTake, rightTake);

        //Assigning Elevator Motors, Sensors, and Object
        liftWinch = new BMotor(robotMap.liftWinch, BMotor.MotorType.RevHDHex);
        liftSwitch = new BTouchSensor(robotMap.liftSwitch);
        Elevator = new Elevator(liftWinch, liftSwitch);

    }

}
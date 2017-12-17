package org.firstinspires.ftc.bailey.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;
import org.firstinspires.ftc.bailey.teamcode.hardware.BMotor;
import org.firstinspires.ftc.bailey.teamcode.hardware.BTouchSensor;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Intake;
import org.firstinspires.ftc.bailey.teamcode.subsystems.Elevator;


/**
 * Created by Unnas on 12/12/2017.
 *
 */

public class BaileyBot {

    //Declaring drivetrain motors and object
    public BMotor leftDrive;
    public BMotor rightDrive;
    public Drivetrain Drivetrain;

    //Declaring intake motors and object
    public BMotor leftIntake;
    public BMotor rightIntake;
    public Intake Intake;

    //Declaring elevator motors, sensors, and object
    public BMotor liftWinch;
    public BTouchSensor liftSwitch;
    public BMotor liftIntakeL;
    public BMotor liftIntakeR;
    public Intake liftIntake;
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
        leftDrive = new BMotor(robotMap.leftDrive, BMotor.MotorType.RevHDHex);
        rightDrive = new BMotor(robotMap.rightDrive, BMotor.MotorType.RevHDHex);
        gyro = new BGyro(robotMap.gyro); // Assigning IMU
        Drivetrain = new Drivetrain(leftDrive, rightDrive, gyro);


        //Assigning Elevator Motors, Sensors, and Object
        liftWinch = new BMotor(robotMap.liftWinch, BMotor.MotorType.RevHDHex);
        liftSwitch = new BTouchSensor(robotMap.liftSwitch);
        liftIntakeL = new BMotor(robotMap.liftIntakeL, BMotor.MotorType.RevHDHex); // for lift intake
        liftIntakeR = new BMotor(robotMap.liftIntakeR, BMotor.MotorType.RevHDHex); // for lift intake
        liftIntake = new Intake(liftIntakeL, liftIntakeR); // lift intake
        Elevator = new Elevator(liftWinch, liftSwitch, liftIntake);

    }
}
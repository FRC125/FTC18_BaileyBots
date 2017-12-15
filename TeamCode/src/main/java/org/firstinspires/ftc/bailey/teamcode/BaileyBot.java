package org.firstinspires.ftc.bailey.teamcode;

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

        gyro = new BGyro(robotMap.gyro); // Assigning IMU

        //Assigning Elevator Motors, Sensors, and Object
        liftWinch = new BMotor(robotMap.liftWinch, BMotor.MotorType.RevHDHex);
        liftSwitch = new BTouchSensor(robotMap.liftSwitch);
        Elevator = new Elevator(liftWinch, liftSwitch);

    }
}
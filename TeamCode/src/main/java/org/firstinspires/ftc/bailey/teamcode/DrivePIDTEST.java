package org.firstinspires.ftc.bailey.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.bailey.teamcode.BaileyBot;

/**
 * Created by unnas on 11/28/17.
 *
 */

@Autonomous(name="DrivePIDTEST", group="Autons")
public class DrivePIDTEST extends OpMode
{
    BaileyBot Robot;
    int autoState;

    @Override
    public void init() {
        Robot = new BaileyBot(hardwareMap);
    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){
        autoState = 0;
        Robot.Drivetrain.resetDriveDistance();
        Robot.Drivetrain.setAngleToHold(0.0);
    }


    @Override
    public void loop() {

        switch (autoState) {
            case 0:
                if(Robot.Drivetrain.distanceDrive(10.0)){ // drive forward 10 ft
                    autoState = 1;
                    Robot.Drivetrain.resetDriveDistance();
                }
                break;
            case 1:
                if(Robot.Drivetrain.turnToAngle(90.0)) { // turn 90 deg
                    autoState = 2;
                    Robot.Drivetrain.setAngleToHold(90.0);
                }
                break;
            case 2:
                if(Robot.Drivetrain.distanceDrive(5.0)) { // drive forward 5 ft
                    autoState = 3;
                    Robot.Drivetrain.resetDriveDistance();
                }
                break;
            default:
                break;
        }


    }

    @Override
    public void stop() {

    }

}
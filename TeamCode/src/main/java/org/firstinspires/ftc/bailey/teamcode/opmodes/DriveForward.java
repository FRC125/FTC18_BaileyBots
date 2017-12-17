package org.firstinspires.ftc.bailey.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.bailey.teamcode.BaileyBot;

/**
 * Created by Josh on 11/28/17.
 *
 */

@Autonomous(name="DriveForward", group="Autons")
public class DriveForward extends OpMode
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

        //drive logic
        switch (autoState) {
            case 0:
                if(Robot.Drivetrain.distanceDrive(10.0)){ // drive forward 3 ft
                    autoState = 1;
                    Robot.Drivetrain.resetDriveDistance();
                    Robot.Elevator.changePos(4);// bring the glpyh all the way up
                }
                break;
        }

    }

    @Override
    public void stop() {
        Robot.Drivetrain.tankDrive(0,0);
    }

}
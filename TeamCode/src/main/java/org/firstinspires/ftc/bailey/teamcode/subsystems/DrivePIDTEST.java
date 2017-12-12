package org.firstinspires.ftc.bailey.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;/
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.bailey.teamcode.BaileyBot;
import org.firstinspires.ftc.teamcode.BB18;

/**
 * Created by josh on 11/28/17.
 */

@Autonomous(name="DrivePIDTEST", group="Auto Testing")
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
                if(Robot.Drivetrain.distanceDrive(10.0)){
                    autoState = 1;
                    Robot.Drivetrain.resetDriveDistance();
                }
                break;
            case 1:
                if(Robot.Drivetrain.turnToAngle(90.0)) {
                    autoState = 2;
                    Robot.Drivetrain.setAngleToHold(90.0);
                }
                break;
            case 2:
                if(Robot.Drivetrain.distanceDrive(5.0)) {
                    autoState = 3;
                    Robot.Drivetrain.resetDriveDistance();
                }
                break;
            case 3:
                if(Robot.Elevator.changePos(3)){
                    autoState = 4;
                    Robot.Elevator.getLiftIntake().intake(true);
                }
            default:
                break;
        }


    }

    @Override
    public void stop() {

    }

}
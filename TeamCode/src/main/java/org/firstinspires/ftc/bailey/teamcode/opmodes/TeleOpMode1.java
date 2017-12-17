package org.firstinspires.ftc.bailey.teamcode.opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.bailey.teamcode.BaileyBot;
import org.firstinspires.ftc.bailey.teamcode.hardware.BGyro;

import static android.R.attr.isGame;
import static android.R.attr.left;
import static android.R.attr.right;

/**wf
 * Created by unnas on 12/12/2017.
 *
 */

@TeleOp(name = "TeleOp 1", group = "Teleop")
public class TeleOpMode1 extends OpMode {


    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;

    BGyro gyro = null;
    double initialAngle;
    double headingToHold;

    private ElapsedTime runTime = new ElapsedTime();

    private BaileyBot Robot;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        Robot = new BaileyBot(hardwareMap);
    }

    @Override
    public void init_loop() {
        publishData();
    }

    @Override
    public void start(){

        Robot.Elevator.run(0);
        Robot.Drivetrain.tankDrive(0, 0);
        init_loop();

    }


    @Override
    public void loop() {

        //Driving
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;
        Robot.Drivetrain.tankDrive(left, right);

        //Operating lift
        float lift_pow = -gamepad2.left_stick_y;
        Robot.Elevator.run(lift_pow);

        if(gamepad2.y){
            Robot.Elevator.changePos(true);
        }else if(gamepad2.a){
            Robot.Elevator.changePos(false);
        }

        //Operating intake
        if(gamepad2.left_bumper){
            Robot.Elevator.getLiftIntake().intake(true);
        }else if(gamepad2.right_bumper){
            Robot.Elevator.getLiftIntake().intake(false);
        }else{
            Robot.Elevator.getLiftIntake().stopIntake();
        }

        publishData();

    }

    @Override
    public void stop() {
        publishData();

        Robot.Elevator.run(0);
    }

    private void publishData(){
        telemetry.addData("Status", "Run Time: " + runTime.toString());
        telemetry.addData("Elevator Position: ", Robot.Elevator.getPosition());
        telemetry.addData("Elevator Switch: ", Robot.Elevator.getSwtichStatus());
        telemetry.addData("Driver Left Stick Y: ", -gamepad1.left_stick_y);
        telemetry.addData("Driver Right Stick Y: ", -gamepad1.right_stick_y);
        telemetry.addData("Operator Left Stick Y:", -gamepad2.left_stick_y);
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveTeleOp extends LinearOpMode {
    private void runOpMode() throws InterruptedException{
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor"));
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("front_left_motor"));
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("back_right_motor"));
        DcMotor backRightMotor = hardwareMap.dcMotor.get("back_left_motor");

        waitForStart();

        while (opModeIsActive()) {
            double y = -this.gamepad.left_stick_y;
            double x = this.gamepad.left_stick_x;
            double rx = this.gamepad.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightPower.setPower(backRightPower);
        }
    }
}
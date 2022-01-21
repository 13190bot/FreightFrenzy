package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="DriveTeleOp", group = "TeleOpCode")
public class DriveTeleOp extends LinearOpMode {

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;

    public void runOpMode() throws InterruptedException{
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        rearLeftMotor = hardwareMap.dcMotor.get("rearLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        rearRightMotor = hardwareMap.dcMotor.get("rearRight");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double y = -this.gamepad1.left_stick_y;
            double x = this.gamepad1.left_stick_x;
            double rx = this.gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (-y - rx - x) / denominator;
            double rearLeftPower = (-y - rx + x) / denominator;
            double frontRightPower = (y - rx - x) / denominator;
            double rearRightPower = (y - rx + x) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            rearLeftMotor.setPower(rearLeftPower);
            frontRightMotor.setPower(frontRightPower);
            rearRightMotor.setPower(rearRightPower);

            if (this.gamepad1.left_bumper) {
                duckMotor.setPower(0.5);
            }

            telemetry.addData("FrontLeftPower", frontLeftPower);
            telemetry.addData("BackLeftPower", rearLeftPower);
            telemetry.addData("FrontRightPower", frontRightPower);
            telemetry.addData("BackRightPower", rearRightPower);
            telemetry.update();
        }
    }
}
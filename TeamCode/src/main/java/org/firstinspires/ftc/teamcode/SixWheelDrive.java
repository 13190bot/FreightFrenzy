package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SixWheelDrive extends LinearOpMode {
    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor;

    public void runOpMode() {
        frontLeftMotor = hardwareMap.dcMotor.get("leftFront");
        rearLeftMotor = hardwareMap.dcMotor.get("leftRear");
        frontRightMotor  = hardwareMap.dcMotor.get("rightFront");
        rearRightMotor = hardwareMap.dcMotor.get("rightRear");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;

            double leftPower = y + x;
            double rightPower = y - x;

            frontLeftMotor.setPower(leftPower);
            rearLeftMotor.setPower(leftPower);
            frontRightMotor.setPower(rightPower);
            rearRightMotor.setPower(rightPower);

            telemetry.addData("frontLeftPower",frontLeftMotor.getPower());
            telemetry.addData("rearLeftPower",rearLeftMotor.getPower());
            telemetry.addData("frontRightPower",frontRightMotor.getPower());
            telemetry.addData("rearLeftPower",rearRightMotor.getPower());
            telemetry.update();
        }
    }
}

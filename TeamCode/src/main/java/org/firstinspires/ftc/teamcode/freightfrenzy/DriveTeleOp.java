package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name="DriveTeleOp", group = "TeleOpCode")
public class DriveTeleOp extends LinearOpMode {

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;
    private SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

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
            double horizontal = gamepad1.left_stick_x;
            double vertical = gamepad1.left_stick_y;
            double angle = gamepad1.right_stick_x;

            drive.setWeightedDrivePower(new Pose2d(vertical, horizontal, angle)); // in roadrunner x is vertical and y is horizontal
            drive.update();

            if (this.gamepad1.left_bumper) {
                duckMotor.setPower(0.5);
            }

            telemetry.addData("FrontLeftPower", frontLeftMotor.getPower());
            telemetry.addData("BackLeftPower", rearLeftMotor.getPower());
            telemetry.addData("FrontRightPower", frontRightMotor.getPower());
            telemetry.addData("BackRightPower", rearRightMotor.getPower());
            telemetry.update();
        }
    }
    @SuppressWarnings("SameParameterValue")
    protected void mechanumDrive(boolean xDisable, boolean yDisable) {
        double horizontal = -gamepad1.left_stick_x;
        double vertical = -gamepad1.left_stick_y;
        double angle = -gamepad1.right_stick_x;

        if (xDisable) {
            horizontal = 0;
        }
        if (yDisable) {
            vertical = 0;
        }

        drive.setWeightedDrivePower(new Pose2d(vertical, horizontal, angle)); // in roadrunner x is vertical and y is horizontal
        drive.update();
    }
}
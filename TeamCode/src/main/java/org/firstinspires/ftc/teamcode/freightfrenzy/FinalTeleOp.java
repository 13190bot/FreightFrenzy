package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class FinalTeleOp extends DriveTeleOp{

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;

    public void runOpMode() {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        rearLeftMotor = hardwareMap.dcMotor.get("rearLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        rearRightMotor = hardwareMap.dcMotor.get("rearRight");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

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
}

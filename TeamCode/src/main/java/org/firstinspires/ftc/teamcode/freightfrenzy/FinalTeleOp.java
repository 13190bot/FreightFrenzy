package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class FinalTeleOp extends DriveTeleOp{
    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;
    public void runOpMode() {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        rearLeftMotor = hardwareMap.dcMotor.get("rearLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        rearRightMotor = hardwareMap.dcMotor.get("rearRight");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");

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

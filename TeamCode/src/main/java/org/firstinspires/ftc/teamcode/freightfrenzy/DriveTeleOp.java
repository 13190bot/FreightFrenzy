package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Claw.*;

@TeleOp(name="DriveTeleOp", group = "TeleOpCode")
public class DriveTeleOp extends template {

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;

    public void runOpMode() throws InterruptedException{


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        initialize();

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




            telemetry.addData("rotationPosition", armRotationMotor.getCurrentPosition());
            telemetry.addData("intakeMotorPower", intakeMotor.getPower());
            if(isRotationTooFar()){
                telemetry.addData("Status: ", "too far");
            }
            if(gamepad1.y && !isRotationBusy()){
                toTopLevel();
            }
            if(gamepad1.b && !isRotationBusy()){
                toMiddleLevel();
            }
            if(gamepad1.a && !isRotationBusy()){
                toBottomLevel();
            }
            if(gamepad1.x && !isRotationBusy()){
                toPickupPosition();
            }
            if(gamepad1.right_trigger>0.2){
                intakeMotor.setPower(0.5);
            }
            if(gamepad1.left_trigger>0.2){
                intakeMotor.setPower(-0.5);
            }
            if(gamepad1.right_trigger<=0.2 && intakeMotor.getPower() > 0){
                intakeMotor.setPower(0);
            }
            if(gamepad1.left_trigger<=0.2 && intakeMotor.getPower() < 0){
                intakeMotor.setPower(0);
            }
            telemetry.update();
        }
    }
}
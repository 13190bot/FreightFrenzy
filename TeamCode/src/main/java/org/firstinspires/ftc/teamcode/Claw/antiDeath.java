package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class antiDeath extends LinearOpMode{
    public DcMotor armRotationMotor;
    @Override
    public void runOpMode() {
        armRotationMotor = hardwareMap.get(DcMotor.class, "rotationMotor");
        armRotationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status:", "init");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status:", "runnin");
            telemetry.update();
            double x = -gamepad1.left_stick_x;
            telemetry.addData("Power:", armRotationMotor.getPower());
            double currentPosition = (armRotationMotor.getCurrentPosition()*360)/(537.7*6);
            telemetry.addData("Encoder:", currentPosition);
            armRotationMotor.setPower(x*0.5);
        }
    }
}

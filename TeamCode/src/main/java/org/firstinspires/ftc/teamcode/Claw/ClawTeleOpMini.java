package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ClawTeleOpMini extends LinearOpMode {

    private Servo servo;

    @Override
    public void runOpMode() {

        servo = hardwareMap.servo.get("servo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if(gamepad1.a) {
                servo.setPosition(0.5);
            } else if (gamepad1.b) {
                servo.setPosition(1);
            }

            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

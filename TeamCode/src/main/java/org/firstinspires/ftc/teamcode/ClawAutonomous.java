package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="ClawAutonomous", group="LinearOpMode")
//@Disabled
public class ClawAutonomous extends LinearOpMode {

    private Servo servo1, servo2, servo3;
    private DcMotor dc;

    @Override
    public void runOpMode() throws InterruptedException {

        servo1 = hardwareMap.servo.get("servoBottom");
        servo2 = hardwareMap.servo.get("servoMiddle");
        servo3 = hardwareMap.servo.get("servoTop");

        dc = hardwareMap.dcMotor.get("dcMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

        }
    }
}

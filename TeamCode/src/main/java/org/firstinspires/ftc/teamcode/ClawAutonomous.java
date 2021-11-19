package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
        dc.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        pickUpObject();
        dropAtLevel(1);
    }

    public void pickUpObject() throws InterruptedException {
        if (opModeIsActive()) {
            //TODO have to modify the power and time in order to move claw properly
            //go down
            servo1.setPosition(0.083);
            servo2.setPosition(0);
            servo3.setPosition(1);
            sleep(500);
            dc.setPower(0.2);
            sleep(500);
            dc.setPower(0);

            //pick up
            servo3.setPosition(0);
            sleep(1000);
            dc.setPower(-0.2);
            sleep(500);
            dc.setPower(0);

            telemetry.addData("Servo1 Position", servo1.getPosition());
            telemetry.addData("Servo2 Position", servo2.getPosition());
            telemetry.addData("Claw status", "Opening");
            telemetry.update();
        }
    }

    public void dropAtLevel(int level) throws InterruptedException {

    }
}

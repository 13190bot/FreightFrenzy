package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ClawTeleOp extends LinearOpMode {

    private Servo servo;

    @Override
    public void runOpMode() {
        servo = hardwareMap.servo.get("servo");
    }

}


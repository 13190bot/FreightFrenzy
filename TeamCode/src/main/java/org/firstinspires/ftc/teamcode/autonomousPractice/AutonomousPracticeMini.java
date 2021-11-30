package org.firstinspires.ftc.teamcode.autonomousPractice;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Encoder", group = "Practice")
//@Disabled
public class AutonomousPracticeMini extends LinearOpMode{

    DcMotor frontLeftDc, frontRightDc, backLeftDc, backRightDc;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftDc = hardwareMap.dcMotor.get("front_left_motor");
        frontRightDc = hardwareMap.dcMotor.get("front_right_motor");
        backLeftDc = hardwareMap.dcMotor.get("back_left_motor");
        backRightDc = hardwareMap.dcMotor.get("back_right_motor");

        frontLeftDc.setDirection(DcMotor.Direction.REVERSE);
    }

}

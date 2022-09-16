package org.firstinspires.ftc.teamcode.prateekCode;

import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

//this is a four-wheel drive
//it cannot strafe
@TeleOp(name = "Prateek 4wd")
public class FourWheelDrive extends OpMode {

    private DcMotor lf, lr, rf, rr; //abriviations: first leter

    public void init () {

        lf = hardwareMap.get(DcMotor.class, "leftFront");
        lr = hardwareMap.get(DcMotor.class, "leftRear");
        rf = hardwareMap.get(DcMotor.class, "rightFront");
        rr = hardwareMap.get(DcMotor.class, "rightRear");
        telemetry.addData("hardware", "imitialised");

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData("init ", "finished");
    }

    @Override
    public void loop() {

        lf.setPower(-1*gamepad1.left_stick_y - -1*gamepad1.right_stick_x);
        lr.setPower(-1*gamepad1.left_stick_y - -1*gamepad1.right_stick_x);
        rf.setPower(-1*gamepad1.left_stick_y + -1*gamepad1.right_stick_x);
        rr.setPower(-1*gamepad1.left_stick_y + -1*gamepad1.right_stick_x);


    }
}

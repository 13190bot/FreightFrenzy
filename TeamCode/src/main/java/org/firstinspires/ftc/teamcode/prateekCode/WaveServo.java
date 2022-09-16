package org.firstinspires.ftc.teamcode.prateekCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Parade Bot OpMode 2")
public class WaveServo extends OpMode {

    private DcMotor armMotor;

    public int delay = 0;

    @Override
    public void init() {

        armMotor = hardwareMap.dcMotor.get("hand_motor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition(1);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motor", "initialised");
        telemetry.addData("motor pos", armMotor::getCurrentPosition);
        telemetry.update();
    }

    @Override
    public void start() {
        armMotor.setPower(.1);
    }

    @Override
    public void loop() {
        telemetry.addData("in loop", true);
        telemetry.update();

        if (armMotor.getCurrentPosition() >= Math.abs( armMotor.getTargetPosition() ) && delay == 0 ) {
            armMotor.setTargetPosition(-1 * armMotor.getTargetPosition() );
            delay = 50;
        }

        else if (delay < 0) {
            telemetry.addData("delay", "less than 0");
            delay = 0;
        }

        else {
            delay -- ;
        }

        telemetry.addData("motor pos", armMotor::getCurrentPosition);
        telemetry.addData("motor targPos", armMotor::getTargetPosition);
        telemetry.update();
    }
}

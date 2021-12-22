package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

//this program assumes that the arm starts pointing straight up
//and the servo can rotate 180 degrees with the maximum/minimum pointing straight down
@TeleOp
public class ClawTestThing extends LinearOpMode {
    static final double COUNTS_PER_MOTOR_REV = 1992.6;
    static final double MAX_POSITION = 1.0;
    static final double MIN_POSITION = 0.0;
    static double motorPosition = 180;

    private DcMotor motor;
    double encoderOutput = 0;
    //private Servo servo;

    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "claw_motor");
        //servo = hardwareMap.get(Servo.class, "claw_servo_bottom");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while(opModeIsActive()) {
            encoderOutput = motor.getCurrentPosition();
            telemetry.addData("Encoder Position", encoderOutput);
            telemetry.addData("Motor Power", 0);
            telemetry.update();
            if(gamepad1.b){
                telemetry.addData("Running",true);
                telemetry.update();
            }
            if (gamepad1.left_stick_x<-0.5) {
                moveDcMotor(motorPosition - 45);
            }
            if (gamepad1.left_stick_x > 0.5) {
                moveDcMotor(motorPosition + 45);
            }
        }
    }

    public void moveDcMotor(double angle){
        telemetry.addData("Moving", true);
        telemetry.update();
        double angleChange = angle-motorPosition;
        motorPosition = angle;
        int movementAmount = (int) (angleChange*COUNTS_PER_MOTOR_REV)/360;
        motor.setTargetPosition(movementAmount);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(1);
        while(opModeIsActive() && motor.isBusy()){
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.update();
        }
        motor.setPower(0);
        telemetry.addData("Motor Power", motor.getPower());
        telemetry.update();
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /*
    public void setAngle(double angle){
        double angleChange = angle-motorPosition;
        motorPosition = angle;
        int movementAmount = (int) (angleChange*COUNTS_PER_MOTOR_REV)/360;
        motor.setTargetPosition(movementAmount);
        double servoAngle = 180-(motorPosition-90);
        servo.setPosition(servoAngle/360);
        while(motor.isBusy()){
            sleep(50);
        }
    }
*/
    /* currently useless
    public void resetPosition(){
        servo.setPosition(0);
        sleep(1000);
        motor.setTargetPosition();
        while(motor.isBusy()){
            sleep(50);
        }
    }
    */
}
/*
plan:
-move servo to correct position (0 or 180 degrees)
-find out position of DcMotor
-for now, make it so the servo is always flat, but will be changed later
*/
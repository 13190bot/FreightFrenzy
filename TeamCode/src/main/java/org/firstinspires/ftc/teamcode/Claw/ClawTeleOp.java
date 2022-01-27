package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp
public class ClawTeleOp extends template {
    public TouchSensor limit;
    @Override
    public void runOpMode() {

        initialize();
        limit = hardwareMap.get(TouchSensor.class, "limit");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("rotationPosition", (armRotationMotor.getCurrentPosition()*360)/(537.7*6));
            telemetry.addData("intakeMotorPower", intakeMotor.getPower());
            telemetry.addData("limitSwitch", limit.isPressed());
            telemetry.addData("servoPos", directionServo.getPosition());
            if(limit.isPressed()){
                armRotationMotor.setPower(0);
                armRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                telemetry.addData("ERROR: ", "too far");
            }
            if(gamepad2.y && !isRotationBusy()){
                toTopLevel();
            }
            if(gamepad2.b && !isRotationBusy()){
                toMiddleLevel();
            }
            if(gamepad2.a && !isRotationBusy()){
                toBottomLevel();
            }
            if(gamepad2.x && !isRotationBusy()){
                toPickupPosition();
            }
            if(gamepad2.right_trigger>0.2){
                intakeMotor.setPower(1);
            }
            if(gamepad2.left_trigger>0.2){
                intakeMotor.setPower(-1);
            }
            if(gamepad2.right_trigger<=0.2 && intakeMotor.getPower() > 0){
                intakeMotor.setPower(0);
            }
            if(gamepad2.left_trigger<=0.2 && intakeMotor.getPower() < 0){
                intakeMotor.setPower(0);
            }
            double x = gamepad2.left_stick_x;
            if((x>0.2) && !isRotationBusy()){
                manual = true;
                if(x>0.5){
                    x=0.5;
                }
                armRotationMotor.setPower(x);
            }else if((x<-0.2)&&!isRotationBusy() && !limit.isPressed()){
                manual = true;
                if(x<-0.5){
                    x=-0.5;
                }
                armRotationMotor.setPower(x);
            }else{
                manual = false;
                if(!isRotationBusy()){
                    armRotationMotor.setPower(0);
                }
            }
            if(gamepad2.left_bumper){
                armRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            double servoInput = gamepad2.right_stick_x;
            if(servoInput>0.2){
                directionServo.setPosition(directionServo.getPosition()+0.01);
            }
            if(servoInput<-0.2){
                directionServo.setPosition(directionServo.getPosition()-0.01);
            }
            telemetry.update();
        }
    }

}


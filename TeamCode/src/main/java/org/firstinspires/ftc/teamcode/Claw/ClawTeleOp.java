package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ClawTeleOp extends template {
    @Override
    public void runOpMode() {

        initialize();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("rotationPosition", armRotationMotor.getCurrentPosition());
            telemetry.addData("intakeMotorPower", intakeMotor.getPower());
            if(isRotationTooFar()){
                telemetry.addData("Status: ", "too far");
            }
            if(gamepad1.y && !isRotationBusy()){
                toTopLevel();
            }
            if(gamepad1.b && !isRotationBusy()){
                toMiddleLevel();
            }
            if(gamepad1.a && !isRotationBusy()){
                toBottomLevel();
            }
            if(gamepad1.x && !isRotationBusy()){
                toPickupPosition();
            }
            if(gamepad1.right_trigger>0.2){
                intakeMotor.setPower(0.5);
            }
            if(gamepad1.left_trigger>0.2){
                intakeMotor.setPower(-0.5);
            }
            if(gamepad1.right_trigger<=0.2 && intakeMotor.getPower() > 0){
                intakeMotor.setPower(0);
            }
            if(gamepad1.left_trigger<=0.2 && intakeMotor.getPower() < 0){
                intakeMotor.setPower(0);
            }
            telemetry.update();
        }
    }

}


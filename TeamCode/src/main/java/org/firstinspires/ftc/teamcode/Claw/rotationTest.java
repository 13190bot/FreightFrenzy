package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public abstract class rotationTest extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 1992.6;

    public DcMotor armRotationMotor;

    @Override
    public void runOpMode() {
        initialize();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("rotationPosition", armRotationMotor.getCurrentPosition());
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
            telemetry.update();
        }
    }

    public void initialize(){
        //TODO flip the rotation directions if necessary
        telemetry.addData("Status", "Initializing Arm Motors");
        telemetry.update();
        armRotationMotor = hardwareMap.get(DcMotor.class, "rotationMotor");
        //TODO update starting position to correct position
        armRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public boolean isRotationBusy(){
        if(armRotationMotor.isBusy()){
            return true;
        }else{
            if(armRotationMotor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER ) {
                armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }if(armRotationMotor.getPower() != 0){
                armRotationMotor.setPower(0);
            }
            return false;
        }
    }
    public void toPickupPosition(){
        armRotationMotor.setTargetPosition(0);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
    }
    public void toBottomLevel(){
        double targetAngle = 170;
        double amountExtended = 0.1;
        moveThings(targetAngle, amountExtended);
    }
    public void toMiddleLevel(){
        double targetAngle = 145;
        double amountExtended = 0.4;
        moveThings(targetAngle, amountExtended);
    }
    public void toTopLevel(){
        double targetAngle = 120;
        double amountExtended = 1;
        moveThings(targetAngle, amountExtended);
    }
    public boolean isRotationTooFar(){
        if(armRotationMotor.getCurrentPosition() > 180 && armRotationMotor.getPower()>0){
            armRotationMotor.setPower(0);
            return true;
        }else if(armRotationMotor.getCurrentPosition() < 0 && armRotationMotor.getPower() < 0){
            armRotationMotor.setPower(0);
            return true;
        }else return false;
    }
    private void moveThings(double targetAngle, double extensionAmount){
        int targetPosition = (int) (targetAngle*COUNTS_PER_MOTOR_REV)/360;
        armRotationMotor.setTargetPosition(targetPosition);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
    }
}

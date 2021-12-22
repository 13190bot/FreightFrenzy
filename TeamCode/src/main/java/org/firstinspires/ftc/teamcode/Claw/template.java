package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class template extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 288;

    protected DcMotor armRotationMotor;
    protected DcMotor intakeMotor;
    protected DcMotor extensionMotor;

    protected void initialize(){
        //TODO flip the rotation directions is necessary
        telemetry.addData("Status", "Initializing Arm Motors");
        telemetry.update();
        armRotationMotor = hardwareMap.get(DcMotor.class, "arm_rotation_motor");
        armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        extensionMotor = hardwareMap.get(DcMotor.class, "extension_motor");
        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    protected boolean isRotationBusy(){
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
    protected boolean isExtensionBusy(){
        if(extensionMotor.isBusy()){
            return true;
        }else{
            if(extensionMotor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){
                extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }if(extensionMotor.getPower() != 0){
                extensionMotor.setPower(0);
            }
            return false;
        }
    }
    protected void toPickupPosition(){
        //TODO make sure angle 0 is always at this position using STOP_AND_RESET_ENCODER
        armRotationMotor.setTargetPosition(0);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
        //TODO add/change arm extension
    }
    protected void toBottomLevel(){
        //TODO correct the angle
        double targetAngle = 180;
        int targetPosition = (int) (targetAngle*COUNTS_PER_MOTOR_REV)/360;
        armRotationMotor.setTargetPosition(targetPosition);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
        //TODO add/change how much the arm extends
    }
    protected void toMiddleLevel(){
        //TODO correct the angle
        double targetAngle = 205;
        int targetPosition = (int) (targetAngle*COUNTS_PER_MOTOR_REV)/360;
        armRotationMotor.setTargetPosition(targetPosition);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
        //TODO add/change how much the arm extends
    }
    protected void toTopLevel(){
        //TODO correct the angle
        double targetAngle = 230;
        int targetPosition = (int) (targetAngle*COUNTS_PER_MOTOR_REV)/360;
        armRotationMotor.setTargetPosition(targetPosition);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotationMotor.setPower(0.5);
        //TODO add/change how much the arm extends
    }
    protected boolean isRotationTooFar(){
        //TODO change boundary values
        if(armRotationMotor.getCurrentPosition() >= 250 || armRotationMotor.getCurrentPosition() <= 0){
            armRotationMotor.setPower(0);
            return true;
        }else return false;
    }
    protected void outPut(){
        intakeMotor.setPower(0.8);
    }
    protected void intake(){
        intakeMotor.setPower(-0.8);
    }
    protected void stopIntake(){
        intakeMotor.setPower(0);
    }
    protected void spinDucks(double time){
        //TODO if this is needed, add it
    }

}

//pickup button
//arm motor limiting
//preset heights for each level
//extending
//spin one way to intake something - continuous
//try and do something like it can work no matter the start position?


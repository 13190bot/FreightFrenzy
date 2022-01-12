package org.firstinspires.ftc.teamcode.Claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class template extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 1992.6;

    public DcMotor armRotationMotor;
    public DcMotor intakeMotor;
    public Servo directionServo;

    public void initialize(){
        //TODO flip the rotation directions if necessary
        telemetry.addData("Status", "Initializing Arm Motors");
        telemetry.update();
        armRotationMotor = hardwareMap.get(DcMotor.class, "rotationMotor");
        //TODO update starting position to correct position
        armRotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        directionServo = hardwareMap.get(Servo.class, "directionServo");
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
        directionServo.setPosition(1);
    }
    public void toBottomLevel(){
        double targetAngle = 170;
        double amountExtended = 0.1;
        moveThings(targetAngle, amountExtended);
        directionServo.setPosition(1);
    }
    public void toMiddleLevel(){
        double targetAngle = 145;
        double amountExtended = 0.4;
        moveThings(targetAngle, amountExtended);
        directionServo.setPosition(0.5);
    }
    public void toTopLevel(){
        double targetAngle = 120;
        double amountExtended = 1;
        moveThings(targetAngle, amountExtended);
        directionServo.setPosition(0);
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

//pickup button
//arm motor limiting
//preset heights for each level
//extending
//spin one way to intake something - continuous
//try and do something like it can work no matter the start position?


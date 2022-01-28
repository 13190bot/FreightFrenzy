package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.teamcode.Claw.template;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

@TeleOp
public class FinalTeleOp extends template {

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor;
    public TouchSensor limit;

    public void runOpMode() {
        frontLeftMotor = hardwareMap.dcMotor.get("leftFront");
        rearLeftMotor = hardwareMap.dcMotor.get("leftRear");
        frontRightMotor = hardwareMap.dcMotor.get("rightFront");
        rearRightMotor = hardwareMap.dcMotor.get("rightRear");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        limit = hardwareMap.touchSensor.get("limit");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        initialize();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double horizontal = -gamepad1.left_stick_x;
            double vertical = -gamepad1.left_stick_y;
            double angle = -gamepad1.right_stick_x;

            drive.setWeightedDrivePower(new Pose2d(vertical, horizontal, angle)); // in roadrunner x is vertical and y is horizontal
            drive.update();

            if (this.gamepad1.left_bumper) {
                duckMotor.setPower(-0.5);
            } else {
                duckMotor.setPower(0);
            }

            telemetry.addData("FrontLeftPower", frontLeftMotor.getPower());
            telemetry.addData("BackLeftPower", rearLeftMotor.getPower());
            telemetry.addData("FrontRightPower", frontRightMotor.getPower());
            telemetry.addData("BackRightPower", rearRightMotor.getPower());
            telemetry.update();

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

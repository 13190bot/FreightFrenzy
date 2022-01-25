package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Claw.template;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

@TeleOp
public class FinalTeleOp extends template {

    private DcMotor frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, duckMotor, armRotationMotor, intakeMotor;
    public Servo directionServo;

    public void runOpMode() {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        rearLeftMotor = hardwareMap.dcMotor.get("rearLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        rearRightMotor = hardwareMap.dcMotor.get("rearRight");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");

        armRotationMotor = hardwareMap.dcMotor.get("armRotation");
        intakeMotor = hardwareMap.dcMotor.get("intake");
        directionServo = hardwareMap.servo.get("directionServo");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        initialize();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double horizontal = gamepad1.left_stick_x;
            double vertical = gamepad1.left_stick_y;
            double angle = gamepad1.right_stick_x;

            drive.setWeightedDrivePower(new Pose2d(vertical, horizontal, angle)); // in roadrunner x is vertical and y is horizontal
            drive.update();

            if (this.gamepad1.left_bumper) {
                
                duckMotor.setPower(0.5);
            }

            telemetry.addData("FrontLeftPower", frontLeftMotor.getPower());
            telemetry.addData("BackLeftPower", rearLeftMotor.getPower());
            telemetry.addData("FrontRightPower", frontRightMotor.getPower());
            telemetry.addData("BackRightPower", rearRightMotor.getPower());
            telemetry.update();

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

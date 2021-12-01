package org.firstinspires.ftc.teamcode.Melvin;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class MechanumWheel extends LinearOpMode {

    public void runOpMode() {
    DcMotor frontLeftMotor = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor backLeftMotor = hardwareMap.dcMotor.get(("motorBackLeft"));
    DcMotor frontRightMotor = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor backRightMotor = hardwareMap.dcMotor.get(("motorBackRight"));

    frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    double y = -gamepad1.left_stick_y;
    double x = gamepad1.left_stick_x;
    double rx = gamepad1.right_stick_x;

    double frontLeftPower = (y + x + rx);
    double backLeftPower = (y - x + rx);
    double frontRightPower = (y - x - rx);
    double backRightPower = (y + x - rx);

    frontLeftMotor.setPower(frontLeftPower);
    backLeftMotor.setPower(backLeftPower);
    frontRightMotor.setPower(frontRightPower);
    backRightMotor.setPower(backRightPower);
}
}

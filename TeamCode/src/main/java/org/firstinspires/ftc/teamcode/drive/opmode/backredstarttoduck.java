package org.firstinspires.ftc.teamcode.drive.opmode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "backredstarttoduck")
public class backredstarttoduck extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {



        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));

        SampleMecanumDrive drive =new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(startPose);

        Trajectory moveForward = drive.trajectoryBuilder(new Pose2d(0, 0))
                .lineToLinearHeading(new Pose2d(30, 0, Math.toRadians(0)))
                .build();

        Trajectory toParking = drive.trajectoryBuilder(moveForward.end())
                .lineToLinearHeading(new Pose2d(25, 30, Math.toRadians(90)))
                .build();

        Trajectory moveBack = drive.trajectoryBuilder(toParking.end())
                .forward(10)
                .build();


        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(moveForward);
        drive.turn(Math.toRadians(140));
        drive.followTrajectory(toParking);
        drive.turn(Math.toRadians(385));
        drive.followTrajectory(moveBack);


    }
}

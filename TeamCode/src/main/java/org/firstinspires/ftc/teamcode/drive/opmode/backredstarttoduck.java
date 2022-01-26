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

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);



        drive.setPoseEstimate(startPose);

        Trajectory moveForward = drive.trajectoryBuilder(new Pose2d(0, 0))
                .lineToLinearHeading(new Pose2d(40, -19, Math.toRadians(0)))
                .build();

        Trajectory toDuck = drive.trajectoryBuilder(new Pose2d(0, 0))

                .lineToLinearHeading(new Pose2d(7, 25,Math.toRadians(275.89)))

                //.splineTo(new Vector2d(0,6.53))
                .build();

        Trajectory back3 = drive.trajectoryBuilder(toDuck.end())
                .back(3)
                .build();

        Trajectory toParking = drive.trajectoryBuilder(back3.end())
                .lineToLinearHeading(new Pose2d(35, 29, Math.toRadians(22)))
                .build();



        waitForStart();



        if(isStopRequested()) return;

        drive.followTrajectory(moveForward);
        drive.followTrajectory(toDuck);
        drive.followTrajectory(back3);
        drive.followTrajectory(toParking);


    }
}

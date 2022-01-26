package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.internal.opengl.models.Geometry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="BackRedStartToDuck_New")
public class BackRedStartToDuck_New extends LinearOpMode {

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

        Trajectory moveBack = drive.trajectoryBuilder(new Pose2d(0, 20, Math.toRadians(0)))
                .back(10)
                .build();

        Trajectory forwardStuff = drive.trajectoryBuilder(new Pose2d(0, 27, Math.toRadians(0)))
                .forward(33)
                .build();


        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(moveForward);
        drive.turn(Math.toRadians(135));
        drive.followTrajectory(toParking);
        drive.turn(Math.toRadians(-135));
        drive.followTrajectory(moveBack);
        //Servo causing duck to spin
        drive.followTrajectory(forwardStuff);



    }
}

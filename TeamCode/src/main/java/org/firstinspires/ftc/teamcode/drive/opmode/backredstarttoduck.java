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

        Trajectory moveforward = drive.trajectoryBuilder(new Pose2d(0, 0))
                .back(30)
                .build();

        Trajectory traj1 = drive.trajectoryBuilder(moveforward.end())

                .lineToLinearHeading(new Pose2d(10, 50,Math.toRadians(90)))



                .build();

        Trajectory back3 = drive.trajectoryBuilder(traj1.end())
                .back(10)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .splineTo(new Vector2d(46.65, -48.21), Math.toRadians(0))
                .build();

        Trajectory toParking = drive.trajectoryBuilder(back3.end())
                .lineToLinearHeading(new Pose2d(51, 38, Math.toRadians(0.1)))
                .build();



        waitForStart();



        if(isStopRequested()) return;

        drive.followTrajectory(moveforward);
        drive.followTrajectory(traj1);
        //drive.followTrajectory(back3);
        drive.followTrajectory(toParking);
        //  drive.followTrajectory(traj2);

    }
}

package org.firstinspires.ftc.teamcode.freightfrenzy;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;


@Autonomous(name = "StartToDuckAutonomous")
public class StartToDuckAutonomous extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {





        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())

                .lineToLinearHeading(new Pose2d(3.118, 18.61,Math.toRadians(7.55)))
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .splineTo(new Vector2d(0, 0), Math.toRadians(212.812))
                .build();



        waitForStart();


        if(isStopRequested()) return;

        drive.followTrajectory(traj1);
        //drive.followTrajectory(traj2);

    }
}
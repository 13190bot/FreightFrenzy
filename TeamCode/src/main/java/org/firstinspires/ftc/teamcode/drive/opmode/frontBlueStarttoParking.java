package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.internal.opengl.models.Geometry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="frontBlueStarttoParking")
public class frontBlueStarttoParking extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(startPose);

        Trajectory moveForward = drive.trajectoryBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .forward(10)
                .build();

        Trajectory toParking = drive.trajectoryBuilder(moveForward.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
                .forward(70)
                .build();


        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(moveForward);
        drive.turn(Math.toRadians(125));
        drive.followTrajectory(toParking);



    }
}

package org.firstinspires.ftc.teamcode.freightfrenzy;
/*
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@Autonomous(name = "")
public class AutonomousDriveStrafeTest extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {





        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-35.867, -59.425, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())

                .lineToLinearHeading(new Pose2d(-53.931, -58.403,Math.toRadians(212.812)))
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

 */
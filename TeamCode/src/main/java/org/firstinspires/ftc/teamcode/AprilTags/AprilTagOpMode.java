package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous(name = "AprilTag", group = "AprilTag")
public class AprilTagOpMode extends LinearOpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    DetectAprilTag detectAprilTag;
    DriveToTag driveToTag;
    int target = -1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        target = 6;
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        detectAprilTag = new DetectAprilTag(hardwareMap);
        driveToTag = new DriveToTag(mechanicalDriveBase, hardwareMap);

        waitForStart();
        start();

        while (opModeIsActive())
        {
//            detectAprilTag.detectTags(telemetry);
//            telemetry.addData("get One", detectAprilTag.getDetectionID());
            driveToTag.findTag(12,5, 5, telemetry);
        }

        stop();
    }

    class ThreadDetector extends Thread
    {
        public ThreadDetector()
        {
            while (opModeIsActive())
            {
                detectAprilTag.detectTags(telemetry);
                telemetry.addData("Thread get", detectAprilTag.getDetectionID());
            }
        }
    }
}










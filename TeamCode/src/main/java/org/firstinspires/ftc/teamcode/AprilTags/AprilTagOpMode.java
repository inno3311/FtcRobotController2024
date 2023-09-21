package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@Autonomous(name = "AprilTag", group = "AprilTag")
public class AprilTagOpMode extends LinearOpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    DetectAprilTag detectAprilTag;
    AprilTagMaster aprilTagMaster;
    int target = -1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        target = 6;
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        aprilTagMaster = new AprilTagMaster(mechanicalDriveBase, hardwareMap);

        waitForStart();
        start();

        while (opModeIsActive())
        {
            aprilTagMaster.detectTags(telemetry);
            aprilTagMaster.findTag(12,5, 5, telemetry);
        }

        stop();
    }

    class ThreadDetector extends Thread
    {
        public ThreadDetector()
        {
            while (opModeIsActive())
            {
                aprilTagMaster.detectTags(telemetry);
                telemetry.addData("Thread get", detectAprilTag.getDetectionID());
            }
        }
    }
}










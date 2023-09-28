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
        target = 2;
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        aprilTagMaster = new AprilTagMaster(mechanicalDriveBase, hardwareMap);

        start();
        waitForStart();


        while (opModeIsActive())
        {
            if (aprilTagMaster.aprilTagDetected())
            {
                aprilTagMaster.findTag(12, 5, 2, telemetry);
                while (aprilTagMaster.foundTag()) {aprilTagMaster.findTag(12, 5, 2, telemetry);}
                telemetry.addData("Target reached", "");
                telemetry.update();
                break;
            }
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










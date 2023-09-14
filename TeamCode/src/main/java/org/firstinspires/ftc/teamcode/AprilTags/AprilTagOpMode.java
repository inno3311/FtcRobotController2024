package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

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
        target = 1;
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        detectAprilTag = new DetectAprilTag(hardwareMap);
//        driveToTag = new DriveToTag(hardwareMap, telemetry, mechanicalDriveBase);

        waitForStart();

        while (opModeIsActive())
        {
            detectAprilTag.detectTags(telemetry);
            if (detectAprilTag.getDetectionID() == target)
            {
//                break;
            }

//            driveToTag.executeToTag(telemetry,1);
        }

        detectAprilTag.closeAprilTags();
    }
}

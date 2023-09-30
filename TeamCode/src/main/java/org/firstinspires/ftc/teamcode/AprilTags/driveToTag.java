package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

//Further tuning in AprilTagMaster.java to come, I sure of it
public class driveToTag
{

    /**
     * **
     * @param time The amount of time you want the robot to drive to tag
     * @param target The aprilTag you want to drive to
     * @param hardwareMap
     * @param telemetry
     * @param elapsedTime
     * @param mechanicalDriveBase
     * @param aprilTagMaster
     */
    public void driveToTag(int time, int target, HardwareMap hardwareMap, Telemetry telemetry, ElapsedTime elapsedTime, MechanicalDriveBase mechanicalDriveBase, AprilTagMaster aprilTagMaster)
    {
        if (aprilTagMaster.aprilTagDetected())
        {
            elapsedTime.startTime();
            while (elapsedTime.seconds() < time)
            {
                telemetry.addData("Time = ",elapsedTime.seconds() + " seconds");
                aprilTagMaster.findTag(11, 0, 2, telemetry);
            }
        }
    }

}










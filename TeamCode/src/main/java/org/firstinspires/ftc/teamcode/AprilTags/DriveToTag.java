package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

//Further tuning in AprilTagMaster.java to come, I sure of it
public class DriveToTag
{
    HardwareMap hardwareMap;
    Telemetry telemetry;
    ElapsedTime elapsedTime;
    MechanicalDriveBase mechanicalDriveBase;
    AprilTagMaster aprilTagMaster;

    public DriveToTag(HardwareMap hardwareMap, Telemetry telemetry, ElapsedTime elapsedTime, MechanicalDriveBase mechanicalDriveBase, AprilTagMaster aprilTagMaster)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.elapsedTime = elapsedTime;
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.aprilTagMaster = aprilTagMaster;
    }

    /**
     * @param time The amount of time you want the robot to drive to tag
     * @param target The aprilTag you want to drive to
     * **/
    public void drive(int time, int target, double range, double yaw)
    {
        elapsedTime.reset();
        if (aprilTagMaster.aprilTagDetected())
        {
            elapsedTime.startTime();
            while (elapsedTime.seconds() < time)
            {
                telemetry.addData("Time = ",elapsedTime.seconds() + " seconds");
                aprilTagMaster.findTag(range, yaw, target, telemetry);
            }
        }
    }

}










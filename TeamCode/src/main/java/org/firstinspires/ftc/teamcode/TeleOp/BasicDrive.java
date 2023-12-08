package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Basic drive", group = "drive")
public class BasicDrive extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    AprilTagProcessor aprilTagProcessor;
    AprilTagMaster aprilTagMaster;
    DriveToTag driveToTag;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        aprilTagMaster = new AprilTagMaster(mechanicalDriveBase, aprilTagProcessor);
        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), aprilTagMaster);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        driveToTag.targetLocator(gamepad1);
        mechanicalDriveBase.gamepadController(gamepad1);
        mechanicalDriveBase.driveBaseTelemetry(telemetry);
    }
}

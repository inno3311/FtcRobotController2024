package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.IntakeChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOpLord extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    DriveToTag driveToTag;
    LinerSlideChild linerSlideChild;
    TransferRight transferRight;
    TransferLeft transferleft;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(mechanicalDriveBase, hardwareMap));
        linerSlideChild = new LinerSlideChild(this);
        transferRight = new TransferRight(this);
        transferleft = new TransferLeft(this);
        heightChild = new HeightChild(this);
        intakeChild = new IntakeChild(this);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
        driveToTag.targetLocator(gamepad1);
        transferRight.transferDrive();
        transferleft.transferDrive();
        linerSlideChild.linerSlideDrive();
        heightChild.heightDrive();
        intakeChild.IntakeDrive();
    }

}

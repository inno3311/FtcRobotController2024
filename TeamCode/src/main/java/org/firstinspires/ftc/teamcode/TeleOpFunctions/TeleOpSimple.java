package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "Simple TeleOp", group = "TeleOp")
public class TeleOpSimple extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    LinerSlideChild linerSlideChild;
    TransferRight transferRight;
    TransferLeft transferLeft;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        linerSlideChild = new LinerSlideChild(this);
        transferRight = new TransferRight(this);
        transferLeft = new TransferLeft(this);
        heightChild = new HeightChild(this);
        intakeChild = new IntakeChild(this);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
        linerSlideChild.linerSlideDriveSimple();
        transferRight.transferDrive();
        transferLeft.transferDrive();
        heightChild.heightDriveSimple();
        intakeChild.IntakeDrive();
    }
}

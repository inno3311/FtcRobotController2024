package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "Simple TeleOp", group = "TeleOp")
public class TeleOpSimple extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    LinerSlideChild linerSlideChild;
    TransferChild transferChild;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        linerSlideChild = new LinerSlideChild(this);
        transferChild = new TransferChild(this);
        heightChild = new HeightChild(this);
        intakeChild = new IntakeChild(this);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
        linerSlideChild.linerSlideDriveSimple();
        transferChild.transferDrive();
        heightChild.heightDriveSimple();
        intakeChild.IntakeDrive();
    }
}

package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOpFunctionsInheritanceTest extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    LinerSlideChild linerSlideChild;
    TransferRight transferRight;
    TransferLeft transferleft;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
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
        transferRight.transferDrive();
        transferleft.transferDrive();
        linerSlideChild.linerSlideDrive();
        heightChild.heightDrive();
        intakeChild.IntakeDrive();
    }

}

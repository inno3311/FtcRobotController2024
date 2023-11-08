package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "TeleOpFunctionsInheritanceTest", group = "TeleOp")
public class TeleOpFunctionsInheritanceTest extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
//    LinerSlideChild linerSlideChild;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
//        linerSlideChild = new LinerSlideChild(this);
        heightChild = new HeightChild(this);
        intakeChild = new IntakeChild(this);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
//        linerSlideChild.linerSlideDrive();
        heightChild.heightDrive();
        intakeChild.IntakeDrive();
    }

}
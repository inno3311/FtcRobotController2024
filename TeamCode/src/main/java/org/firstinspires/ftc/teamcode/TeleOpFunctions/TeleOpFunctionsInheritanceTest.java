package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

@TeleOp(name = "TeleOpFunctionsInheritanceTest", group = "TeleOp")
public class TeleOpFunctionsInheritanceTest extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
//    MotorControl motorControl;
    LinerSlideChild linerSlideChild;
    HeightChild heightChild;
    IntakeChild intakeChild;

    @Override
    public void init()
    {
//        /*motorControl = */new MotorControl(hardwareMap, telemetry, gamepad1, gamepad2);
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        linerSlideChild = new LinerSlideChild();
        heightChild = new HeightChild();
        intakeChild = new IntakeChild();
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
        linerSlideChild.linerSlideDrive();
        heightChild.heightDrive();
        intakeChild.IntakeDrive();
    }

}

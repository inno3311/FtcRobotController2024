package org.firstinspires.ftc.teamcode.TeleOpFunctions.OldCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOpFunctions.OldCode.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.OldCode.LinerSlide;

@TeleOp(name = "TeleOp Comp base", group = "TeleOp")
public class TeleOpFuctionsTestClass extends OpMode
{
//    MechanicalDriveBase mechanicalDriveBase;
    LinerSlide linerSlide;
    Intake intake;

    @Override
    public void init()
    {
//        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        linerSlide = new LinerSlide(hardwareMap, telemetry, gamepad2);
        intake = new Intake(hardwareMap, telemetry, gamepad2);
    }

    @Override
    public void loop()
    {
//        mechanicalDriveBase.gamepadController(gamepad1);
        linerSlide.driveSlide();
        intake.intakeControl();
    }
}

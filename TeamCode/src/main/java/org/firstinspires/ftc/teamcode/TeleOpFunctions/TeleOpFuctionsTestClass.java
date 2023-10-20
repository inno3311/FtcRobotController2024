package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "Hope Test Teleop", group = "TeleOp")
public class TeleOpFuctionsTestClass extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    Intake intake;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        intake = new Intake(hardwareMap, telemetry, gamepad1);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBase.gamepadController(gamepad1);
        intake.intakeControl();
    }
}

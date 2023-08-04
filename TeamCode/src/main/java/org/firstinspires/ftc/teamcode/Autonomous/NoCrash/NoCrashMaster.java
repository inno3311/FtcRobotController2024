package org.firstinspires.ftc.teamcode.Autonomous.NoCrash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBase;

@Autonomous(name = "No Crash", group = "No Crash")
public class NoCrashMaster extends OpMode
{
    MecanumDriveBase mecanumDriveBase;

    @Override
    public void init()
    {
        mecanumDriveBase = new MecanumDriveBase(hardwareMap);
    }

    @Override
    public void loop()
    {
            mecanumDriveBase.gamepadController(gamepad1);
    }
}

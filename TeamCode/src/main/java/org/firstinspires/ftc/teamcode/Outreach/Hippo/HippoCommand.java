package org.firstinspires.ftc.teamcode.Outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

@TeleOp(name = "Hippo Outreach", group = "outreach")
public class HippoCommand extends OpMode
{
    MechanicalDriveBase drive;
    HippoTrigger hippoTrigger;
    HippoShooter hippoShooter;
    HippoIntake hippoIntake;
    @Override
    public void init()
    {
        drive = new MechanicalDriveBase(hardwareMap);
        hippoIntake = new HippoIntake(this);
        hippoShooter = new HippoShooter(this);
        hippoTrigger = new HippoTrigger(this);
    }

    @Override
    public void loop()
    {
        drive.gamepadController(gamepad1);
        hippoTrigger.driveTrigger(gamepad1.y);
        hippoShooter.simpleDrive(1, gamepad1.y);
        hippoIntake.simpleDrive(1, gamepad1.a, gamepad1.right_bumper);
    }

}

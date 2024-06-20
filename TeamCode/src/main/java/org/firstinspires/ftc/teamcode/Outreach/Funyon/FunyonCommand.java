package org.firstinspires.ftc.teamcode.Outreach.Funyon;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Outreach.MechanicalDriveOutreach;

@TeleOp(name = "Funyon Outreach", group = "Outreach")
public class FunyonCommand extends OpMode
{
    MechanicalDriveOutreach mechanicalDriveOutreach;
    FunyonShooter shooter;

    @Override
    public void init()
    {
        mechanicalDriveOutreach = new MechanicalDriveOutreach(hardwareMap);
        shooter = new FunyonShooter(hardwareMap);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mechanicalDriveOutreach.gamepadController(gamepad1);
        mechanicalDriveOutreach.driveBaseTelemetry(telemetry);
        shooter.controlMethod(gamepad1);
    }
}

/** Below is config and code notes **/

/** Drive base expansion hub**/
// lf = 3, REVERSE
// rf = 2, FORWARD
// lb = 1, REVERSE
// rb = 0, FORWARD

/** Firing and intake control hub**/
//  intakeWheel =  0  |||  direction forward
//  intakeMill =  1  |||  direction Reversed
//  shooter = 3 |||
//  servo = 5 (i think) ||| presets should be set

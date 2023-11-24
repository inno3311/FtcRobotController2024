package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class DroneLauncher extends ServoControl
{

    public DroneLauncher(OpMode opMode)
    {
        super("Launcher", 0, 1, opMode);
    }

    public  void launcherControl()
    {
        super.driveServo(1, gamepad1.left_bumper);
    }
}

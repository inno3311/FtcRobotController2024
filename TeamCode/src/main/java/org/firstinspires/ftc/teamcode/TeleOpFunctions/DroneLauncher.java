package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class DroneLauncher extends ServoControl
{

    public DroneLauncher(OpMode opMode)
    {
        super("launcher", 0, 1, opMode);
    }

    public  void launcherControl()
    {
        super.driveServo(1, gamepad1.dpad_left && gamepad1.b);
        super.driveServo(0, gamepad1.x);
    }
}

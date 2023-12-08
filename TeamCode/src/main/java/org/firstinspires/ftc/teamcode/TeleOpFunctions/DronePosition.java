package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class DronePosition extends ServoControl
{

    public DronePosition(OpMode opMode)
    {
        super("position", 0, 1, opMode);
    }

    public void PositionControl()
    {
        super.driveServo(1, gamepad1.dpad_up);
        super.driveServo(0.6, gamepad1.dpad_down);
    }
}

package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class DronePosition extends ServoControl
{

    public DronePosition(OpMode opMode)
    {
        super("angel", 0, 1, opMode);
    }

    public void PositionControl()
    {
        super.driveServo(1, gamepad1.a);
        super.driveServo(0.5, !gamepad1.a);
    }
}

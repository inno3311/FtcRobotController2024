package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class TransferLeft extends ServoControl
{

    public TransferLeft(OpMode opMode)
    {
        super("transferLeft", 0, 0, opMode);
    }

    public void transferDrive()
    {
        managePosition();
        telemetry();
    }

    private void managePosition()
    {
        driveServo(1, gamepad2.a);
        driveServo(0, gamepad2.x);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

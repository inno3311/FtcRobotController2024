package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

// Expansion hub servo 2 "transferRight"
public class TransferRight extends ServoControl
{

    public TransferRight(OpMode opMode)
    {
        super("transferRight", 0, 0, opMode);
    }

    public void transferDrive()
    {
        managePosition();
        telemetry();
    }

    private void managePosition()
    {
        driveServo(0, gamepad2.y);
        driveServo(1, gamepad2.a);
    }

    public void autonomousControl(boolean up)
    {
        if (up)
        {
            driveServo(0);
        }
        else
        {
            driveServo(1);
        }
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

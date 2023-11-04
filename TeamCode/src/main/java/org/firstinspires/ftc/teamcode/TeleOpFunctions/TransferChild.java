package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class TransferChild extends ServoControl
{
    private enum Position
    {
        deliver,
        pickup
    }
    Position position;

    private double deliverPosition = 0; // TODO needs assigned
    private double pickupPosition = 0;


    public TransferChild(OpMode opMode)
    {
        super("transfer", Integer.MIN_VALUE, Integer.MAX_VALUE, opMode);
        position = Position.deliver;
    }

    public void transferDrive()
    {
        managePosition();
        telemetry();
    }

    private void managePosition()
    {
        if (gamepad2.right_bumper)
        {
            if (position == Position.pickup)
            {
                position = Position.deliver;
            }
            else
            {
                position = Position.pickup;
            }
        }
        if (position == Position.pickup)
        {
            driveServo(pickupPosition);
        }
        else if (position == Position.deliver)
        {
            driveServo(deliverPosition);
        }

    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

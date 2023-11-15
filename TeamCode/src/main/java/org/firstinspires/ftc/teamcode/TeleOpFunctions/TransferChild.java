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

    private double deliverPosition = 1; // TODO needs assigned
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
        driveServo(1, gamepad2.a);
        driveServo(0, gamepad2.x);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

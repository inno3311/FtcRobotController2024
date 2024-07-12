package org.firstinspires.ftc.teamcode.Reference;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//s3
@TeleOp(name = "Drive", group = "learn")
public class ReferenceTeleOp extends OpMode
{
    ReferenceDrive referenceDrive;
    @Override
    public void init()
    {
        referenceDrive = new ReferenceDrive(this, false);
    }

    @Override
    public void loop()
    {
        referenceDrive.mecunumDrive(gamepad1);
        //e3
        //s4 configure and run robot e4
        //s5 go to DriveBase and add tankDrive e5
        //s6
        referenceDrive.tankDrive(gamepad1);
        //e6
    }

}

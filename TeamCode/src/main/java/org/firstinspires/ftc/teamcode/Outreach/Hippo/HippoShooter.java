package org.firstinspires.ftc.teamcode.Outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HippoShooter extends MotorControl
{

    public HippoShooter(OpMode opMode)
    {
        super("shooter", true, false, opMode);

    }

    @Override
    protected void simpleDrive(double speed, boolean argument)
    {
        super.simpleDrive(speed, argument);
    }
}

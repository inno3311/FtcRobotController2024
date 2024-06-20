package org.firstinspires.ftc.teamcode.Outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HippoShooter extends MotorControl
{
    // + speed is shooting
    public HippoShooter(OpMode opMode)
    {
        super("shooter", true, false, opMode);
    }

    @Override
    protected void run(double speed)
    {
        super.run(speed);
    }
}

package org.firstinspires.ftc.teamcode.Outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class HippoTrigger extends ServoControl
{
    ElapsedTime time;
    double flag = 0;

    public HippoTrigger(OpMode opMode)
    {
        super("trigger", 0, 1, opMode);
        time = new ElapsedTime();
        time.startTime();
    }

    protected void driveTrigger(boolean argument)
    {
        if (argument && flag + 3 > time.seconds())
        {
            flag = time.seconds();
        }

        if (flag + 2 < time.seconds())
        {
            driveServo(0);
        }
        else if (flag + 3 < time.seconds())
        {
            driveServo(0.8);
        }
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}

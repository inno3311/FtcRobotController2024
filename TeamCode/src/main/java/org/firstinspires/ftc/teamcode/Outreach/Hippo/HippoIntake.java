package org.firstinspires.ftc.teamcode.Outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HippoIntake extends MotorControl
{

    public HippoIntake(OpMode opMode)
    {
        super("intake", true, false, opMode);
    }



    @Override
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        super.simpleDrive(speed, argument1, argument2);
    }
}

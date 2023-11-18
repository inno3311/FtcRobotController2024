package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class LinerSlideChild extends MotorControl
{
    private final int resetPosition = -10;
    private final int upperPosition = -1000;

    private final int lowerBounds = 0;
    private final int upperBounds = -1700;

    //Constructor calls parent constructor using hardcoded input
    public LinerSlideChild(OpMode opMode)
    {
        super("slide", true, true, opMode);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void linerSlideDrive()
    {
        this.analogControl();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(0.7, gamepad2.right_stick_y, lowerBounds, upperBounds, true);
    }

    public void linerSlideDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(0.7, gamepad2.right_stick_y);
    }


    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class LinerSlideChild extends MotorControl
{
    private final int resetPosition = -10;
    private final int upperPosition = -1000;

    private final int lowerBounds = -10;
    private final int upperBounds = -1700;

    //Constructor calls parent constructor using hardcoded input
    public LinerSlideChild(OpMode opMode)
    {
        super("slide", true, true, opMode);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void linerSlideDrive()
    {
        this.encoderDrive();
        this.analogControl();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(1, gamepad2.right_stick_y, lowerBounds, upperBounds, false);
    }

    private void encoderDrive()
    {
        encoderControl(resetPosition, 0.5, gamepad2.b);
        encoderControl(upperPosition, 0.5, gamepad2.y);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

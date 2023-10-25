package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class LinerSlideChild extends MotorControl
{
    private final int resetPosition = 50;
    private final int upperPosition = 1000;

    private final int lowerBounds = 5;
    private final int upperBounds = 2700;

    public LinerSlideChild()
    {
        super("Slide", true, true);
    }

    public void linerSlideDrive()
    {
        this.encoderDrive();
        this.analogControl();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(0.5, lowerBounds, upperBounds, gamepad2.right_stick_y);
    }

    private void encoderDrive()
    {
        encoderControl(resetPosition, 0.2, gamepad2.a);
        encoderControl(upperPosition, 0.2, gamepad2.y);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

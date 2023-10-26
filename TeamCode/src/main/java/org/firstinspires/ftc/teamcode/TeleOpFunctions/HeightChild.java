package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HeightChild extends MotorControl
{
    private final int pixelsOne = -1780;
    private final int pixelsTwo = -1610;
    private final int pixelsThree = -1570;
    private final int pixelsFour = -1540;
    private final int pixelsFive = -1520;

    private final int lowerBound = -1800;
    private final int upperBound = 0;

    //Constructor calls parent constructor using hardcoded input
    public HeightChild()
    {
        super("Height", false, true);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void heightDrive()
    {
        this.analogControl();
        this.encoderDrive();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(1, gamepad2.left_stick_y, lowerBound, upperBound);
    }

    private void encoderDrive()
    {
        encoderControl(pixelsOne, 1, gamepad2.left_trigger);
        encoderControl(pixelsTwo, 1, gamepad2.dpad_right);
        encoderControl(pixelsThree, 1, gamepad2.dpad_down);
        encoderControl(pixelsFour, 1, gamepad2.dpad_left);
        encoderControl(pixelsFive, 1, gamepad2.dpad_up);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

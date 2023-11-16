package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HeightChild extends MotorControl
{
    private final int pixelsOne = -2553;
    private final int pixelsTwo = -2370;
    private final int pixelsThree = -2310;
    private final int pixelsFour = -2250;
    private final int pixelsFive = -2190;

    private final int lowerBound = -1230;
    private final int upperBound = -2560;

    //Constructor calls parent constructor using hardcoded input
    public HeightChild(OpMode opmode)
    {
        super("height", false, true, opmode);
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
        super.analogControl(1, gamepad2.left_stick_y, lowerBound, upperBound, false);
    }

    private void encoderDrive()
    {
        encoderControl(pixelsOne, 1, gamepad2.left_trigger);
        encoderControl(pixelsTwo, 1, gamepad2.dpad_left);
        encoderControl(pixelsThree, 1, gamepad2.dpad_down);
        encoderControl(pixelsFour, 1, gamepad2.dpad_right);
        encoderControl(pixelsFive, 1, gamepad2.dpad_up);
    }


    public void heightDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(1, gamepad2.left_stick_y);
    }


    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

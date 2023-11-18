package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HeightChild extends MotorControl
{

    private final int lowerBound = -1230;
    private final int upperBound = -2555;

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
        encoderControl(lowerBound, 1, gamepad2.left_bumper);
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

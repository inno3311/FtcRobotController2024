package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class LinerSlideChild extends MotorControl
{
    private final int resetPosition = -30;
    private final int upperPosition = -1000;

    //Constructor calls parent constructor using hardcoded input
    public LinerSlideChild(OpMode opMode)
    {
        super("slide", true, true, opMode);
    }
    //Calls all methods and then is called in the OpMode loop
    public void linerSlideDrive()
    {
        this.encoderDrive();
        this.analogControl();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(0.5, gamepad2.right_stick_y,true);
    }

    private void encoderDrive()
    {
        encoderControl(upperPosition, 1, gamepad2.left_bumper);
        encoderControl(resetPosition, 0.75, gamepad2.left_trigger);
    }

    @Override
    public void encoderControl(int target, double speed)
    {
        super.encoderControl(target, speed);
    }

    public void linerSlideDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(0.7, gamepad2.right_stick_y, false);
    }


    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

}

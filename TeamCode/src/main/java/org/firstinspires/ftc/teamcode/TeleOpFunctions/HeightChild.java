package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HeightChild extends MotorControl
{

    private final int lowerBound = -1500;
    private final int upperBound = -2500;

    //Constructor calls parent constructor using hardcoded input
    public HeightChild(OpMode opmode)
    {
        super("height", false, true, opmode);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void heightDrive()
    {
        this.resetDrive();
        this.analogControl();
        this.encoderDrive();
        this.telemetry();
    }

    private void analogControl()
    {
        if (!gamepad2.back)
        {
            super.analogControl(1, gamepad2.left_stick_y, false, lowerBound, upperBound);
        }
    }

    private void resetDrive()
    {
        if (Math.abs(gamepad2.left_stick_y) == 0)
        {
            super.simpleDrive(-1, gamepad2.back, false);
        }
    }

    private void encoderDrive()
    {
        encoderControl(lowerBound, 1, gamepad2.dpad_down);
        encoderControl(-2000, 1, gamepad2.dpad_up);
    }

    @Override
    public void encoderControl(int target, double speed)
    {
        super.encoderControl(target, speed);
        telemetry();
    }

    public void heightDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(1, gamepad2.left_stick_y,false);
    }

    @Override
    public void telemetry()
    {
        super.telemetry();
    }

    @Override
    protected int getMotorPosition()
    {
        return super.getMotorPosition();
    }
}

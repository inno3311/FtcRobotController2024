package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class IntakeChild extends MotorControl
{

    //Constructor calls parent constructor using hardcoded input
    public IntakeChild(OpMode opmode)
    {
        super("intake", false, false, opmode);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void IntakeDrive()
    {
        this.simpleDrive();
        this.telemetry();
    }

    private void simpleDrive()
    {
        super.simpleDrive(1, gamepad2.right_bumper, gamepad2.back);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class IntakeChild extends MotorControl
{

    public IntakeChild()
    {
        super("Intake", true, false);
    }

    public void IntakeDrive()
    {
        this.simpleDrive();
        this.telemetry();
    }

    private void simpleDrive()
    {
        super.simpleDrive(0.5, gamepad2.right_bumper, gamepad2.back);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}

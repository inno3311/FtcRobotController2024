package org.firstinspires.ftc.teamcode.FieldCentric;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

public class TurnToHeading
{
    Telemetry telemetry;
    MechanicalDriveBase mechanicalDriveBase;

    public TurnToHeading(Telemetry telemetry, MechanicalDriveBase mechanicalDriveBase)
    {
        this.telemetry = telemetry;
        this.mechanicalDriveBase = mechanicalDriveBase;
    }

    protected double turnToHeading(double x, double y, double currentHeading)
    {
        double targetHeading = Math.atan2(x,y);
        double headingDeviation = (targetHeading - currentHeading) % 360;
        if (headingDeviation > 180)
        {
            headingDeviation = headingDeviation - 360;
        }
        return headingDeviation;
    }

    protected void turnTH(double target, double speed)
    {
        int rightBackPos = mechanicalDriveBase.rb.getCurrentPosition();

        rightBackPos -= (int) target;
        while (mechanicalDriveBase.rb.getCurrentPosition() >= rightBackPos)
        {
            mechanicalDriveBase.driveMotors(0, speed, 0, 1);

            telemetry.addData("target = ", target);
            telemetry.addData("rb pos = ", mechanicalDriveBase.rb.getCurrentPosition());
            telemetry.update();

        }

        mechanicalDriveBase.driveMotors(0, 0, 0, 0);
    }

}

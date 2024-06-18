package org.firstinspires.ftc.teamcode.FieldCentric;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;

public class CentricDrive
{
    MechanicalDriveBase mechanicalDriveBase;

    public CentricDrive(MechanicalDriveBase mechanicalDriveBase)
    {
        this.mechanicalDriveBase = mechanicalDriveBase;
    }

    public void drive(double x,double y, double heading, double turn)
    {
        double targetMagnitude = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        double yMagnitude = targetMagnitude * Math.sin(heading);
        double xMagnitude = targetMagnitude * Math.cos(heading);
        mechanicalDriveBase.driveMotors(x * xMagnitude, turn, y * yMagnitude,1);
    }
}

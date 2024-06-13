package org.firstinspires.ftc.teamcode.FieldCentric;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.PIDController;

public class TurnToHeading
{
    Telemetry telemetry;
    MechanicalDriveBase mechanicalDriveBase;
    IMUControl imu;

    PIDController pid;
    private double target;

    public TurnToHeading(Telemetry telemetry, MechanicalDriveBase mechanicalDriveBase, IMUControl imu, PIDController pid)
    {
        this.telemetry = telemetry;
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.imu= imu;
        this.pid = pid;
        pid.reset();
        pid.setSetpoint(target);
//        pid.setInputRange(0, target);
//        pid.setOutputRange(0, 1);
//        pid.setTolerance(.4);
//        pid.enable();
    }

    protected double turnToHeading(double x, double y, double currentHeading)
    {
        double targetHeading = Math.toDegrees(Math.atan2(x,y));
        double headingDeviation = (targetHeading - currentHeading) % 360;
        if (headingDeviation > 180) //change 180 to get get only positives
        {
            headingDeviation = headingDeviation - 360;
        }
        return headingDeviation;
    }

    protected void turnTH(double target, double speed)
    {
        double imu_heading = (imu.getAngle() % 360);

        if (imu_heading >= 180)
        {
            imu_heading = (imu_heading - 360) * (Math.abs(imu.getAngle())/(imu.getAngle()));
        }

        if (target != 0)
        {
            this.target = target;
            pid.reset();
            pid.setSetpoint(target);
            pid.setInputRange(0, target);
            pid.setOutputRange(0, speed + 0.5);
            pid.setTolerance(1);
            pid.enable();
        }

        speed = pid.performPID(Math.abs(imu_heading));

        if (imu_heading > this.target)
        {
            mechanicalDriveBase.driveMotors(0, 1, 0, 0.5);
        }
        else if (imu_heading < this.target)
        {
            mechanicalDriveBase.driveMotors(0, -1, 0, 0.5);
        }
        else
        {
            mechanicalDriveBase.driveMotors(0,0,0,0);
        }

        telemetry.addData("DATA", "taget: " + this.target + "  imu_actual: " + imu.getAngle() + " Imu_heading: " + imu_heading + " speed: " + speed);
        telemetry.update();

    }

}

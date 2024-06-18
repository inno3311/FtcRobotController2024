package org.firstinspires.ftc.teamcode.FieldCentric;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.PIDController;
import org.opencv.core.Mat;

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
//        double headingDeviation = (targetHeading - currentHeading) % 360;
//        if (headingDeviation > 180)
//        {
//            headingDeviation = headingDeviation - 360;
//        }

        if (targetHeading < 0)
        {
            targetHeading = Math.abs(targetHeading) + (180 - Math.abs(targetHeading)) * 2;
        }
// 140 (180 - 140) * 2
        return targetHeading;

    }

    protected void turnTH(double target)
    {
        double speed;

        double imu_heading = Math.abs(imu.getAngle());

        if (imu_heading > 360)
        {
            imu_heading -= 360 * Math.floor(imu_heading / 360);
        }
        else if (imu.getAngle() < 0)
        {
            imu_heading = 360 - imu_heading;
        }

//        else if (imu_heading < -180)
//        {
//            imu_heading += 360  * Math.floor(imu.getAngle() / 360);
//        }
//        if (imu.getAngle() > 360 || imu.getAngle() < -360)
//        {
//            imu_heading *= -1 * (imu.getAngle() % 360);
//        }

//        if (imu_heading >= 180)
//        {
//            imu_heading = (imu_heading - 180) * (Math.abs(imu.getAngle())/(imu.getAngle()));
//        }

        if (target != 0)
        {
            this.target = target;
//            imu.resetAngle();
            pid.reset();
            pid.setSetpoint(target);
            pid.setInputRange(0, target);
            pid.setOutputRange(0, 1);
            pid.setTolerance(0.2);
            pid.enable();
        }

        speed = pid.performPID(imu_heading);

        if (this.target >= 180 && !pid.onTarget() && imu_heading != this.target)
        {
            mechanicalDriveBase.driveMotors(0, speed, 0, 0.5);
        }
        else if (this.target < 180 && !pid.onTarget() && imu_heading != this.target)
        {
            mechanicalDriveBase.driveMotors(0, -speed,0, 0.5);
        }
        else
        {
            mechanicalDriveBase.driveMotors(0,0,0,0);
        }

        telemetry.addData("DATA", "taget: " + this.target + "  imu_actual: " + imu.getAngle() + " Imu_heading: " + imu_heading + " speed: " + speed);
        telemetry.update();

    }

    protected void turnTH2(double target)
    {
        double speed;

        if (target != 0)
        {
            this.target = target;
//            imu.resetAngle();
        }

        imu.rotate((int) (target - imu.getAngle()), 1);



        telemetry.addData("DATA", "taget: " + this.target + "  imu_actual: " + imu.getAngle());
        telemetry.update();

    }

    private double toTheNearest10th(double number)
    {
        return (Math.round(number/10)) * 10;
    }

}

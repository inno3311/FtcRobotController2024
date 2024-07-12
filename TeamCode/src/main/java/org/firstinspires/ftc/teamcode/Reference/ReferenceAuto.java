package org.firstinspires.ftc.teamcode.Reference;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.IMU;

//s7
@Autonomous(name = "Auto", group = "learn")
public class ReferenceAuto extends LinearOpMode
{

    private DcMotor lf;
    private DcMotor rf;
    private DcMotor lb;
    private DcMotor rb;
    //s12
    private IMU imu;
    //e12

    ReferenceDrive referenceDrive;
    @Override
    public void runOpMode()
    {
        //declare
        referenceDrive = new ReferenceDrive(this, true);
        //e7
        //s13
        imu = new IMU(hardwareMap,telemetry);
        //e13
        //s11
        waitForStart();
        drive(12,0.5);
        //e11
        //s15
        rotate(90, 0.5);
        //e15 run tests
        //s16
        drive(12,0.5);
        rotate(90, 0.5);

        drive(12,0.5);
        rotate(90, 0.5);

        drive(12,0.5);
        rotate(90, 0.5);
        //e16
    }

    //s10
    public void drive(double distance, double speed)
    {
        if (distance < 0)
        {
            while (lf.getCurrentPosition() > distance)
            {
                referenceDrive.driveMotors(1, 0, 0, -speed);
            }
        }
        else
        {
            while (lf.getCurrentPosition() < distance)
            {
                referenceDrive.driveMotors(1, 0, 0, speed);
            }
        }

        referenceDrive.setMotorMode(true);
    }
    //e10

    //s14
    public void rotate(double degrees, double speed)
    {
        int direction = 1;

        if (degrees < 0)
        {
            degrees = Math.abs(degrees);
            direction = -1;
        }

        // restart imu angle tracking.
        imu.resetAngle();

        while (opModeIsActive() && Math.abs(imu.getAngle()) < degrees)
        {
            this.referenceDrive.driveMotors(0, direction, 0, speed);
        }

        referenceDrive.driveMotors(0, 0, 0, 0);

        telemetry.addData("getAngle() %f", Math.abs(imu.getAngle()));
        telemetry.update();
    }
    //e14


}

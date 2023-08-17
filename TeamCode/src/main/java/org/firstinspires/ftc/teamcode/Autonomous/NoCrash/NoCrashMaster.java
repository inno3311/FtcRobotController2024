package org.firstinspires.ftc.teamcode.Autonomous.NoCrash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBase;

@Autonomous(name = "No Crash", group = "No Crash")
public class NoCrashMaster extends OpMode
{
    MecanumDriveBase mecanumDriveBase;
    DistanceSensor distanceSensorRight;
    DistanceSensor distanceSensorLeft;

    @Override
    public void init()
    {
        mecanumDriveBase = new MecanumDriveBase(hardwareMap);
        distanceSensorRight = hardwareMap.get(DistanceSensor.class, "LeftDis");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class, "RightDis");

    }

    @Override
    public void loop()
    {
        if (distanceSensorRight.getDistance(DistanceUnit.INCH) <= 24)
        {
            mecanumDriveBase.brake();
            mecanumDriveBase.driveMotors(0.3,1,0,1);
        }
        else if ((distanceSensorLeft.getDistance(DistanceUnit.INCH) <= 24))
        {
            mecanumDriveBase.brake();
            mecanumDriveBase.driveMotors(0.3,-1,0,1);
        }
        else
        {
            mecanumDriveBase.driveMotors(1,0,0,0.5);
        }
    }

}

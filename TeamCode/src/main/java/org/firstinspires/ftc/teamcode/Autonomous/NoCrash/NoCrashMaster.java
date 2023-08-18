package org.firstinspires.ftc.teamcode.Autonomous.NoCrash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBase;
import org.firstinspires.ftc.teamcode.IMU.MainIMU;

@Autonomous(name = "No Crash", group = "No Crash")
public class NoCrashMaster extends OpMode
{
    MecanumDriveBase mecanumDriveBase;
    DistanceSensor distanceSensorRight;
    DistanceSensor distanceSensorLeft;
    MainIMU mainIMU;
    double rightAngle = 0;

    @Override
    public void init()
    {
        mecanumDriveBase = new MecanumDriveBase(hardwareMap);
        distanceSensorRight = hardwareMap.get(DistanceSensor.class, "LeftDis");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class, "RightDis");
        mainIMU = new MainIMU(telemetry, hardwareMap);
    }

    @Override
    public void loop()
    {
        if (distanceSensorLeft.getDistance(DistanceUnit.INCH) <= 24)
        {
            mecanumDriveBase.brake();
            rightAngle = Math.abs(mainIMU.getHeading(telemetry));
            rightAngle += Math.abs(mainIMU.getHeading(telemetry));
            while (Math.abs(mainIMU.getHeading(telemetry)) < )
            {
                telemetry.addData("Heading", Math.abs(mainIMU.getHeading(telemetry)));
                telemetry.addData("rightAngle", rightAngle);
                telemetry.update();
                mecanumDriveBase.driveMotors(0, 1, 0, 1);
            }
        }
//        else if ((distanceSensorLeft.getDistance(DistanceUnit.INCH) <= 24))
//        {
//            mecanumDriveBase.brake();
//            rightAngle = Math.abs(mainIMU.getHeading(telemetry));
//            rightAngle += Math.abs(mainIMU.getHeading(telemetry));
//            while (Math.abs(mainIMU.getHeading(telemetry)) < rightAngle)
//            {
//                mecanumDriveBase.driveMotors(0, -1, 0, 1);
//            }
//        }
        else
        {
            mecanumDriveBase.driveMotors(1,0,0,0.5);
        }
    }

}

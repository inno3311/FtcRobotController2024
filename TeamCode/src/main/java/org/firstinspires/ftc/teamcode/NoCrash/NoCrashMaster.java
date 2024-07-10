package org.firstinspires.ftc.teamcode.NoCrash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMU;

@Autonomous(name = "No Crash", group = "No Crash")
@Disabled
public class NoCrashMaster extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    DistanceSensor distanceSensorRight;
    DistanceSensor distanceSensorLeft;
    IMU imu;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        distanceSensorRight = hardwareMap.get(DistanceSensor.class, "LeftDis");
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class, "RightDis");
        imu = new IMU(hardwareMap, telemetry);
    }

    @Override
    public void loop()
    {
        //Distance sensors detect something the robot will stop and turn
        if (distanceSensorLeft.getDistance(DistanceUnit.INCH) <= 24 && distanceSensorRight.getDistance(DistanceUnit.INCH) <= 24)
        {
            //Call IMU rotate to turn 90 degrees
            imu.rotate(90,1);
        }
        else
        {
            mechanicalDriveBase.driveMotors(1,0,0,0.8);
        }
    }

}


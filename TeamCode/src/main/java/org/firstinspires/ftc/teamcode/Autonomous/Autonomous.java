package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.IMU.MainIMU;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "IMUTest", group = "IMU")
public class Autonomous extends LinearOpMode
{
    MainIMU mainIMU;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mainIMU = new MainIMU(telemetry, hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("Heading", mainIMU.getHeading(telemetry));
            telemetry.update();
        }
    }
}

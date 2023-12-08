package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "IMUTest", group = "IMU")
@Disabled
public class IMUTestMaster extends LinearOpMode
{
    IMUControl imuControl;

    @Override
    public void runOpMode() throws InterruptedException
    {
        imuControl = new IMUControl(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive())
        {
            //Rotate the robot 90 degrees
            imuControl.rotate(90, 1);
            sleep(1000);
        }
    }
}

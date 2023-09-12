package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

@Autonomous(name = "EncoderTest", group = "EncoderTest")
public class EncoderTestMaster extends LinearOpMode
{
    IMUControl imuControl;
    MechanicalDriveBase mechanicalDriveBase;
    final double  COUNTS_PER_INCH = (8192 * 1) / (2 * 3.1415); // 1,303.835747254496

    @Override
    public void runOpMode() throws InterruptedException
    {
        imuControl = new IMUControl(hardwareMap, telemetry);
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        waitForStart();

        while (opModeIsActive())
        {
            mechanicalDriveBase.strafeWithEncoders(COUNTS_PER_INCH * 12, true, 0.5);
            sleep(2000);
            mechanicalDriveBase.strafeWithEncoders(COUNTS_PER_INCH * 12, true, 0.5);

        }
    }
}

package org.firstinspires.ftc.teamcode.FieldCentric;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.PIDController;

import java.io.IOException;

@TeleOp(name = "TurnToHeadingTest", group = "FieldCentric")
public class TurnToHeadingTest extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    TurnToHeading turnToHeading;
    IMUControl imu;

    PIDController pid;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        pid = new PIDController(.02, .003, .001);
        turnToHeading = new TurnToHeading(telemetry, mechanicalDriveBase, imu, pid);
    }

    @Override
    public void loop()
    {
//        mechanicalDriveBase.gamepadController(gamepad1);
        turnToHeading.turnTH(turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, mechanicalDriveBase.lb.getCurrentPosition()), 0.5);
    }
}

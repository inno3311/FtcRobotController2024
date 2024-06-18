package org.firstinspires.ftc.teamcode.FieldCentric;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.PIDController;

@TeleOp(name = "Centric Command", group = "FieldCentric")
public class CentricCommand extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;
    IMUControl imu;

    PIDController pid;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        pid = new PIDController(.02, .003, .001);
        turnToHeading = new TurnToHeading(telemetry, mechanicalDriveBase, imu, pid);
        centricDrive = new CentricDrive(mechanicalDriveBase);
    }

    @Override
    public void loop()
    {
//        centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), gamepad1.right_stick_x);
//        turnToHeading.turnTH(turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, imu.getAngle()));
        turnToHeading.turnTH(turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, imu.getAngle()));
    }
}

package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake
{
    HardwareMap hardwareMap;
    Telemetry telemetry;
    Gamepad gamepad;

    DcMotor intake;

    private void initIntake()
    {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public Intake(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        initIntake();
    }

    public void driveIntake()
    {
        if (gamepad.right_bumper)
        {
            intake.setPower(1);
        }
        else if (gamepad.back)
        {
            intake.setPower(-1);
        }
        else
        {
            intakeBreak();
        }

        telemetry();
    }

    private void intakeBreak() {intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}

    private void telemetry() {telemetry.addData("Intake", "Speed", intake.getPower());}

}

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
    DcMotor height;

    private final int lowerPosition = 0; //TODO need to find and set this value
    private final int upperPosition = 0; //TODO need to find and set this value

    private void initIntake()
    {
        intake = hardwareMap.get(DcMotor.class, "intake");
        height = hardwareMap.get(DcMotor.class, "height");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        height.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public Intake(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        initIntake();
    }

    public void intakeControl()
    {
        driveIntake();
        heightControl();
        telemetry();
    }

    private void driveIntake()
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
    }

    private void heightControl()
    {
        if (gamepad.left_bumper)
        {
            adjustHeight();
        }
        else
        {
            heightEncoder();
        }
    }

    private void adjustHeight()
    {
        if (gamepad.dpad_up)
        {
            height.setPower(0.2);
        }
        else if (gamepad.dpad_down)
        {
            height.setPower(-0.2);
        }
    }

    private void heightEncoder()
    {

        height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (gamepad.dpad_up)
        {
            height.setTargetPosition(0);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            height.setPower(0.2);
        }
        else if (gamepad.dpad_down)
        {
            height.setTargetPosition(0);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            height.setPower(0.2);        }
        else
        {
            heightBreak();
        }
    }

    private void intakeBreak() {intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    private void heightBreak() {height.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    private void telemetry() {telemetry.addData("Intake", "Intake Speed %.2f, Height speed %.2f, Height position %.2f", intake.getPower(), height.getPower(), height.getCurrentPosition());}

}

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

    private final int pixelsOne = -1780;
    private final int pixelsTwo = -1610;
    private final int pixelsThree = -1570;
    private final int pixelsFour = -1540;
    private final int pixelsFive = -1520;

    private void initIntake()
    {
        intake = hardwareMap.get(DcMotor.class, "intake");
        height = hardwareMap.get(DcMotor.class, "height");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        height.setDirection(DcMotorSimple.Direction.REVERSE);
        height.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
            intake.setPower(0);
            intakeBreak();
        }
    }

    private void heightControl()
    {
        analogHeight();
        encoderHeight();
    }

    private void analogHeight()
    {
        double heightSpeed = -gamepad.left_stick_y;

        if (Math.abs(heightSpeed) > 0)
        {
            height.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            height.setPower(heightSpeed * 0.5);
        }
        else if (height.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
            height.setTargetPosition(height.getCurrentPosition());
            height.setPower(0.4);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

    }


    private void encoderHeight()
    {

        if (gamepad.dpad_up)
        {
            height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            height.setTargetPosition(pixelsFive);
            height.setPower(0.3);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.dpad_right)
        {
            height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            height.setTargetPosition(pixelsFour);
            height.setPower(0.3);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.dpad_down)
        {
            height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            height.setTargetPosition(pixelsThree);
            height.setPower(0.3);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.dpad_left)
        {
            height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            height.setTargetPosition(pixelsTwo);
            height.setPower(0.3);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.left_trigger > 0.5)
        {
            height.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            height.setTargetPosition(pixelsOne);
            height.setPower(0.3);
            height.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


    private void intakeBreak() {intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    private void heightBreak() {height.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    private void telemetry()
    {
        telemetry.addData("Intake", "Intake Speed = " + intake.getPower());
        telemetry.addData("Height", "Height speed = " + height.getPower());
        telemetry.addData("Height", "Height position = " + height.getCurrentPosition());
    }

}

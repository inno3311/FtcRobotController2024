package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LinerSlide
{
    HardwareMap hardwareMap;
    Telemetry telemetry;
    Gamepad gamepad;

    DcMotor linerSlide;

    private final int lowerPosition = 0; //TODO need to find and set this value
    private final int upperPosition = 0; //TODO need to find and set this value

    private void initMotor()
    {
        linerSlide = hardwareMap.get(DcMotor.class, "slide");
        linerSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        linerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public LinerSlide(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        initMotor();
    }

    public void driveSlide()
    {
        if (!gamepad.a && !gamepad.b)
        {
            joystickDive();
        }
        else if (gamepad.a || gamepad.b)
        {
            encoderDrive();
        }
        else if (linerSlide.getPower() == 0)
        {
            slideBreak();
        }

        telemetry();
    }

    private void encoderDrive()
    {
        linerSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (gamepad.y)
        {
            linerSlide.setTargetPosition(upperPosition);
            linerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linerSlide.setPower(1);
        }
        else if (gamepad.a)
        {
            linerSlide.setTargetPosition(lowerPosition);
            linerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linerSlide.setPower(1);
        }

    }

    private void joystickDive()
    {
        linerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linerSlide.setPower(gamepad.right_stick_y * 0.5);
    }

    private void slideBreak()
    {
        linerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void telemetry() {telemetry.addData("Liner slide", "Speed, Encoder Position", linerSlide.getPower(), linerSlide.getCurrentPosition());}

}

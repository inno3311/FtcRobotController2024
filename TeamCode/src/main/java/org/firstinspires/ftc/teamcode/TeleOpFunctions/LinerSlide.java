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
        if (!gamepad.a && !gamepad.b && ! (linerSlide.isBusy() && linerSlide.getMode() == DcMotor.RunMode.RUN_TO_POSITION))
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
        if (gamepad.a)
        {
            linerSlide.setTargetPosition(0); //put value here in place of 0
            linerSlide.setPower(1);
        }
        else if (gamepad.b)
        {
            linerSlide.setTargetPosition(0); //put value here in place of 0
            linerSlide.setPower(1);
        }
        linerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void joystickDive()
    {
        linerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linerSlide.setPower(gamepad.left_stick_x);
    }

    private void slideBreak()
    {
        linerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void telemetry() {telemetry.addData("Liner slide", "Speed, Encoder Position", linerSlide.getPower(), linerSlide.getCurrentPosition());}

}

package org.firstinspires.ftc.teamcode.TeleOpFunctions.OldCode;

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

    private final int lowerBounds = 5;
    private final int upperBounds = 2700;
    private final int resetPosition = 50;
    private final int upperPosition = 1000;

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
        encoderControl();
        analogControl();
        telemetry();
    }

    private void encoderControl()
    {
//        linerSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (gamepad.y)
        {
            linerSlide.setTargetPosition(upperPosition);
            linerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linerSlide.setPower(0.3);
        }
        else if (gamepad.a)
        {
            linerSlide.setTargetPosition(resetPosition);
            linerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linerSlide.setPower(0.3);
        }

    }

    private void analogControl()
    {
        double slidePower = gamepad.right_stick_y * 0.7;

        if (Math.abs(slidePower) > 0)
        {
            if (linerSlide.getCurrentPosition() > upperBounds && slidePower < 0) {linerSlide.setPower(0);}
            else if (linerSlide.getCurrentPosition() < lowerBounds && slidePower > 0) {linerSlide.setPower(0);}
            else
            {
                linerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                linerSlide.setPower(slidePower);
            }
        }
        else
        {
            linerSlide.setPower(0);
            slideBreak();
        }

    }

    private void slideBreak() {linerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}

    private void telemetry()
    {
        telemetry.addData("Liner slide", "Speed " + linerSlide.getPower());
        telemetry.addData("Liner slide", "Encoder Position = " + linerSlide.getCurrentPosition());
        telemetry.addData("", linerSlide.getCurrentPosition());
    }

}

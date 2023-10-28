package org.firstinspires.ftc.teamcode.Controller;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TeleOpFunctionsInheritanceTest;

public class MotorControl extends TeleOpFunctionsInheritanceTest
{
    private DcMotor motor;
    private String motorName;
    private boolean hasEncoder;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    protected Gamepad gamepad1;
    protected Gamepad gamepad2;

    //Will be used to get the parameters below from the masterclass
    private MotorControl(OpMode opMode)
    {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
    }

    /**
     * @param motorName Name that you well enter for configuration
     * @param direction Direction you want the motor to spin: true = FORWARD, false = REVERSE
     * @param hasEncoder Does it have an encoder?
     */
    protected MotorControl(String motorName, Boolean direction, Boolean hasEncoder, OpMode opMode)
    {
        this(opMode);

        this.motorName = motorName;
        this.hasEncoder = hasEncoder;
        motor = this.hardwareMap.get(DcMotor.class, motorName);

        if (direction) {motor.setDirection(DcMotorSimple.Direction.FORWARD);}
            else {motor.setDirection(DcMotorSimple.Direction.REVERSE);}

        if (hasEncoder) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);}

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    /**
     * Analog control method without bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     */
    protected void analogControl(double speedLimit, double input)
    {
        double slidePower = input;
        Range.clip(slidePower, -speedLimit, speedLimit);

        if (Math.abs(slidePower) > 0)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(slidePower);
        }
        else {motorBreak();}

    }

    /**
     * Analog control method with bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     * @param lowerBound Motor will not spin past this bound at negative power (must have encoder to use this feature)
     * @param upperBound Motor will not spin past this bound at positive power(must have encoder to use this feature)
     */
    protected void analogControl(double speedLimit, double input, int lowerBound, int upperBound, boolean advanceBreak)
    {
        double slidePower = input;
        Range.clip(slidePower, -speedLimit, speedLimit);

        if (Math.abs(slidePower) > 0)
        {
            if (motor.getCurrentPosition() > upperBound && slidePower > 0) {motorBreak();}
            else if (motor.getCurrentPosition() < lowerBound && slidePower < 0) {motorBreak();}
            else
            {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor.setPower(slidePower);
            }
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
//            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(motor.getCurrentPosition());
            motor.setPower(1);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {}
        else {motorBreak();}

    }

    /**
     * @param speed The speed at which the motor will spin
     * @param argument1 The Gamepad bool input that will make it spin forward
     * @param argument2 The Gamepad bool input that will make it spin backwards
     */
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        if (argument1)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(speed);
        }
        else if (argument2)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(-speed);
        }
        else {motorBreak();}
    }


    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     * @param argument The Gamepad bool input that will make it move
     */
    protected void encoderControl(int target, double speed, boolean argument)
    {
        if (argument)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(target);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(speed);
        }
    }

    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     */
    protected void encoderControlAutonomous(int target, double speed)
    {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPosition(target);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(speed);
    }

    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     * @param argument The Gamepad analog input that will make it move if its value is greater than 0.2
     */
    protected void encoderControl(int target, double speed, double argument)
    {
        if (Math.abs(argument) > 0.2)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(target);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(speed);
        }
    }


    /**
     *     Breaking method
     */
    protected void motorBreak()
    {
        motor.setPower(0);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData(motorName, "Breaking");
    }

    /**
     *     Prints motor telemetry
     */
    protected void telemetry()
    {
        telemetry.addData(motorName, "Speed: %.2f", motor.getPower());
        if (hasEncoder) {telemetry.addData(motorName, "Encoder Position: %d", motor.getCurrentPosition());}
    }

}

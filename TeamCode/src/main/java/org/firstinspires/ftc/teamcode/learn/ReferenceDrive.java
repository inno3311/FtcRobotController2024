package org.firstinspires.ftc.teamcode.learn;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ReferenceDrive
{
    //s1
    public DcMotor lf;
    public DcMotor rf;
    public DcMotor lb;
    public DcMotor rb;
    private OpMode opMode;
    //s8 add is encoders e8?
    public ReferenceDrive(OpMode opMode, boolean encoders)
    {
        this.opMode = opMode;

        //set name for configuration
        lf = opMode.hardwareMap.get(DcMotor.class, "lf");
        rf = opMode.hardwareMap.get(DcMotor.class, "rf");
        lb = opMode.hardwareMap.get(DcMotor.class, "lb");
        rb = opMode.hardwareMap.get(DcMotor.class, "rb");

        //set direction
        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.FORWARD);
        rb.setDirection(DcMotor.Direction.REVERSE);

        // Run Without Encoders comment out on step 8
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // setMotorMode(encoders)
    }

    //s9
    public void setMotorMode(boolean encoders)
    {
        if (encoders)
        {
            //stop and reset the encoders so we can run them
            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else
        {
            //run without encoders
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    //e9

    public void driveMotors(double drive, double turn, double strafe, double speed)
    {
        double leftPowerFront  = (drive + turn + strafe);
        double rightPowerFront = (drive - turn - strafe);
        double leftPowerBack   = (drive + turn - strafe);
        double rightPowerBack  = (drive - turn + strafe);

        lf.setPower(leftPowerFront * speed);
        rf.setPower(rightPowerFront * speed);
        lb.setPower(leftPowerBack * speed);
        rb.setPower(rightPowerBack * speed);

    }

    public void mecunumDrive(Gamepad gamepad)
    {
        double drive = -gamepad.left_stick_y;
        double turn = gamepad.right_stick_x;
        double strafe = gamepad.left_stick_x;
        double speed = 1 - gamepad.left_trigger;
        driveMotors(drive, turn, strafe, speed);
    }
    //e1

    //s5
    public void tankDrive(Gamepad gamepad)
    {
        double drive = -gamepad.left_stick_y;
        double turn = gamepad.right_stick_x;
        driveMotors(drive, turn, 0, 1);
    }
    //e5 go back to TeleOp and add tankDrive

    //s2
    public void telemetry()
    {
        opMode.telemetry.addData("Motors", "lf: %.2f rf: %.2f lb: %.2f rb: %.2f", lf.getPower(), rf.getPower(), lb.getPower(), rb.getPower());
        opMode.telemetry.update();
    }
    //e2
    //make TeleOp after this
}

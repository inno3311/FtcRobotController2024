package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

import java.io.IOException;

@Autonomous(name = "Auto straight test", group = "Mez")
public class LinearOpModeMez extends LinearOpMode
{

    /** Drive control */
    MecanumSynchronousDriver driver;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;

    @Override
    public void runOpMode() throws InterruptedException
    {

        try
        {
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        driver.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driver.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driver.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driver.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();
        start();

        //Drive forward 72 inches
//        driver.strafe(24 * 3,1,0.3, new IMUControl(hardwareMap, telemetry));
//        driver.forward(24 * 4, 1, 0.4);
        driver.turn(90, 1, 0.4);
        sleep(10000);
        driver.turn(90, -1, 0.4);

        while (opModeIsActive())
        {
//            telemetry.addData("encoder", "left: " + driver.lf.getCurrentPosition() + " right: " + driver.rf.getCurrentPosition());
//            telemetry.update();
            telemetry.addData("90 = ", (ticksPerDegree * 90) + "\n current position = " +  driver.rb.getCurrentPosition());
            telemetry.update();
        }
    }
}

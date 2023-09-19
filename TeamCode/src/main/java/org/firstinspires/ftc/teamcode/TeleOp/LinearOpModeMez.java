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




        waitForStart();
        start();

        aroundyTest();

        //rotateTest();


        //Drive forward 72 inches
//        driver.strafe(24 * 3,1,0.3, new IMUControl(hardwareMap, telemetry));
//        driver.forward(24 * 4, 1, 0.4);

//        driver.forward(12 * 4,1,0.6);
//        sleep(1000);
//        driver.forward(12 * 4,-1,0.6);
//        sleep(1000);
//        driver.turn(180, -1, .5);


//        driver.forward(12 * 2,1,0.3);
//        sleep(1000);
//        driver.turn(90, -1, .3);
//        sleep(1000);
//        driver.forward(12 * 1.8,1,.3);
//        sleep(1000);
//        driver.turn(90, 1, 1);
//        sleep(1000);
//        driver.forward(12 * 2,1,.3);
//        sleep(1000);
//        driver.turn(90, 1, 0.4);
//        sleep(1000);
//        driver.forward(12 * 1.8,1,0.3);
//        sleep(1000);
//        driver.turn(100, 1, 0.4);
//        sleep(1000);
//        driver.forward(12 * 4,1,0.3);
//        sleep(1000);
//        driver.forward(12 * 4,-1,0.3);

        while (opModeIsActive())
        {
//            telemetry.addData("encoder", "left: " + driver.lf.getCurrentPosition() + " right: " + driver.rf.getCurrentPosition());
//            telemetry.update();
//            telemetry.addData("90 = ", (ticksPerDegree * 90) + "\n current position = " +  driver.rb.getCurrentPosition());
//            telemetry.update();
        }
    }


    public void rotateTest()
    {
//        sleep(1000);
//        driver.turn(360, -1, .5);

        sleep(3000);
        driver.turn(90, 1, .5);

        sleep(3000);
        driver.turn(90, -1, .5);

//        sleep(3000);
//        driver.turn(180, -1, .5);

    }

    public void aroundyTest()
    {
        driver.forward(12 * 4,1,0.8);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 1.5,1,0.8);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 4,1,0.8);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 1.5,1,0.8);
        sleep(100);
        driver.turn(90, -1, .5);
    }


}



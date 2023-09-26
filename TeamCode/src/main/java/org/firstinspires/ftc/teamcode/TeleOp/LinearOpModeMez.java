package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

@Autonomous(name = "Mez test", group = "Mez")
public class LinearOpModeMez extends LinearOpMode
{

    /** Drive control */
    MecanumSynchronousDriver driver;
//    IMUControl imuControl;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;

    ImuHardware imuControl;

    WebCamHardware webcam;


    @Override
    public void runOpMode() throws InterruptedException
    {

        try
        {
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);
            webcam = new WebCamHardware(this);
            imuControl = new ImuHardware(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        webcam.initTfod();

        waitForStart();
        start();





//        aroundyTest();

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

        imuControl.resetAngle();

        while (opModeIsActive())
        {
//            driver.rotate(1, 1, imuControl);
            driver.rotate(90,1, imuControl);
//            driver.rotate(90,1, imuControl);

            sleep(2000);
//            webcam.telemetryTfod();
//            telemetry.addData("encoder", "left: " + driver.lf.getCurrentPosition() + " right: " + driver.rf.getCurrentPosition());
//            telemetry.update();
//            telemetry.addData("90 = ", (ticksPerDegree * 90) + "\n current position = " +  driver.rb.getCurrentPosition());
//            telemetry.update();
        }
    }


    public void rotateTest()
    {
        double rotateSpeed = 0.4;
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);

        sleep(2000);
        driver.turn(360, -1, rotateSpeed);


//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(90, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(180, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(360, 1, rotateSpeed);
    }

    public void aroundyTest()
    {
        double speed = 0.7;
        driver.forward(12 * 4,1,speed);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 1.5,1,speed);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 4,1,speed);
        sleep(100);
        driver.turn(90, -1, .5);
        sleep(100);
        driver.forward(12 * 1.5,1,speed);
        sleep(100);
        driver.turn(90, -1, .5);
    }


}



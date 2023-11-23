package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.io.IOException;
import java.lang.annotation.ElementType;

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
    WebcamName webCamName;

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

/*
        webcam.initTfod();


        Recognition rec = null;
        while ((rec = webcam.findObject()) == null)
        {
            telemetry.addData("- Camera", "Looking for object");
            telemetry.update();
        }

        double x = (rec.getLeft() + rec.getRight()) / 2 ;
        double y = (rec.getTop()  + rec.getBottom()) / 2 ;

        if(x > 50 && x < 150) telemetry.addData("Left", x);
        else if(x > 160 && x < 450) telemetry.addData("Center", x);
        else if(x > 460 && x < 600) telemetry.addData("Right", x);
        else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");

        telemetry.addData(""," ");
        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
        telemetry.addData("- Position", "%.0f / %.0f", x, y);
        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
        telemetry.update();

*/

        waitForStart();
        start();

        try
        {
            driver.strafe(48, 1, 0.6, imuControl);
            sleep(3000);
            driver.strafe(48, -1, 0.6, imuControl);
            sleep(3000);

            driveStraightTest();
            sleep(10000);

            driver.rotate2(-30, imuControl);
            sleep(1000);

            driver.rotate2(-30, imuControl);
            sleep(1000);

            driver.rotate2(-30, imuControl);
            sleep(1000);

            driver.rotate2(-45, imuControl);
            sleep(1000);

            driver.rotate2(-45, imuControl);
            sleep(1000);

            driver.rotate2(-90, imuControl);
            sleep(1000);

            driver.rotate2(-90, imuControl);
            sleep(1000);



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        //Drive forward 72 inches
//        driver.strafe(24 * 3,1,0.3, new IMUControl(hardwareMap, telemetry));
//        driver.forward(24 * 4, 1, 0.4);




        while (opModeIsActive())
        {
//            driver.rotate(1, 1, imuControl);
//            driver.rotate(90,1, imuControl);
//            driver.rotate(90,1, imuControl);

            sleep(2000);
//            webcam.telemetryTfod();
//            telemetry.addData("encoder", "left: " + driver.lf.getCurrentPosition() + " right: " + driver.rf.getCurrentPosition());
//            telemetry.update();
//            telemetry.addData("90 = ", (ticksPerDegree * 90) + "\n current position = " +  driver.rb.getCurrentPosition());
//            telemetry.update();
        }
    }


    public void driveStraightTest()
    {
        driver.forward(24 * 4, 1, .6);
        sleep(3000);
        driver.forward(24 * 4, -1, .6);
        sleep(8000);
        driver.forward(24 * 4, 1, 1);
        sleep(3000);
        driver.forward(24 * 4, -1, 1);

    }

    public void rotateTest() throws InterruptedException, IOException
    {
        double rotateSpeed = 0.4;
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);

//        sleep(2000);
//        driver.rotateOd(360, 1);

//        int startPos = driver.rb.getCurrentPosition();
//        Logging.setup();
//        while (opModeIsActive())
//        {
//            driver.driveMotors(0, .5, 0, 1);
//
//            telemetry.addData("rotateOd2", "startPos:  %d   targetPos: %d ", startPos, driver.rb.getCurrentPosition() - startPos);
//            //mOpMode.telemetry.addData("rotateOd2", "power: %f currPos:  %d", power, this.rb.getCurrentPosition() - startPos);
//            telemetry.update();
//
//            Logging.log("#rotateOd startPos:  %d   Pos: %d ", startPos,driver.rb.getCurrentPosition() - startPos);
//
//        }
//
//        telemetry.addData("rotateOd2", "startPos:  %d   targetPos: %d ", startPos, driver.rb.getCurrentPosition() - startPos);
//        telemetry.update();


//        sleep(2000);
//        driver.rotateOd(180, 0.5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);

        sleep(2000);
        driver.rotateOd(45, 0.5);
        sleep(2000);
        driver.rotateOd(45, .5);
        sleep(2000);
        driver.rotateOd(90, .5);
        sleep(2000);
        driver.rotateOd(180, .5);
        sleep(2000);
        //driver.rotateOd(180, .5);
        sleep(2000);
        //driver.rotateOd(-180, .5);



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



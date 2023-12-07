package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.io.IOException;
import java.lang.annotation.ElementType;

@Autonomous(name = "Mez test", group = "Mez")
//@Disabled
public class LinearOpModeMez extends AutonomousBase
{


//    IMUControl imuControl;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;



    @Override
    public void runOpMode() throws InterruptedException
    {

        try {
            Logging.setup();
            Logging.log("Starting Logging for PlanGamma2");
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.runOpMode();

        waitForStart();
        start();



//        try
 //       {
        //driver.strafe();


//            driver.rotate2(-30, imuControl);
//            sleep(1000);
//
//            driver.rotate2(-30, imuControl);
//            sleep(1000);
//
//            driver.rotate2(-30, imuControl);
//            sleep(1000);
//
//            driver.rotate2(-45, imuControl);
//            sleep(1000);

//            driver.rotate2(-45, imuControl);
//            sleep(1000);
//
//            driver.rotate2(-90, imuControl);
//            sleep(1000);
//
//            driver.rotate2(-90, imuControl);
//            sleep(1000);



//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }



        //Drive forward 72 inches
//        driver.strafe(24 * 3,1,0.3, new IMUControl(hardwareMap, telemetry));
//        driver.forward(24 * 4, 1, 0.4);




        while (opModeIsActive())
        {

            testDeadWheels();

            //strafeTest();

            //driveStraightTest();

//            try
//            {
//                rotateTest();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }


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

    public void testDeadWheels()
    {
        int lfTicks, rfTicks, rbTicks = 0;
        lfTicks = driver.lf.getCurrentPosition();
        rfTicks = driver.rf.getCurrentPosition();
        rbTicks = driver.rb.getCurrentPosition();

        telemetry.addData("testDeadWheels", "lf = " + lfTicks + "\nrf = " +  rfTicks + "rb = " + rbTicks);
        telemetry.update();
    }

    public void strafeTest()
    {
        driver.strafe(24, 1, 0.5, imuControl);
        sleep(3000);
        driver.strafe(24, -1, 0.5, imuControl);
        sleep(3000);
    }

    public void driveStraightTest()
    {

        driver.forward(24, 1, .6);
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



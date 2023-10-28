package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

@Autonomous(name = "Blue Stage Left", group = "Group3311")
public class BlueStageLeftLinearOpMode extends LeftRightSuper
{

//    BlueStageRightLinearOpMode blueStageRightLinearOpMode;
//
//    WebCamHardware webcam;
//
//    ImuHardware imuControl;
//
//    /** Drive control */
//    MecanumSynchronousDriver driver;
//    AprilTagMaster aprilTagMaster;
//    InitAprilTags initAprilTags;
//    DriveToTag driveToTag;
//    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
//    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
//    enum zone
//    {
//        middle,
//        left,
//        right
//    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try {
            planGamma(zone);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        int day = 1;
//        switch (day)
//        {
//            case 1:
//                System.out.println("It's sunday");
//                break;
//            case 2:
//                System.out.println("It's Monday");
//                break;
//            default:
//                System.out.println("There is a problem there are only 7 days in a week numbers is not in range");
//                break;
//        }


        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);
    }
//    private void left() {
//
//    }
//    private void right() {
//    }


    //I recommend deleting this once you know you don't need it
    public void right()
    {

        driver.forward(20,1,0.6);
        driver.forward(24,1,0.6);
        sleep(500);
        driver.turn(45,1,0.4);
        sleep(500);
        driver.forward(5,1,0.6);
        sleep(500);
        driver.forward(5,-1,0.6);
        sleep(500);
        driver.turn(135,-1,0.4);
        sleep(500);
        driver.forward(20,1,0.6);
    }

/** As well as this commented-out code
* */
//     public void left()
//    {
//        driver.forward(20,1,0.6);
//        sleep(500);
//        driver.turn(45,-1,0.4);
//        sleep(500);
//        driver.forward(9,1,0.6);
//        sleep(500);
//        driver.forward(9,-1,0.6);
//        sleep(500);
//        driver.turn(135,-1,0.4);
//        sleep(500);
//        driver.forward(20,1,0.6);
//    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha() throws IOException, InterruptedException {
        //So to start you off, you are going to want to push the pixel to the second line.  So, a
        //forward command is in order.  You will need to measure the distance.  From there you will
        //probably need to back up a bit before rotating to your end goal.  I will let you figure
        //out what route that will be.


        planPurple(zone, false);

        sleep(1000);

        //Turn left
        driver.rotate(-90, imuControl);
        sleep(1000);

        // Turns 90 degrees left
        driver.turn(90, -1, .4);

        sleep(1000);
        //Turn right
        //driver.turn(90, 1, 0.4);
        driver.rotate(-90, imuControl);

        driver.forward(16, 1, 0.5);

    }

    /**
     * There is always a plan B.  ;)
     */
    public void planGamma(SpikeLineEnum zone) throws IOException, InterruptedException {

        //This planPurple detects the object and pushes the pixel toward it. It must go first
        //You need to write code that goes back and faces the backdrop so that Sam's AprilTag program can take over.
        planPurple(zone, false);
        //Write your code here

     driver.forward(14.5,1,5.0);

     sleep(1000);

     driver.turn(90,-0,.4);

     sleep(1000);

     driver.forward();


    }

}



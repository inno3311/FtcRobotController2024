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
    private boolean pixelInMiddle, pixelIsLeft, pixelIsRight;

    BlueStageRightLinearOpMode blueStageRightLinearOpMode;

    WebCamHardware webcam;

    ImuHardware imuControl;

//    enum zone
//    {
//        middle,
//        left,
//        right
//    }
//    private zone current = null;
//
//     //private enum zone
//    {
//       // center,
//        //left,
//       // right
//    }
   @Override
    public void runOpMode() throws InterruptedException
    {
        try
        {
            blueStageRightLinearOpMode = new BlueStageRightLinearOpMode();
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);
            webcam = new WebCamHardware(this);
            imuControl = new ImuHardware(this);
            initAprilTags = new InitAprilTags();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        webcam.initTfod();

        Recognition rec = null;
        while((rec = webcam.findObject()) == null){
            telemetry.addData("- Camera", "Looking for object");
            telemetry.update();
        }

        double x = (rec.getLeft() + rec.getRight()) / 2 ;
        double y = (rec.getTop()  + rec.getBottom()) / 2 ;

        String getXPosition = webcam.findTarget(x);
        if(getXPosition.equals("left")){
            telemetry.addData("Left", x);
            blueStageRightLinearOpMode.zone = BlueStageRightLinearOpMode.SpikeLineEnum.LEFT_SPIKE;
        }else if (getXPosition.equals("center")){
            telemetry.addData("Center", x);
            blueStageRightLinearOpMode.zone = BlueStageRightLinearOpMode.SpikeLineEnum.CENTER_SPIKE;
        } else if (getXPosition.equals("right")){
            telemetry.addData("Right", x);
            blueStageRightLinearOpMode.zone = BlueStageRightLinearOpMode.SpikeLineEnum.RIGHT_SPIKE;
        } else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");
//


        switch (blueStageRightLinearOpMode.zone) {
            case CENTER_SPIKE:
                try {
                    planAlpha();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RIGHT_SPIKE:
                right();
                break;
            case LEFT_SPIKE:
                left();
                break;
            default:
                planBeta();
                break;
        }

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


        //Your code goes in this function.   You can make other plans as well.  (two shells are
        //provided.
        try {
            planAlpha();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //planBeta();

        //Sample Test Programs
        //aroundyTest();
        //rotateTest();


        while (opModeIsActive())
        {

        }
    }
    private void left(){

    }
//    private void right() {
//    }

    public void right()
    {
        driver.forward(24,1,0.6);
        sleep(500);
        driver.turn(30,1,0.4);
        sleep(500);
        driver.forward(2,1,0.6);
        sleep(500);
        driver.forward(2,-1,0.6);

    }



    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha() throws IOException, InterruptedException {
        //So to start you off, you are going to want to push the pixel to the second line.  So, a
        //forward command is in order.  You will need to measure the distance.  From there you will
        //probably need to back up a bit before rotating to your end goal.  I will let you figure
        //out what route that will be.

        //Go forward 24 inches at speed of .5  (24 is just a filler.  you need to figure out how far it is
        blueStageRightLinearOpMode.planPurple(blueStageRightLinearOpMode.zone, false);

        sleep(1000);

        //Turn left
        driver.rotate(-90, imuControl);
        sleep(1000);


        sleep(1000);
        //Turn right
        //driver.turn(90, 1, 0.4);
        driver.rotate(-90, imuControl);

        driver.forward(16, 1, 0.5);

        //Left and let AprilTag take over
        driver.rotate(-90, imuControl);
//
//        // Drive 25 inches forward
//       driver.forward(25,1,0.6);
//
//       // Sleeps for 500 milisecond to make sure the robot is not turning
//        sleep(500);
//
//        // Dives backard 5 inches
//
//        driver.forward(5,-1,0.6);
//
//        sleep(500);
//
//        // Turns 90 degrees left
//        driver.turn(90, -1, .4);
//
//        sleep(500);
//
//        // After turning 90 degree drivebase drives 31 inches
//        driver.forward(31, 1, 0.6);
//


    }

      public void pixelRight( ) {
      }
        public void pixelLeft( ) {
        }

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta()
    {



    }


    /**
     * This test rotates in place. Each step has a 3 second pause.
     * 1.  Rotate right 90 degrees.
     * 2.  Rotate left 90 degrees.
     * 3.  Rotate left 180 degrees.
     * 4.  Rotate right 360 degrees.
     */
    public void rotateTest()
    {
        double rotateSpeed = 0.5;

        sleep(3000);
        driver.turn(90, 1, rotateSpeed);

        sleep(3000);
        driver.turn(90, -1, rotateSpeed);

        sleep(3000);
        driver.turn(180, -1, rotateSpeed);

        sleep(3000);
        driver.turn(360, 1, rotateSpeed);
    }

    /**
     * This is a sample run that drives in a "O" shape counter clockwise.
     *
     */
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



package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;

import java.io.IOException;

@Autonomous(name = "Blue Stage Left", group = "Group3311")
public class BlueStageLeftLinearOpMode extends LinearOpMode
{
    private boolean pixelInMiddle, pixelIsLeft, pixelIsRight;



    /** Drive control */
    MecanumSynchronousDriver driver;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
    enum zone
    {
        middle,
        left,
        right
    }
    private zone current = null;

     //private enum zone
    {
       // center,
        //left,
       // right
    }



    @Override
    public void runOpMode() throws InterruptedException {
        switch (current) {
            case middle:
                planAlpha();
                break;
            case right:
                right;
                break;
            case left:
                left;
                break;
            default:
                planBeta();
                break;
        }
         public void right(){
            driver.forward(23,1,0.6);
            driver.turn(30,1,0.6);
            driver.forward(2,1,0);
            driver.forward0,0
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
        planAlpha();
        //planBeta();

        //Sample Test Programs
        //aroundyTest();
        //rotateTest();


        while (opModeIsActive())
        {

        }
    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha()
    {
        //So to start you off, you are going to want to push the pixel to the second line.  So, a
        //forward command is in order.  You will need to measure the distance.  From there you will
        //probably need to back up a bit before rotating to your end goal.  I will let you figure
        //out what route that will be.

        //Go forward 24 inches at speed of .5  (24 is just a filler.  you need to figure out how far it is

        // Drive 25 inches forward
       driver.forward(25,1,0.6);

       // Sleeps for 500 milisecond to make sure the robot is not turning
        sleep(500);

        // Dives backard 5 inches

        driver.forward(5,-1,0.6);

        sleep(500);

        // Turns 90 degrees left
        driver.turn(90, -1, .4);

        sleep(500);

        // After turning 90 degree drivebase drives 31 inches
        driver.forward(31, 1, 0.6);



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



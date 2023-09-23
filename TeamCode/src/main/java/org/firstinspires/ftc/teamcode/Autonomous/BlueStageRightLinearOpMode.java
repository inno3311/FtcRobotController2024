package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends LinearOpMode
{

    /** Drive control */
    MecanumSynchronousDriver driver;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
    private boolean pixelIsLeft, pixelIsRight;

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

        //Change this to pixelIsLeft for left, pixelIsRight for right, or delete completely for middle
       pixelIsLeft = true;

        //Your code goes in this function.   You can make other plans as well.  (two shells are
        //provided.
        if(pixelIsLeft){
            //Put either pixelLeft(); or pixelLeftBeta(); here
           pixelLeftBeta();
        } else if (pixelIsRight){
            pixelRight();
        } else planAlpha();
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
        
       //Go forward 24 inches at speed of .5  (24 is just a filler.  you need to figure out how far it is), then go backward
       driver.forward(25, 1, 0.6);
       driver.forward(23, -1, 0.6);
       //Turn left through the truss  
       driver.turn(90, -1, 0.4);
       driver.forward(80, 1, 0.9);
       //Turn right  
       driver.turn(90, 1, 0.4);
       driver.forward(15, 1, 0.6);
       //Turn left and go to backdrop 
       driver.turn(90, -1, 0.4);
       driver.forward(5, 1, 0.6);

    }

    public void pixelRight(){
        //Go forward just enough to turn
        driver.forward(2, 1, 0.6);
        driver.turn(30, 1, 0.4);
        //Push pixel into place
        driver.forward(12, 1, 0.6);
        //Go backward after placing pixel
        driver.forward(13, -1, 0.6);
        sleep(3000);

        //To go through truss
        driver.turn(30, -1, 0.4);
        //Turn left through the truss
        driver.turn(90, -1, 0.4);
        sleep(3000);

        //Go to the other side
        driver.forward(80, 1, 0.9);
        sleep(3000);
        //Turn right
        driver.turn(90, 1, 0.4);
        sleep(3000);


        //Turn left and go to backdrop
        driver.turn(90, -1, 0.4);
        driver.forward(15, 1, 0.6);
        driver.turn(90, -1, 0.4);
        driver.forward(5, 1, 0.5);


    }

    public void pixelLeft(){

        //Go forward just enough to turn
        driver.forward(7, 1, 0.6);
        driver.turn(30, -1, 0.4);
        //Push pixel into place
        driver.forward(12, 1, 0.6);
        //Go backward after placing pixel
        driver.forward(19, -1, 0.6);
        sleep(3000);

        //To go through truss
        driver.turn(56, -1, 0.4);

        //Turn left through the truss
        //Go to the other side
        driver.forward(60, 1, 0.9);
        sleep(3000);
        driver.turn(20, 1, 0.4);
        driver.forward(20, 1, 0.9);

        //Turn right
        driver.turn(90, 1, 0.4);
        sleep(3000);


        driver.turn(30, 1, 0.4);
        driver.forward(15, 1, 0.7);
    }

    public void pixelLeftBeta(){

        //Go forward just enough to turn
        driver.forward(7, 1, 0.6);
        driver.turn(30, -1, 0.4);
        //Push pixel into place
        driver.forward(12, 1, 0.6);
        //Go backward after placing pixel
        driver.forward(19, -1, 0.6);
        //Adjust
        driver.turn(25, 1, 0.4);

        //Drive forward (meant to go through the middle of the truss)
        driver.forward(50, 1, 0.7);
        sleep(2000);
        //Go through the middle of the truss
        driver.turn(60, -1, 0.4);
        driver.forward(46, 1, 0.7);
        sleep(3000);
        //Turn left (position into backdrop)
        driver.turn(80, -1, 0.4);
        driver.forward(70, 1, 0.7);

        //Face right
        driver.turn(60, 1, 0.4);

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



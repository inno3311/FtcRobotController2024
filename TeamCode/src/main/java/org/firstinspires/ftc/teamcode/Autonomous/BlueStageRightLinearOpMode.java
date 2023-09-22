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
 
    /**
     * There is always a plan B.  ;)
     */
    public void planBeta()
    {
        //EXAMPLE CODE (NOT TESTED AND WILL BE CHANGED IN THE FUTURE)
        //ROBOT IS SUPPOSED TO PLACE THE PIXEL, TURN, THEN EVENTUALLY GO THROUGH THE RIGHT PART OF THE TRUSS AND INTO THE BACKDROP

        //Place the pixel
        driver.forward(25, 1, 0.6);
        //Try to get out of the way of the pixel
        driver.turn(90, 1, 0.4);
        driver.forward(9, 1, 0.3);
        //Turn left (current destination is through the right of the truss)
        driver.turn(90, -1, 0.4);
        //Drive forward (current destination is through the right of the truss)
        driver.forward(21, 1, 0.7);
        //Turn left (through the right of the truss) and into the backdrop
        driver.turn(90, -1, 0.4);
        driver.forward(50, 1, 0.8);

    }

    public void planGamma(){//Because there were 22 minutes left in the meeting and I wanted something to do (:

        //ENTIRE METHOD LIKELY TO BE DELETED

        //Place the pixel
        driver.forward(25, 1, 0.6);
        //Try to get out of the way of the pixel
        driver.turn(90, 1, 0.4);
        driver.forward(9, 1, 0.3);
        //Turn left (current destination is through the middle of the truss)
        driver.turn(90, -1, 0.4);
        //Drive forward (current destination is through the middle of the truss)
        driver.forward(11, 1, 0.7);
        //Turn left through the truss
        driver.turn(90, -1, 0.4);
        driver.forward(40, 1, 0.7);
        //Turn to the left backdrop
        driver.turn(90, -1, 0.4);
        driver.forward(20, 1, 0.6);
        //Into the backdrop
        driver.turn(90, 1, 0.4);
        driver.forward(6, 1, 0.5);

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



package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends LinearOpMode
{
    WebCamHardware webcam;

    /** Drive control */
    MecanumSynchronousDriver driver;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
    private boolean pixelInMiddle, pixelIsLeft, pixelIsRight;

    zoneEnum zone;

 enum zoneEnum
    {
        center,
        left,
        right
    }

    /*
    if (stuff right here to determine which zone it goes to)
    {
        zone current = zone.x; x = center, right, or left
    }
     switch(current)
     {
     case center:
        planAlpha();
        break;
     case right:
        pixelRight();
        break;
     case left:
        pixelLeft();
        break;
    default:
        planBeta(false,true,false);
        break;
    }*/


    @Override
    public void runOpMode() throws InterruptedException
    {

        try
        {
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);
            webcam = new WebCamHardware(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        webcam.initTfod();


        Recognition rec = null;
        while ((rec = webcam.findObject()) == null)
        {
            telemetry.addData("- Camera", "Looking for object");
            telemetry.update();
        }

        double x = (rec.getLeft() + rec.getRight()) / 2 ;
        double y = (rec.getTop()  + rec.getBottom()) / 2 ;

        if(x > 50 && x < 150)
        {
            telemetry.addData("Left", x);
            zone = zoneEnum.left;
        }
        else if(x > 160 && x < 450) {
            telemetry.addData("Center", x);
            zone = zoneEnum.center;
        }
        else if(x > 460 && x < 600){
            telemetry.addData("Right", x);
            zone = zoneEnum.right;
        }
        else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");

        telemetry.addData(""," ");
        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
        telemetry.addData("- Position", "%.0f / %.0f", x, y);
        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
        telemetry.update();

        waitForStart();
        start();

        //Change this to pixelIsLeft = true for left, pixelIsRight = true for right, or pixelInMiddle for middle
        pixelIsLeft = true;

        switch(zone) {
            case center:
                planAlpha();
                break;
            case right:
                pixelRight();
                break;
            case left:
                pixelLeft();
                break;
            default:
                planBeta(false, true, false);
                break;
        }

        /*
        //Your code goes in this function.   You can make other plans as well.  (two shells are
        //provided.
        if(pixelInMiddle){
            //Put planAlpha(); here
            planAlpha();
        }
        if(pixelIsLeft){
            //Put pixelLeft(); here
            pixelLeft();
        } else if (pixelIsRight){
            pixelRight();
        } else planBeta(false, true, false); //(I know putting all three instances as parameters isn't
        //best practice, but [for now at least] I wanted to put all the beta instances in the same place)
        //Only one parameter can be set to true.

        //Sample Test Programs
        //aroundyTest();
        //rotateTest();
*/
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

    public void pixelRight() {
        //Go forward just enough to turn
        driver.forward(2, 1, 0.6);
        driver.turn(30, 1, 0.4);
       // driver.rotateOd(30, .4);

        //Push pixel into place
        driver.forward(12, 1, 0.6);
        //Go backward after placing pixel
        driver.forward(13, -1, 0.6);
        sleep(3000);

        //To go through truss
        driver.turn(25, -1, 0.4);
        //Turn left through the truss
        driver.turn(90, -1, 0.4);
        //driver.rotateOd(-120, .4);
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

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta(boolean beta, boolean leftBeta, boolean rightBeta)
    {

        /*THESE ARE PLAN BETA INSTANCES FOR ALL THREE INSTANCES. SET beta TO true FOR THE PLAN BETA
          FOR THE MIDDLE INSTANCE (PIXEL IN MIDDLE). SET leftBeta TO true FOR THE PLAN BETA
          LEFT INSTANCE. SET rightBeta TO true FOR THE PLAN  BETA RIGHT INSTANCE.

          ONLY ONE INSTANCE CAN BE TRUE (This goes without saying, but I said it anyway (: )

         */
        if(beta){
            //BETA INSTANCE IF PIXEL IS IN THE MIDDLE
            //Go forward 24 inches at speed of .5  (24 is just a filler.  you need to figure out how far it is), then go backward
            driver.forward(25, 1, 0.6);
            driver.forward(23, -1, 0.6);
            //Turn right (out of the way of the pixel)
            driver.turn(90, 1, 0.4);
            driver.forward(8, 1, 0.9);
            //Turn left
            driver.turn(90, -1, 0.4);
            driver.forward(25, 1, 0.6);
            //Turn left and go to backdrop
            driver.turn(90, -1, 0.4);
            driver.forward(40, 1, 0.6);
            driver.turn(90, -1, 0.4);
            driver.forward(15, 1, 0.8);

        }

        if(leftBeta){
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

        if(rightBeta){
            //Go forward just enough to turn
            driver.forward(2, 1, 0.6);
            driver.turn(30, 1, 0.4);
            //Push pixel into place
            driver.forward(12, 1, 0.6);
            //Go backward after placing pixel (for space only)
            driver.forward(3, -1, 0.6);
            sleep(3000);

            //Get out of the way of the pixel
            driver.turn(30, -1, 0.4);
            //Go to the middle
            driver.forward(17, 1, 0.8);
            sleep(3000);

            //Go to the other side
            driver.turn(90, -1, 0.4);
            sleep(3000);
            //Through the truss
            driver.forward(20, 1, 0.7);
            sleep(3000);

            //Turn left and go to backdrop
            driver.turn(90, -1, 0.4);
            driver.forward(15, 1, 0.6);

        }

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


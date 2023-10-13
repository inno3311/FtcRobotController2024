package org.firstinspires.ftc.teamcode.Autonomous;

import android.widget.VideoView;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends LinearOpMode
{
    WebCamHardware webcam;

    /** Drive control */
    MecanumSynchronousDriver driver;
    AprilTagMaster aprilTagMaster;
    InitAprilTags initAprilTags;
    DriveToTag driveToTag;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
    private boolean pixelInMiddle, pixelIsLeft, pixelIsRight;

    zoneEnum zone = null;

    enum zoneEnum
    {
        left,
        center,
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
            initAprilTags = new InitAprilTags();
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

        if(x <= 159)
        {
            //Range for left 50-150
            telemetry.addData("Left", x);
            zone = zoneEnum.left;
        }
        else if(x > 160 && x <= 459){
            //Range for the center 160 - 459
            telemetry.addData("Center", x);
            zone = zoneEnum.center;
        }
        else if(x >= 460){
            //Range for the right
            telemetry.addData("Right", x);
            zone = zoneEnum.right;
        }
        else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");

        telemetry.addData(""," ");
        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
        telemetry.addData("- Position", "%.0f / %.0f", x, y);
        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
        telemetry.update();


        initAprilTags.initAprilTags(webcam, driver, hardwareMap, telemetry);
        aprilTagMaster = initAprilTags.getAprilTagMaster();
        driveToTag = initAprilTags.getDriveToTag();
        waitForStart();
        start();

        //Change this to pixelIsLeft = true for left, pixelIsRight = true for right, or pixelInMiddle for middle
        pixelIsLeft = true;

        switch(zone){
            case center:
                telemetry.addData("Center detected", "");
                planBeta(true, false, false);
              //  planAlpha();
                break;
            case right:
                planBeta(false, false, true);
                //pixelRight();
                break;
            case left:
                planBeta(false, true, false);
                //pixelLeft();
                break;
            default:
                planBeta(false, true, false);//(I know putting all three instances as parameters isn't
                //best practice, but [for now at least] I wanted to put all the beta instances in the same place)
                //Only one parameter can be set to true.
                break;
        }
        //TODO We need to make this work for red side to because red uses targets (AprilTag Ids) 4-6
        //ordinal returns an int +1 because it starts counting at 0
        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);

        telemetry.addData("Finished", "");
        telemetry.update();
        sleep(10000);
        /*
        //Your code goes in this function. You can make other plans as well.  (two shells are
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
        } else planBeta(false, true, false);

        //Sample Test Programs
        //aroundyTest();
        //rotateTest();
*/
        stop();
    }

   /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha()
    {

       //Go forward 24 inches at speed of .5  (24 is just a filler.  you need to figure out how far it is), then go backward
       driver.forward(25, 1, 0.6);
       sleep(500);
       driver.forward(23, -1, 0.6);
        sleep(500);
       //Turn left through the truss  
       driver.turn(90, -1, 0.4);
        sleep(500);
       driver.forward(70, 1, 0.9);
        sleep(500);
       //Turn right  
       driver.turn(90, 1, 0.4);
        sleep(500);
       driver.forward(20, 1, 0.6);
        sleep(500);
       //Turn left and go to backdrop 
       driver.turn(90, -1, 0.4);
       //driver.forward(5, 1, 0.6);

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
        driver.turn(30, -1, 0.4);
        //Turn left through the truss
        driver.turn(90, -1, 0.4);
        //driver.rotateOd(-120, .4);
        sleep(3000);
        
        //Go to the other side
        driver.forward(70, 1, 0.9);
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
        driver.forward(70, 1, 0.9);
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
    public void planBeta(boolean centerBeta, boolean leftBeta, boolean rightBeta)
    {

        /*THESE ARE PLAN BETA INSTANCES FOR ALL THREE INSTANCES. SET beta TO true FOR THE PLAN BETA
          FOR THE MIDDLE INSTANCE (PIXEL IN MIDDLE). SET leftBeta TO true FOR THE PLAN BETA
          LEFT INSTANCE. SET rightBeta TO true FOR THE PLAN  BETA RIGHT INSTANCE.

          ONLY ONE INSTANCE CAN BE TRUE (This goes without saying, but I said it anyway (: )

         */
        if(centerBeta){
            //BETA INSTANCE IF PIXEL IS IN THE MIDDLE

            //Go forward 25 in
            driver.forward(25, 1, 0.6);

            //Go backward 12 in for space to turn
            driver.forward(12, -1, 0.6);

            //sleep
            sleep(1000);

            //Turn right 30° (out of the way of the pixel)
            driver.turn(30, 1, 0.4);

            //Forward after turning to go into position for going through truss
            driver.forward(20, 1, 0.9);

            sleep(1000);

            //Turn left
            driver.turn(30, -1, 0.4);

            //Continue to go into position
            driver.forward(13, 1, 0.6);

            sleep(1000);

            //Turn left to go through truss
            driver.turn(90, -1, 0.4);

            //Go through truss
            driver.forward(80, 1, 0.6);

            sleep(1000);

            //Turn left once through truss for next command
            driver.turn(90, -1, 0.4);

            //Go forward into position
            driver.forward(24, 1, 0.8);

            sleep(1000);

            //Face right and let AprilTag take over
            driver.turn(90, 1, 0.4);


        }

        if(leftBeta){
            //Go forward just enough to turn
            driver.forward(7, 1, 0.6);
            driver.turn(60, -1, 0.4);
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

    public void planPurple(boolean targetIsCenter, boolean targetIsLeft, boolean targetIsRight){
        /***
            This is a backup to the backup. Different initial position and
            different "parking" position for flexibility.
        ***/


        //Following line remains true for all three instances...
        //Go forward to determine whether object is left/center/right
        driver.forward(20, 1, 0.6);

        sleep(1000);

        //...then calls one of the if statements      
                
        //If target is in the center...
        if(targetIsCenter) {

            //Go forward and place pixel
            driver.forward(4, 1, 0.5);

            sleep(1000);

            //Go backward into position
            driver.forward(5, -1, 0.6);


        }

        //If target is on the left...
        if(targetIsLeft){

            //Face left
            driver.turn(40, -1, 0.4);
                        
            //Go forward and place pixel
            driver.forward(4, 1, 0.5);

            sleep(1000);

            //Turn back
            driver.turn(40, 1, 0.4);

            sleep(1000);
            
            //Go backward into position
            driver.forward(5, -1, 0.6);

        }

        if(targetIsRight){
            //Face right
            driver.turn(40, 1, 0.4);
                        
            //Go forward and place pixel
            driver.forward(4, 1, 0.5);

            sleep(1000);

            //Turn back
            driver.turn(40, -1, 0.4);

            sleep(1000);
            
            //Go backward into position
            driver.forward(5, -1, 0.6);


        }

        //Wait for next command...
        sleep(1000);
        

        
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

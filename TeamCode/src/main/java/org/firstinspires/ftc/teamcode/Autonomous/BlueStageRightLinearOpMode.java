package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Autonomous.LeftRightSuper.SpikeLineEnum.CENTER_SPIKE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends LeftRightSuper
{


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
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        try
        {
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
        while ((rec = webcam.findObject()) == null)
        {
            telemetry.addData("- Camera", "Looking for object");
            telemetry.update();
        }

        double x = (rec.getLeft() + rec.getRight()) / 2 ;
        double y = (rec.getTop()  + rec.getBottom()) / 2 ;

        String getXPosition = webcam.findTarget(x);
        if(getXPosition.equals("left")){
            telemetry.addData("Left", x);
            zone = SpikeLineEnum.LEFT_SPIKE;
        }else if (getXPosition.equals("center")){
            telemetry.addData("Center", x);
            zone = SpikeLineEnum.CENTER_SPIKE;
        } else if (getXPosition.equals("right")){
            telemetry.addData("Right", x);
            zone = SpikeLineEnum.RIGHT_SPIKE;
        } else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");
//
//        if(x <= 160)
//        {
//            //Range for left 50-150
//            telemetry.addData("Left", x);
//            zone = SpikeLineEnum.LEFT_SPIKE;
//        }
//        else if(x > 160 && x <= 459){
//            //Range for the center 160 - 459
//            telemetry.addData("Center", x);
//            zone = SpikeLineEnum.CENTER_SPIKE;
//        }
//        else if(x >= 460){
//            //Range for the right
//            telemetry.addData("Right", x);
//            zone = SpikeLineEnum.RIGHT_SPIKE;
//        }


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


        switch(zone){
            case CENTER_SPIKE:
                telemetry.addData("Center detected", "");
//                pixelInCenter = true;
//                try
//                {
////                    planAlpha();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
                try {
                    planBeta(zone);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  planAlpha();
                break;
            case RIGHT_SPIKE:
                try {
                    planBeta(zone);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                pixelIsRight = true;
//                try
//                {
//                    planAlpha();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
                //pixelRight();
                break;
            case LEFT_SPIKE:
//                pixelIsLeft = true;
             //   try
//                {
//                    planAlpha();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
                try {
                    planBeta(zone);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //pixelLeft();
                break;
            default:
                try {
                    planBeta(zone); //Only one parameter can be set to true.
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


        //Sample Test Programs
        //aroundyTest();
        //rotateTest();

        stop();
    }
*/

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha() throws IOException, InterruptedException
    {
        planPurple(zone, false);
        sleep(1000);

         //Turn left
        //driver.turn(88, -1, 0.4);
        driver.rotate(-90, imuControl);
        sleep(1000);

        //Go through truss
        driver.forward(60, 1, 0.6);
        sleep(1000);
        //Turn right
        //driver.turn(90, 1, 0.4);
        driver.rotate(90, imuControl);

        driver.forward(16, 1, 0.5);

        //Left and let AprilTag take over
        driver.rotate(-90, imuControl);


      //Go left through truss
//      driver.rotate(-35, imuControl);
//      driver.forward(25, 1, 0.6);
//
//      //turn right
//      driver.rotate(40, imuControl);
//
//      driver.forward(12, 1, 0.5);
//
//      driver.rotate(-35, imuControl);


    }

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta(SpikeLineEnum zone) throws IOException, InterruptedException {

        /*THESE ARE PLAN BETA INSTANCES FOR ALL THREE INSTANCES. SET beta TO true FOR THE PLAN BETA
          FOR THE MIDDLE INSTANCE (PIXEL IN MIDDLE). SET leftBeta TO true FOR THE PLAN BETA
          LEFT INSTANCE. SET rightBeta TO true FOR THE PLAN  BETA RIGHT INSTANCE.

          ONLY ONE INSTANCE CAN BE TRUE (This goes without saying, but I said it anyway (: )

         */

//        planPurple(zone, true);

        if(zone == SpikeLineEnum.CENTER_SPIKE){
            //Beta instance if object is in the middle
//
//            //Go forward 25 in
            driver.forward(24, 1, 0.6);

            sleep(1000);
//
//            //Go backward 12 in for space to turn
            driver.forward(3, -1, 0.6);

            //sleep
            sleep(1000);

            driver.strafe(12, 1, 0.6, imuControl);

            sleep(1000);

            //Turn right 30° (out of the way of the pixel)
            //driver.turn(30, 1, 0.4);
//            driver.rotate(45, imuControl);

            //           sleep(1000);

            //Forward after turning to go into position for going through truss
//            driver.forward(24, 1, 0.6);

//            sleep(1000);

            //Turn left
//            driver.rotate(-45, imuControl);
            //driver.turn(30, -1, 0.4);

//            sleep(1000);

            //Continue to go into position
            driver.forward(28, 1, 0.6);

            sleep(1000);

            //Turn left to go through truss
            //driver.turn(90, -1, 0.4);
            driver.rotate(-90, imuControl);

            //Go through truss
            driver.forward(80, 1, 0.6);

            sleep(1000);

            //Turn left once through truss for next command
            //driver.turn(90, -1, 0.4);
            driver.rotate(-90, imuControl);

            //Go forward into position
            driver.forward(24, 1, 0.8);

            sleep(1000);

            //Face right and let AprilTag take over
            //driver.turn(90, 1, 0.4);
            driver.rotate(90, imuControl);
        }

        if(zone == SpikeLineEnum.LEFT_SPIKE )
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            sleep(1000);

            //driver.turn(45, -1, 0.4);
            driver.rotate(-45, imuControl);

            sleep(1000);

            //Push pixel into place
            driver.forward(4, 1, 0.6);

            sleep(1000);

            //Go backward after placing pixel
            driver.forward(4, -1, 0.6);

            sleep(1000);

            //Adjust
            //driver.turn(45, 1, 0.4);
            driver.rotate(45, imuControl);

            //Drive forward (meant to go through the middle of the truss)
            driver.forward(24, 1, 0.7);
            sleep(1000);
            //Go through the middle of the truss
            //driver.turn(52, -1, 0.4);
            driver.rotate(-90, imuControl);
            sleep(1000);
            driver.forward(46, 1, 0.7);
            sleep(1000);
            //Turn left (position into backdrop)
//            driver.turn(80, -1, 0.4);
//            driver.forward(70, 1, 0.7);

            //Face right
//            driver.turn(60, 1, 0.4);

        }

        if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
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



    //This is code for controlling what happens if obj
    public void planPurple(SpikeLineEnum zone, boolean beta) throws IOException, InterruptedException
    {
        /***
            This is the starting code for if the object is on the left/center/right.
            PlanAlpha or planBeta to follow.
        ***/

        sleep(1000);

        //...then calls one of the if statements

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {

            //Go forward to determine whether object is left/center/right
            driver.forward(24, 1, 0.6);
            //Go forward and place pixel
            //driver.forward(4, 1, 0.5);

            sleep(1000);

            //Go backward into position
            driver.forward(19, -1, 0.6);

        }

        //If target is on the left...
       else if(zone == SpikeLineEnum.LEFT_SPIKE)
       {

            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            //driver.turn(45, -1, 0.4);
            driver.rotate(-45, imuControl);

            //Push pixel into place
            driver.forward(7, 1, 0.6);

            sleep(1000);

            //Go backward after placing pixel
            driver.forward(7, -1, 0.6);

            //Adjust
            //driver.turn(45, 1, 0.4);
            driver.rotate(45, imuControl);

            driver.forward(15, -1, 0.5);

        }

        else if(zone == SpikeLineEnum.RIGHT_SPIKE){
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            sleep(1000);

            //driver.turn(45, -1, 0.4);
            driver.rotate(45, imuControl);

            sleep(1000);

            //Push pixel into place
            driver.forward(6, 1, 0.6);

            sleep(1000);

            //Go backward after placing pixel
            driver.forward(6, -1, 0.6);

            sleep(1000);

            //Adjust
            //driver.turn(45, 1, 0.4);
            driver.rotate(-45, imuControl);

            driver.forward(15, -1, 0.5);


        }

        if (beta){



        }


        //Wait for next command...
        sleep(1000);

//        //Turn left
//        driver.turn(88, -1, 0.4);
//
//        //Go through truss
//        driver.forward(60, 1, 0.6);
//
//        //Turn right
//        driver.turn(90, 1, 0.4);


    }



}

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

    /** Drive control */
    MecanumSynchronousDriver driver;
    AprilTagMaster aprilTagMaster;
    InitAprilTags initAprilTags;
    DriveToTag driveToTag;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;
    enum zone
    {
        middle,
        left,
        right
    }
    private zone current = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try {
            planGamma(zone);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);
    }

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


    }

    /**
     * There is always a plan B.  ;)
     */
    public void planGamma(SpikeLineEnum zone) throws IOException, InterruptedException {



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
                driver.forward(4, 1, 0.6);

                sleep(1000);

                //Go backward after placing pixel
                driver.forward(4, -1, 0.6);

                //Adjust
                //driver.turn(45, 1, 0.4);
                driver.rotate(-45, imuControl);
                sleep(1000);
                driver.forward(15, 1, 0.5);

            }

            else if(zone == SpikeLineEnum.RIGHT_SPIKE)
            {
                //Go forward just enough to turn
                driver.forward(17, 1, 0.6);

                sleep(1000);

                //driver.turn(45, -1, 0.4);
                driver.rotate(45, imuControl);

                sleep(1000);

                //Push pixel into place
                driver.forward(5, 1, 0.6);

                sleep(1000);

                //Go backward after placing pixel
                driver.forward(5, -1, 0.6);

                sleep(1000);

                //Adjust
                //driver.turn(45, 1, 0.4);
                driver.rotate(-125, imuControl);

                sleep(1000);

                driver.forward(15, 1, 0.5);

                sleep(1000);

                driver.strafe(15,1, 0.6, imuControl);

            }


        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);


    }

}



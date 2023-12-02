package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.IntakeChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

public class AutonomousBase extends LinearOpMode
{
    final int blue = 1;
    final int red = -1;
    public int isBlue = red; //Red is negative!
    public final int DELAY = 500;

    public boolean robotIsMoving = true;

    WebCamHardware webcam;


    protected ImuHardware imuControl;

    /** Drive control */
    protected MecanumSynchronousDriver driver;
    AprilTagMaster aprilTagMaster;
    InitAprilTags initAprilTags;
    DriveToTag driveToTag;


    LinerSlideChild linerSlideChild;
    TransferRight transferRight;
    TransferLeft transferleft;
    HeightChild heightChild;
    IntakeChild intakeChild;

    SpikeLineEnum zone = SpikeLineEnum.UNKNOWN;

    public enum SpikeLineEnum
    {
        LEFT_SPIKE,
        CENTER_SPIKE,
        RIGHT_SPIKE,
        UNKNOWN
    }

    protected void initMembers()
    {
        try
        {
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);
            webcam = new WebCamHardware(this);
            imuControl = new ImuHardware(this);
            initAprilTags = new InitAprilTags();



            linerSlideChild = new LinerSlideChild(this);
            sleep(DELAY);
            transferRight = new TransferRight(this);
            sleep(DELAY);
            transferleft = new TransferLeft(this);
            sleep(DELAY);
            heightChild = new HeightChild(this);
            sleep(DELAY);
            intakeChild = new IntakeChild(this);
            sleep(DELAY);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        initMembers();

        webcam.initTfod();


        Recognition rec = null;
        while ((rec = webcam.findObject()) == null)
        {
            telemetry.addData("- Camera", "Looking for object");
            telemetry.update();
        }

        double x = (rec.getLeft() + rec.getRight()) / 2 ;
        double y = (rec.getTop()  + rec.getBottom()) / 2 ;

        zone = webcam.findTarget(x);

        telemetry.addData(""," ");
        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
        telemetry.addData("- Position", "%.0f / %.0f", x, y);
        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
        telemetry.update();

        waitForStart();

//        telemetry.addData(""," ");
//        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
//        telemetry.addData("- Position", "%.0f / %.0f", x, y);
//        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
//        telemetry.update();

        initAprilTags.initAprilTags(webcam, driver, hardwareMap, telemetry);
        aprilTagMaster = initAprilTags.getAprilTagMaster();
        driveToTag = initAprilTags.getDriveToTag();


        start();

        //TODO We need to make this work for red side to because red uses targets (AprilTag Ids) 4-6
        //ordinal returns an int +1 because it starts counting at 0
    }

    public void goToPixel() {
        PlanAlpha blueStage = new PlanAlpha();
        switch (zone) {
            case CENTER_SPIKE:
                telemetry.addData("Center detected", "");

        }
    }




    //This is code for controlling what happens if obj
    public void planPurple(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        /***
         This is the starting code for if the object is on the left/center/right.
         PlanAlpha or planBeta to follow.
         ***/

        //...then calls one of the if statements

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            //Go forward to determine whether object is left/center/right
            driver.forward(27, 1, 0.6);
            //Go forward and place pixel
            
            //Go backward into position
            driver.forward(22, -1, 0.6);

        }


        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            sleep(DELAY);

            driver.rotate2(45 * isBlue, imuControl);

            //Push pixel into place
            driver.forward(5, 1, 0.6);

            sleep(DELAY);

            //Go backward after placing pixel
            driver.forward(5, -1, 0.6);
            sleep(DELAY);


            //Adjust (right)
            driver.rotate2(-45*isBlue, imuControl);

            sleep(DELAY);
            //Go backward into position
            driver.forward(17, -1, 0.6);

        }

        else if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            sleep(DELAY);

            driver.rotate2(-45*isBlue, imuControl);

            sleep(DELAY);

            //Push pixel into place
            driver.forward(6, 1, 0.6);

            sleep(DELAY);

            //Go backward after placing pixel
            driver.forward(6, -1, 0.6);

            sleep(DELAY);

            //Adjust (left)
            driver.rotate2(45*isBlue, imuControl);

            //Go back
            driver.forward(17, -1, 0.6);
        }

        //Wait for next command...
        sleep(DELAY);

    }

    public void parkRobot(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {

        double defaultSpeed = 0.6;
        int defaultWaitTime = 5;

        //TODO maybe: Add variables for adding/subtracting for more reusable code
        driver.forward(2, -1, defaultSpeed, 5);
        if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {

            if(isBlue == 1) driver.strafe(27, -isBlue, defaultSpeed, imuControl);
            else if (isBlue == -1) driver.strafe(32, isBlue, defaultSpeed, imuControl, 5);

        }
        else if(zone == SpikeLineEnum.CENTER_SPIKE) {
            //if(isBlue == 1)
                if(blue ==1) driver.strafe(25, -isBlue, defaultSpeed, imuControl);
                else if (isBlue == -1){
                    driver.strafe(24, -isBlue, defaultSpeed, imuControl);
                    driver.rotate2(5, imuControl, 3);
                }
        }
        else if (zone == SpikeLineEnum.LEFT_SPIKE)
        {

            if(isBlue == 1) driver.strafe(16, -isBlue, defaultSpeed, imuControl, defaultWaitTime);
            else if(isBlue == -1) driver.strafe(28, -isBlue, defaultSpeed, imuControl, defaultWaitTime);

        }

        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            if(isBlue == 1) {
                // driver.forward(8, 1, defaultSpeed, defaultWaitTime);
                 driver.forward(11, 1, defaultSpeed, defaultWaitTime);
            }
            else if (isBlue == -1)
            {
               // driver.forward(8, 1, defaultSpeed, defaultWaitTime);
                 driver.forward(12, 1, defaultSpeed, defaultWaitTime);
            }

        } else if(zone == SpikeLineEnum.LEFT_SPIKE){

            if(isBlue == 1){
                // driver.forward(8, 1, defaultSpeed, defaultWaitTime);
                 driver.forward(10, 1, defaultSpeed, defaultWaitTime);
            }
            else if(isBlue == -1){
                // driver.forward(8, 1, defaultSpeed, defaultWaitTime);
                 driver.forward(11, 1, defaultSpeed, defaultWaitTime);
            }

        } else if(zone == SpikeLineEnum.RIGHT_SPIKE){

            if(isBlue == 1) driver.forward(10, 1, defaultSpeed, defaultWaitTime);

        }else driver.forward(8, 1, defaultSpeed, defaultWaitTime);

    }

}


package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

public class AutonomousBase extends LinearOpMode {

    public int isBlue = -1; //Red is negative!

    public int DELAY = 500;

    WebCamHardware webcam;

    ImuHardware imuControl;

    /** Drive control */
    MecanumSynchronousDriver driver;
    AprilTagMaster aprilTagMaster;
    InitAprilTags initAprilTags;
    DriveToTag driveToTag;

    SpikeLineEnum zone = SpikeLineEnum.UNKNOWN;

    public enum SpikeLineEnum
    {
        LEFT_SPIKE,
        CENTER_SPIKE,
        RIGHT_SPIKE,
        UNKNOWN
    }

    public AutonomousBase() {

    }

    protected void initMembers()
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

        telemetry.addData(""," ");
        telemetry.addData("Image", "%s (%.0f %% Conf.)", rec.getLabel(), rec.getConfidence() * 100);
        telemetry.addData("- Position", "%.0f / %.0f", x, y);
        telemetry.addData("- Size", "%.0f x %.0f", rec.getWidth(), rec.getHeight());
        telemetry.update();

        initAprilTags.initAprilTags(webcam, driver, hardwareMap, telemetry);
        aprilTagMaster = initAprilTags.getAprilTagMaster();
        driveToTag = initAprilTags.getDriveToTag();

        start();

        //TODO We need to make this work for red side to because red uses targets (AprilTag Ids) 4-6
        //ordinal returns an int +1 because it starts counting at 0
    }

    public void goToPixel() {
        StageRightLinearOpMode blueStage = new StageRightLinearOpMode();
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
            driver.forward(24, 1, 0.6);
            //Go forward and place pixel
            
            //Go backward into position
            driver.forward(19, -1, 0.6);

        }

        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            driver.rotate2(-45*isBlue, imuControl);

            //Push pixel into place
            driver.forward(7, 1, 0.6);

            sleep(1000);

            //Go backward after placing pixel
            driver.forward(7, -1, 0.6);

            //Adjust
            driver.rotate2(45*isBlue, imuControl);

            driver.forward(15, -1, 0.5);

        }

        else if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            sleep(1000);

            driver.rotate2(-45*isBlue, imuControl);

            sleep(1000);

            //Push pixel into place
            driver.forward(6, 1, 0.6);

            sleep(1000);

            //Go backward after placing pixel
            driver.forward(6, -1, 0.6);

            sleep(1000);

            //Adjust (left)
            driver.rotate2(45*isBlue, imuControl);



        }

        //Wait for next command...
        sleep(1000);

    }

}


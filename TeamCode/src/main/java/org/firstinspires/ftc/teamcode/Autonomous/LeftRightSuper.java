package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;

import java.io.IOException;

public class LeftRightSuper extends LinearOpMode {

    WebCamHardware webcam;

    ImuHardware imuControl;

    /** Drive control */
    MecanumSynchronousDriver driver;
    AprilTagMaster aprilTagMaster;
    InitAprilTags initAprilTags;
    DriveToTag driveToTag;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;


    SpikeLineEnum zone = null;

    enum SpikeLineEnum
    {
        LEFT_SPIKE,
        CENTER_SPIKE,
        RIGHT_SPIKE
    }

    public LeftRightSuper() {


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
            zone = BlueStageRightLinearOpMode.SpikeLineEnum.LEFT_SPIKE;
        }else if (getXPosition.equals("center")){
            telemetry.addData("Center", x);
            zone = BlueStageRightLinearOpMode.SpikeLineEnum.CENTER_SPIKE;
        } else if (getXPosition.equals("right")){
            telemetry.addData("Right", x);
            zone = BlueStageRightLinearOpMode.SpikeLineEnum.RIGHT_SPIKE;
        } else telemetry.addData("OBJECT NOT DETECTED. ADJUST VALUES", "");

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



        //TODO We need to make this work for red side to because red uses targets (AprilTag Ids) 4-6
        //ordinal returns an int +1 because it starts counting at 0
        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);

        telemetry.addData("Finished", "");
        telemetry.update();
        sleep(10000);

    }

    public void goToPixel(){
        BlueStageRightLinearOpMode blueStage = new BlueStageRightLinearOpMode();
        switch(zone){
            case CENTER_SPIKE:
                telemetry.addData("Center detected", "");

                try {
                    blueStage.planBeta(zone);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                //  planAlpha();
                break;
            case RIGHT_SPIKE:
                try {
                    blueStage.planBeta(zone);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case LEFT_SPIKE:
                try {
                   blueStage.planBeta(zone);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                   blueStage.planBeta(zone);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

}


package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends LeftRightSuper
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planBeta(zone);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);

//        try {
//         planBeta(zone);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        //  planAlpha();
//        break;
//        case RIGHT_SPIKE:
//        try {
//            blueStage.planBeta(zone);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        break;
//        case LEFT_SPIKE:
//        try {
//            blueStage.planBeta(zone);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        break;
//        default:
//        try {
//            blueStage.planBeta(zone);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        break;
//    }
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

    }

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta(SpikeLineEnum zone) throws IOException, InterruptedException {


//        planPurple(zone, true);

        if(zone == SpikeLineEnum.CENTER_SPIKE){
            //Beta instance if object is in the middle
//
            //Go forward 25 in
            driver.forward(24, 1, 0.6);

            sleep(1000);
            //Go backward 12 in for space to turn
            driver.forward(3, -1, 0.6);

            sleep(1000);

            driver.strafe(12, 1, 0.6, imuControl);

            sleep(1000);

            //Continue to go into position
            driver.forward(28, 1, 0.6);

            sleep(1000);

            //Turn left to go through truss
            //driver.turn(90, -1, 0.4);
            driver.rotate(-90, imuControl);

            //Go through truss
            driver.forward(74, 1, 0.6);

            sleep(1000);

            //Turn left once through truss for next command
            //driver.turn(90, -1, 0.4);
            driver.rotate(-90, imuControl);

            //Go forward into position
            driver.forward(17, 1, 0.8);

            sleep(1000);

            //Face right and let AprilTag take over
            //driver.turn(90, 1, 0.4);
            driver.rotate(90, imuControl);

            //let apriltag
            driver.forward(3, 1, 0.4);
        }

        if(zone == SpikeLineEnum.LEFT_SPIKE)
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
}

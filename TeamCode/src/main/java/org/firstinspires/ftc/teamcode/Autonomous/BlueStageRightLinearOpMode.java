package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Blue Stage Right", group = "Group3311")
public class BlueStageRightLinearOpMode extends AutonomousBase
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
            driver.forward(24.5, 1, 0.6);

            sleep(1000);
            //Go backward 12 in for space to turn
            driver.forward(3, -1, 0.6);

            sleep(1000);

            driver.strafe(12, 1, 0.6, imuControl);

            sleep(1000);

            //Continue to go into position
            driver.forward(28, 1, 0.6);

            sleep(1000);


            goThroughTrussAndFinish(false, false, true);

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
            driver.forward(5, -1, 0.6);

            sleep(1000);

            //Adjust
            //driver.turn(45, 1, 0.4);
            driver.rotate(45, imuControl);

            //Drive forward (meant to go through the middle of the truss)
            driver.forward(28, 1, 0.7);
            sleep(1000);
            //Go through the middle of the truss
            //driver.turn(52, -1, 0.4);


            goThroughTrussAndFinish(false, true, false);


        }

        if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(2, 1, 0.6);

            //face target
            driver.rotate(30, imuControl);
            //Push pixel into place
            driver.forward(14, 1, 0.6);
            //Go backward after placing pixel (for space only)
            driver.forward(3, -1, 0.6);
            sleep(3000);

            //Rotate for forward position
            driver.rotate(-25, imuControl);
            sleep(1000);
            //strafe left out of the way
            driver.strafe(5, -1, 0.5, imuControl);
            //Go to the middle
            driver.forward(30, 1, 0.8);

            sleep(3000);

            goThroughTrussAndFinish(false, false, true);


        }

    }

    public void goThroughTrussAndFinish(boolean center, boolean left, boolean right) throws IOException, InterruptedException {
        int goThroughTrussDistance;

        driver.rotate2(-90, imuControl);
        sleep(1000);

        //This goes to the other side
        if(left){
            goThroughTrussDistance = 70;
        } else if(center){
            goThroughTrussDistance = 80;
        } else{
            goThroughTrussDistance = 70;
        }

        driver.forward(goThroughTrussDistance, 1, 0.7);
        sleep(1000);

        //Strafe to position
        driver.strafe(17, -1, 0.5, imuControl);

    }


}

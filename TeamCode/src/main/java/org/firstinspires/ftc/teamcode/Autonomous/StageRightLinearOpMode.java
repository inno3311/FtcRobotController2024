package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Stage Right", group = "Group3311")
public class StageRightLinearOpMode extends AutonomousBase
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planBeta(zone, isBlue);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        int wallTarget = 0;
        if (isBlue == -1)
        {
            wallTarget = 3; //originally 3
        }

        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1 + wallTarget, 11, 0);

    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha() throws IOException, InterruptedException
    {
        planPurple(zone, isBlue);
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
    public void planBeta(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException {


        planPurple(zone, isBlue);

        if(zone == SpikeLineEnum.CENTER_SPIKE){
            //After planPurple...
            sleep(1000);
            
            driver.strafe(10, isBlue, 0.6, imuControl);

            sleep(1000);

            //Continue to go into position
            driver.forward(28, 1, 0.6);

            sleep(1000);


            goThroughTrussAndFinish(true, false, false, isBlue);

        }

        if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //After planPurple...
            sleep(1000);

            //Turn left to face pixel
            driver.rotate2(45*isBlue, imuControl);

            sleep(1000);

            //Push pixel into place
            driver.forward(4, 1, 0.6);

            sleep(1000);


            //Go backward after placing pixel
            driver.forward(5, -1, 0.6);

            sleep(1000);

            //Adjust (right)
            driver.rotate(-45*isBlue, imuControl);


            //Strafe barely so that robot doesn't run over pixel
            driver.strafe(0.3, -isBlue, 0.4, imuControl);

            //Drive forward (meant to go through the middle of the truss)
            driver.forward(28, 1, 0.7);
            sleep(1000);
            //Go through the middle of the truss


            goThroughTrussAndFinish(false, true, false, isBlue);


        }

        if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {

            //After planPurple code:
            driver.strafe(5, isBlue, 0.5, imuControl);
            //Go to the middle
            driver.forward(26, 1, 0.8);

            sleep(DELAY);

            goThroughTrussAndFinish(false, false, true, isBlue);


        }

    }

    public void goThroughTrussAndFinish(boolean center, boolean left, boolean right, int isBlue) throws IOException, InterruptedException {
        int goThroughTrussDistance;

        driver.rotate2(-90*isBlue, imuControl);
        sleep(1000);

        //This goes to the other side
        if(left){
            goThroughTrussDistance = 70;
        } else if(center){
            goThroughTrussDistance = 80;
        } else{
            goThroughTrussDistance = 75;
        }

        driver.forward(goThroughTrussDistance, 1, 0.7);
        sleep(1000);

        //Strafe to position
        driver.strafe(23, -isBlue, 0.5, imuControl);


    }


}

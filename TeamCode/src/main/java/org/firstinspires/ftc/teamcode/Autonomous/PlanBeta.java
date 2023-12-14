package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;

import java.io.IOException;

@Autonomous(name = "Plan Beta", group = "Group3311")
public class PlanBeta extends AutonomousBase
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

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);
        transferRight.autonomousControl(true);
        transferleft.autonomousControl(true);

        sleep(1000);

        linerSlideChild.encoderControl(-100, 0.5);

        driveToTag.drive(4, zone.ordinal() + 1 + wallTarget, 6, 0);



        heightChild.encoderControl(0,0.7);

        sleep(2000);
        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);

        sleep(2000);
    }

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            Logging.log("Spike Line is CENTER_SPIKE");
            centerRoute(isBlue);
        }
        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            Logging.log("Spike Line is LEFT_SPIKE");
            if (isBlue == 1)
            {
                stageRoute(isBlue);
            }
            else if (isBlue == -1)
            {
                wingRoute(isBlue);
            }
        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            Logging.log("Spike Line is RIGHT_SPIKE");
            if(isBlue == 1)
            {
                wingRoute(isBlue);
            }
            else if  (isBlue == -1)
            {
                stageRoute(isBlue);
            }
        }

        goThroughTrussAndFinish(false, false, true, isBlue);



//  TODO Do not delete.  FAll back to this for red only
//
//        if (zone == SpikeLineEnum.CENTER_SPIKE)
//        {
//            //Go forward and place pixel
//            driver.forward(26, 1, 0.6);
//
//            //Go back so that robot lets go of pixel
//            driver.forward(4, -1, 0.5);
//
//            //Strafe to left
//            driver.strafe(10, isBlue, 0.6, imuControl);
//
//            //Continue to go into position
//            driver.forward(29, 1, 0.6);
//
//            goThroughTrussAndFinish(true, false, false, isBlue);
//
//        }
//        if (zone == SpikeLineEnum.LEFT_SPIKE)
//        {
//            //Go forward just enough to turn
//            driver.forward(17, 1, 0.6);
//
//            //Turn left to face pixel
//            driver.rotate2(45 * isBlue, imuControl);
//
//            //Push pixel into place
//            driver.forward(4, 1, 0.6);
//
//            //Go backward after placing pixel
//            driver.forward(5, -1, 0.6);
//
//            //Adjust (right)
//            driver.rotate2(-45 * isBlue, imuControl);
//
//            //Strafe barely so that robot doesn't run over pixel
//            driver.strafe(0.3, -isBlue, 0.4, imuControl);
//
//            //Drive forward (meant to go through the middle of the truss)
//            driver.forward(27.5, 1, 0.7);
//
//
//
//            //Go through the middle of the truss
//            goThroughTrussAndFinish(false, true, false, isBlue);
//
//        }
//
//        if (zone == SpikeLineEnum.RIGHT_SPIKE)
//        {
//
//            //Go forward
//            driver.forward(17, 1, 0.6);
//
//            //Turn (right) to place pixel
//            driver.rotate2(-45 * isBlue, imuControl);
//
//            //Place pixel
//            driver.forward(5, 1, 0.6);
//
//            //Go back
//            driver.forward(5, -1, 0.6);
//
//            //Adjust (left)
//            driver.rotate2(45 * isBlue, imuControl);
//
//            //Strafe out of the way
//            driver.strafe(5, isBlue, 0.5, imuControl);
//
//            //Go to the middle
//            driver.forward(25.5, 1, 0.8);
//
//
//
//            goThroughTrussAndFinish(false, false, true, isBlue);
//
//        }
    }

    public void wingRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);

        //Turn left to face pixel
        driver.rotate2(45 * isBlue, imuControl);

        //Push pixel into place
        driver.forward(4, 1, 0.6);

        //Go backward after placing pixel
        driver.forward(5, -1, 0.6);

        //Adjust (right)
        driver.rotate2(-45 * isBlue, imuControl);

        //Strafe barely so that robot doesn't run over pixel
        //driver.strafe(0.3, -isBlue, 0.4, imuControl);

        //Drive forward (meant to go through the middle of the truss)
        driver.forward(27.5, 1, 0.7);
    }

    public void stageRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward
        driver.forward(17, 1, 0.6);

        //Turn (right) to place pixel
        driver.rotate2(-45 * isBlue, imuControl);

        //Place pixel
        driver.forward(5, 1, 0.6);

        //Go back
        driver.forward(5, -1, 0.6);

        //Adjust (left)
        driver.rotate2(45 * isBlue, imuControl);

        //Strafe out of the way
        driver.strafe(5, isBlue, 0.5, imuControl);

        //Go to the middle
        driver.forward(25.5, 1, 0.8);
    }

    public void centerRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward and place pixel
        driver.forward(26, 1, 0.6);

        //Go back so that robot lets go of pixel
        driver.forward(4, -1, 0.5);

        //Strafe to left
        driver.strafe(10, isBlue, 0.6, imuControl);

        //Continue to go into position
        driver.forward(29, 1, 0.6);

    }

    public void goThroughTrussAndFinish(boolean center, boolean left, boolean right, int isBlue) throws IOException, InterruptedException
    {
        int goThroughTrussDistance;

        driver.rotate2(-90*isBlue, imuControl);

        this.heightChild.encoderControl(1000,.7);

        //This goes to the other side
        if(left)
        {
            goThroughTrussDistance = 60;
        } else if(center)
        {
            goThroughTrussDistance = 80;
        } else
        {
            goThroughTrussDistance = 75;
        }

        driver.forward(goThroughTrussDistance, 1, 0.7);
         

        //Strafe to position
        driver.strafe(23, -isBlue, 0.5, imuControl);

    }


}

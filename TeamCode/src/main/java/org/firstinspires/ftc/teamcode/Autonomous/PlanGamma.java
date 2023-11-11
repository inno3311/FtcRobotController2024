package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;
@Autonomous(name = "Plan Gamma", group = "Group3311")
public class PlanGamma extends AutonomousBase {



    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planGamma(zone);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        sleep(1000);

        int wallTarget = 0;
        if (isBlue == -1)
        {
            wallTarget = 3;
        }

        driveToTag.drive(7, zone.ordinal() + 1 + wallTarget, 11, 0);

    }

    /**
     * There is always a plan B.  ;)
     */
    public void planGamma(SpikeLineEnum zone) throws IOException, InterruptedException {


        //...then calls one of the if statements

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            centerRoute(isBlue);

        }

        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
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
            if(isBlue == 1)
            {
                wingRoute(isBlue);
            }
            else if  (isBlue == -1)
            {
                stageRoute(isBlue);
            }

        }

    }


    public void stageRoute(int isBlue) throws IOException, InterruptedException {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);

        sleep(DELAY);
        //Turn to place pixel
        driver.rotate2(-45*isBlue, imuControl);


        sleep(DELAY);
        //Push pixel into place
        driver.forward(4, 1, 0.6);

        sleep(DELAY);

        //Go backward after placing pixel
        driver.forward(4, -1, 0.6);

        sleep(DELAY);
        //Adjust (left)
        driver.rotate2(-45*isBlue, imuControl);

        sleep(DELAY);

        driver.strafe(4, -isBlue, 1, imuControl);

        ///driver.forward(7, 1, 0.5);
        //sleep(DELAY);

        sleep(DELAY);
        driver.forward(17, 1, 0.5);

    }

    public void centerRoute(int isBlue) throws IOException, InterruptedException {

        //Go forward to determine whether object is left/center/right
        driver.forward(25, 1, 0.6);
        //Go forward and place pixel
        //driver.forward(4, 1, 0.5);

        sleep(DELAY);



        //Go backward into position
        driver.forward(3, -1, 0.6);

        sleep(DELAY);

        driver.rotate2(-90 * isBlue,imuControl);

        sleep(DELAY);

        driver.forward(20,1,0.6);

        sleep(DELAY);

        driver.strafe(2, isBlue,1,imuControl);

        driver.forward(5, 1, 0.3);
    }

    public void wingRoute(int isBlue) throws IOException, InterruptedException {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);
        sleep(DELAY);

        //driver.turn(45, -1, 0.4);
        driver.rotate2(45*isBlue, imuControl);


        sleep(DELAY);
        //Push pixel into place
        driver.forward(4, 1, 0.6);

        sleep(DELAY);

        //Go backward after placing pixel
        driver.forward(4, -1, 0.6);

        sleep(DELAY);
        //Adjust
        //driver.turn(45, 1, 0.4);
        driver.rotate2(-135*isBlue, imuControl);

        sleep(DELAY);

        driver.forward(15, 1, 0.5);
        sleep(1000);

        driver.strafe(12,1 * isBlue, 1, imuControl);
        sleep(1000);

//        driver.strafe(3, -isBlue,1,imuControl);
//
//        sleep(DELAY);
//        driver.forward(17, 1, 0.5);
//
//        sleep(DELAY);
//
//        driver.strafe(3,isBlue,1,imuControl);


    }

}

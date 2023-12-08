package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Plan Alpha", group = "Group3311")

//Formerly BlueStageRight
//Formerly BlueStage
public class PlanAlpha extends AutonomousBase
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planAlpha(zone, isBlue);
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
    public void planAlpha(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        planPurple(zone, isBlue);

        if(zone == SpikeLineEnum.CENTER_SPIKE){

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            //Go through truss
            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            driver.strafe(20, isBlue, 0.5, imuControl);
            
        }
        else if (zone == SpikeLineEnum.LEFT_SPIKE){

         //Turn to truss
        driver.rotate2(-90 * isBlue, imuControl);

        //Go through truss
        driver.forward(70, 1, 0.6);

        //Strafe to let AprilTag take over
        driver.strafe(18, isBlue, 0.5, imuControl);
        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE){

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            //Go through truss
            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            if(isBlue == 1) driver.strafe(24, isBlue, 0.5,  imuControl);
            else if(isBlue == -1) driver.strafe(23, isBlue, 0.5, imuControl);


        }



    }


}

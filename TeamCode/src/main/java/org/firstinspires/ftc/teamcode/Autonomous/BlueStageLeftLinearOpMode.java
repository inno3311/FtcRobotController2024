package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Blue Stage Left", group = "Group3311")
public class BlueStageLeftLinearOpMode extends AutonomousBase
{

    int isBlue = -1;


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
     * There is always a plan B.  ;)
     */
    public void planGamma(SpikeLineEnum zone) throws IOException, InterruptedException
    {


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
                driver.rotate(-45 , imuControl);

                //Push pixel into place
                driver.forward(4, 1, 0.6);

                sleep(1000);

                //Go backward after placing pixel
                driver.forward(4, -1, 0.6);
                sleep(1000);

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
                driver.rotate(45 * isBlue, imuControl);
                sleep(1000);

                //Push pixel into place
                driver.forward(5, 1, 0.6);
                sleep(1000);

                //Go backward after placing pixel
                driver.forward(5, -1, 0.6);
                sleep(1000);

                //Adjust
                //driver.turn(45, 1, 0.4);
                driver.rotate(-125 * isBlue, imuControl);
                sleep(1000);

                driver.forward(15, 1, 0.5);
                sleep(1000);

                driver.strafe(15,1 * isBlue, 0.6, imuControl);
                sleep(1000);

            }

        driveToTag.drive(7, zone.ordinal() + 1, 11, 0);

    }
}



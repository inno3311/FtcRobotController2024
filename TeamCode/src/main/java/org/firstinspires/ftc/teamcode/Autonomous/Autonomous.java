package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.IMU.MainIMU;

public class Autonomous extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        MainIMU mainIMU = new MainIMU();
    }
}

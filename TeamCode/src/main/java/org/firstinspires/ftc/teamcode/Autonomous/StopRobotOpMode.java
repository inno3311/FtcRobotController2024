package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

//Touchsensor class
public class StopRobotOpMode extends LinearOpMode {


    AutonomousBase autonomousBase = new AutonomousBase();

    //DigitalChannel or touchSensor?
    DigitalChannel touchSensor;

    public StopRobotOpMode() {

      TouchSensor sensor = new TouchSensor() {
          @Override
          public double getValue() {
              return 0;
          }

          @Override
          public boolean isPressed() {


              return autonomousBase.robotIsMoving = false;

          }

          @Override
          public Manufacturer getManufacturer() {
              return null;
          }

          @Override
          public String getDeviceName() {
              return null;
          }

          @Override
          public String getConnectionInfo() {
              return null;
          }

          @Override
          public int getVersion() {
              return 0;
          }

          @Override
          public void resetDeviceConfigurationForOpMode() {

          }

          @Override
          public void close() {

          }
      };



        try {

            touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");


        } catch (Exception e){
            e.printStackTrace();

        }

    }

    @Override
    public void runOpMode() throws InterruptedException {

        touchSensor.setMode(DigitalChannel.Mode.INPUT);



    }

}

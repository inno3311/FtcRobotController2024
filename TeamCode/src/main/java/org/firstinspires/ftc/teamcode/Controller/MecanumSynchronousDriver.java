package org.firstinspires.ftc.teamcode.Controller;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

//import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.teamcode.util.PIDController;
import org.firstinspires.ftc.teamcode.util.ImuHardware;

import java.io.IOException;

/**
 * MecanumSynchronousDriver serves as an interface to the drive motors giving the user directional
 * methods to command the bot.  The movements are backed up by PID controller to ensure the bot
 * stays on cource.  This class is intended to be used by autonomous programs.
 */
public class MecanumSynchronousDriver<imuControl> extends MechanicalDriveBase
{

   /**
    * Number of ticks covered in one inch of movement for the odometery wheel.
    * NOTE: value may need fine tuning.
    */
   private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
//                    Circumference of the robot when turning == encoder dist from center (radius) * 2 * pi
//                                        ^^^
//   private final double ticksPerDegree = (ticksPerInch * 64.09) / 360; // 16,000
 //  private final double ticksPerDegree = (ticksPerInch * 56.0) / 360; // 16,000
   //private final double ticksPerDegree = (ticksPerInch * 54.6) / 360; // 16,000
//private final double ticksPerDegree = (ticksPerInch * 55.0) / 360;
   //private final double ticksPerDegree = (ticksPerInch * 54.0) / 360;  // 195.57536208817444
//private final double ticksPerDegree = 202.75;//190;  //FOR BATT 12.3 VOLTS
   private final double ticksPerDegree = 199;  // 12.63 V
//68,466 for 360
//34,233 for 180
//17,116 for 90
// 8,558 for 45

   /**
    * Logging method used to write data to file.
    * NOTE: This seems to cause the dev to have to hard reset the bot after each run, making us of
    * this class not ideal.  Need to debug cause.
    */
   Logging logger;

   /**
    * Local copy of the opMode.
    */
   LinearOpMode mOpMode;

   /**
    * PD controller to correct the turning of the bot while driving a straight line.
    */
   PIDController pidDrive;

   /**
    * PID controller to correct the lateral movement (strafing) while the bot is driving a straight line.
    */
   PIDController pidStrafe;


   PIDController pidRotateImu;

   PIDController pidRotateOd;

   double rotation;

    /**
     * Constructor for MechanicalDriveBase from the hardware map
     *
     * @param hardwareMap the hardware map
     */
    public MecanumSynchronousDriver(HardwareMap hardwareMap, LinearOpMode opMode) throws IOException
    {
       super(opMode.hardwareMap);

       logger.setup();
       logger.log("Starting MecanumSynchronousDriver");

       mOpMode = opMode;
       opMode.telemetry.update();

//       imuControl = new IMUControl(this.mOpMode.hardwareMap, this.mOpMode.telemetry);


       //The input can be a large value, therefore the values of Kp needs to be small to compensate.
       //
       // Set PID proportional value to produce non-zero correction value when robot veers off
       // straight line. P value controls how sensitive the correction is.
       pidDrive = new PIDController(0.1, 0, 0.001);

       pidStrafe = new PIDController(0.00001, 0, 0);

       // Set PID proportional value to start reducing power at about 50 degrees of rotation.
       // P by itself may stall before turn completed so we add a bit of I (integral) which
       // causes the PID controller to gently increase power if the turn is not completed.
       pidRotateImu = new PIDController(.003, .00003, 0);
       pidRotateImu = new PIDController(.02, .001, 0);  //works pretty good
       pidRotateImu = new PIDController(.03, .003, 0);  //low bat
       pidRotateImu = new PIDController(.02, .003, .001);

                                                                     //target 17797.357950
       //pidRotateOd = new PIDController(.0005, 0, 0);  //overshoot
       //pidRotateOd = new PIDController(.00025, 0, 0); //undershoot
       //pidRotateOd = new PIDController(.00025, .000001, 0); //overshoot 18442
       //pidRotateOd = new PIDController(.00025, .00000001, 0); // under 17064
//       pidRotateOd = new PIDController(.00025, .0000005, 0);  // slight 17984
//      pidRotateOd = new PIDController(.00025, .000001, 0); // 17836 17862  //works well for .5 power

//       pidRotateOd = new PIDController(.00025, .00001, 0);

       pidRotateOd = new PIDController(.00025, .0000001, .001);  // slight 17984
       pidRotateOd = new PIDController(.00025, .0000002, .001);  // works for 90

       pidRotateOd = new PIDController(.00025, .0000001, .003);  // 180 shutters too much
       pidRotateOd = new PIDController(.00025, .0000001, .002);  // 180 ok... kinda

       pidRotateOd = new PIDController(.00025, .0000002, .000);  // 180 ok... kinda

       pidRotateOd = new PIDController(.0003, .0000002, .000);  // 180 ok... kinda

       Logging.setup();
       Logging.log("Starting MecanumSynchronousDriver Logging");
    }

   /**
    * Drives the bot forward or backward in a straight line.
    * @param target distance in inches to travel.
    * @param forward indicates directipn of travel.  -1 is forward 1 is backwards?
    * @param speed double value indicating the speed from 0 to 1.
    */
    public void forward(double target, int forward, double speed)
    {

        pidDrive.setSetpoint(0);
        pidDrive.setOutputRange(0, speed);
        pidDrive.setInputRange(-5000, 5000);
//        pidDrive.setTolerance(1.0);
        pidDrive.enable();

        // Set up parameters for strafe correction.
        pidStrafe.setSetpoint(0);
        pidStrafe.setOutputRange(0, 0.3);
        pidStrafe.setInputRange(-5000, 5000);
        pidStrafe.enable();

        speed *= forward;
        int leftFrontPos = this.lf.getCurrentPosition();
//        if (forward == 1)
        {
            if (forward == 1)
               leftFrontPos += target * ticksPerInch;
            else
               leftFrontPos -= target * ticksPerInch;

            while (mOpMode.opModeIsActive())
            {
               int currPosTicks = this.lf.getCurrentPosition();


               //if the number is positive the bot is slipping right
               //if the number is negative the bot is slipping left
               //lf and rf are added because rf is reverse of lf direction.
               int wheelDifference = this.lf.getCurrentPosition() + this.rf.getCurrentPosition();

               // if the number is positive the bot strafed left
               // if the number is negative the bot strafed right
               int strafeDifference = this.rb.getCurrentPosition();

               // Use PID with imu input to drive in a straight line.
               // pos is right turn, neg is left turn
               double correction = pidDrive.performPID(wheelDifference);
               mOpMode.telemetry.addData("correction ", "correction: " + correction + " wheelDif: " + wheelDifference);

               double strafeCorrection = pidStrafe.performPID(strafeDifference);
               mOpMode.telemetry.addData("strafeCorrection ", "correction: " + strafeCorrection + " strafeDifference: " + strafeDifference);


               //this.driveMotors(speed, 0, 0, 1); //run with no PID
               correction = correction * (speed * 0.33);
               this.driveMotors(speed, correction * forward, -strafeCorrection, 1); // run with PID

                //logger.log("left Encoder = %d, Right Encoder = %d wheelDifference = %d correction = %f", this.lf.getCurrentPosition(), this.rf.getCurrentPosition(), wheelDifference, correction);
               mOpMode.telemetry.addData("Encoder", "left: " + lf.getCurrentPosition() + " right: " + rf.getCurrentPosition() + " strafe: " + rb.getCurrentPosition());
               mOpMode.telemetry.update();

               if (forward == 1)
               {
                  if (currPosTicks > leftFrontPos)
                     break;
               }
               else
               {
                  if (currPosTicks < leftFrontPos)
                     break;
               }
            }
        }
//        else  //TODO: would like to not have a else here and have to repeat the above code or have to call a subjuction..
//        {
//            leftFrontPos -= target;
//            while (this.lf.getCurrentPosition() >= leftFrontPos)
//            {
//                this.driveMotors(speed, 0, 0, 1);
//
//                //logger.log("left Encoder = %d, Right Encoder = %d ", this.lf.getCurrentPosition(), this.rf.getCurrentPosition());
//                //mOpMode.telemetry.addData("NOOOOOOO", this.lf.getCurrentPosition());
//                //mOpMode.telemetry.update();
//            }
//        }
        this.driveMotors(0, 0, 0, 0);

       this.resetEncoders();
       this.resetRunMode();

       //driver.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        encoderLogging();
    }

    /**
     * Drives the bot right or backward in a straight line.
     * @param target distance in inches to travel.
     * @param right indicates directipn of travel.  -1 is right 1 is backwards?
     * @param speed double value indicating the speed from 0 to 1.
     */
//    public void strafe(double target, int right, double speed, IMUControl imuControl)
//    {
//        imuControl.resetAngle();
//
//        // Set up parameters for turn correction.
//        pidDrive.setSetpoint(0);
//        pidDrive.setOutputRange(0, .19);
//        pidDrive.setInputRange(-5000, 5000);
//        pidDrive.enable();
//
//        // Set up parameters for strafe correction.
//        pidStrafe.setSetpoint(0);
//        pidStrafe.setOutputRange(0, .15);
//        pidStrafe.setInputRange(-5000, 5000);
//        pidStrafe.enable();
//
//        speed *= right;
//        int rightFrontPos = this.rb.getCurrentPosition();
//        if (right == 1)
//        {
//            rightFrontPos += target * ticksPerInch;
//            while ((Math.abs(this.rb.getCurrentPosition()) <= rightFrontPos) && mOpMode.opModeIsActive())
//            {
//                //if the number is positive the bot is slipping right
//                //if the number is negative the bot is slipping left
//                //lf and rf are added because rf is reverse of lf direction.
//                int yDifference = (this.lf.getCurrentPosition() - this.rf.getCurrentPosition()) / 2;
//
//                // if the number is positive the bot strafed left
//                // if the number is negative the bot strafed right
//                int strafeDifference = this.rb.getCurrentPosition();
//
//                double angle = imuControl.getAngle();
//                angle /= 100;
//
//                // Use PID with imu input to drive in a straight line.
//                // pos is right turn, neg is left turn
//                double forwardCorrection = pidDrive.performPID(yDifference);
//                mOpMode.telemetry.addData("correction ", "correction: " + forwardCorrection + " wheelDif: " + yDifference);
//
//                double strafeCorrection = pidStrafe.performPID(strafeDifference);
//                mOpMode.telemetry.addData("strafeCorrection ", "correction: " + strafeCorrection + " strafeDifference: " + strafeDifference);
//
//                this.driveMotors(forwardCorrection, angle, speed, 1); // run with PID
//
//                //         logger.log("left Encoder = %d, Right Encoder = %d ", this.lf.getCurrentPosition(), this.rf.getCurrentPosition());
//                mOpMode.telemetry.addData("Encoder", "left: " + lf.getCurrentPosition() + " right: " + rf.getCurrentPosition() + " strafe: " + rb.getCurrentPosition());
//                mOpMode.telemetry.update();
//            }
//        }
//        else  //TODO: would like to not have a else here and have to repeat the above code or have to call a subfuction..
//        {
//            rightFrontPos -= target;
//            while (this.lf.getCurrentPosition() >= rightFrontPos)
//            {
//                this.driveMotors(speed, 0, 0, 1);
//
//                //logger.log("left Encoder = %d, Right Encoder = %d ", this.lf.getCurrentPosition(), this.rf.getCurrentPosition());
//                //mOpMode.telemetry.addData("NOOOOOOO", this.lf.getCurrentPosition());
//                //mOpMode.telemetry.update();
//            }
//        }
//        this.driveMotors(0, 0, 0, 0);
////        encoderLogging();
//    }

    public void turn(double target, int right, double speed)
    {

        pidDrive.setSetpoint(0);
        pidDrive.setOutputRange(0, speed);
        pidDrive.setInputRange(-5000, 5000);
//        pidDrive.setTolerance(1.0);
        pidDrive.enable();

        // Set up parameters for strafe correction.
        pidStrafe.setSetpoint(0);
        pidStrafe.setOutputRange(0, 0.3);
        pidStrafe.setInputRange(-5000, 5000);
        pidStrafe.enable();

        speed *= right;
        int rightBackPosition = this.rb.getCurrentPosition();
        if (right == 1)
        {
            rightBackPosition += target * ticksPerDegree;
            while ((this.rb.getCurrentPosition() <= rightBackPosition) && mOpMode.opModeIsActive())
            {
                //if the number is positive the bot is slipping right
                //if the number is negative the bot is slipping left
                //lf and rf are added because rf is reverse of lf direction.
                int wheelDifference = this.lf.getCurrentPosition() + this.rf.getCurrentPosition();

                // if the number is positive the bot strafed left
                // if the number is negative the bot strafed right
                int strafeDifference = this.rb.getCurrentPosition();

                // Use PID with imu input to drive in a straight line.
                // pos is right turn, neg is left turn
                double correction = pidDrive.performPID(wheelDifference);
                mOpMode.telemetry.addData("correction ", "correction: " + correction + " wheelDif: " + wheelDifference);

                double strafeCorrection = pidStrafe.performPID(strafeDifference);
                mOpMode.telemetry.addData("strafeCorrection ", "correction: " + strafeCorrection + " strafeDifference: " + strafeDifference);


                //this.driveMotors(speed, 0, 0, 1); //run with no PID
                correction = correction * (speed * 0.33);
                this.driveMotors(0, speed, 0, 1); // run with PID

                //logger.log("left Encoder = %d, Right Encoder = %d wheelDifference = %d correction = %f", this.lf.getCurrentPosition(), this.rf.getCurrentPosition(), wheelDifference, correction);
                mOpMode.telemetry.addData("Encoder", "left: " + lf.getCurrentPosition() + " right: " + rf.getCurrentPosition() + " strafe: " + rb.getCurrentPosition());
                mOpMode.telemetry.update();
            }
        }
        else  //TODO: would like to not have a else here and have to repeat the above code or have to call a subjuction..
        {
            rightBackPosition -= target * ticksPerDegree;
            while (this.rb.getCurrentPosition() >= rightBackPosition && mOpMode.opModeIsActive())
            {
                this.driveMotors(0, speed, 0, 1);

                //logger.log("left Encoder = %d, Right Encoder = %d ", this.lf.getCurrentPosition(), this.rf.getCurrentPosition());
                //mOpMode.telemetry.addData("NOOOOOOO", this.lf.getCurrentPosition());
                //mOpMode.telemetry.update();

               mOpMode.telemetry.addData("90 = ", (ticksPerDegree * 90) + "\n current position = " +  rb.getCurrentPosition());
               mOpMode.telemetry.update();
            }
        }
        this.driveMotors(0, 0, 0, 0);
        this.resetEncoders();
        this.resetRunMode();
//        encoderLogging();
    }

    public void rotateOd(int degrees, double power) throws InterruptedException
    {
       Logging.log("#rotateOd degrees = %d  power = %f", degrees, power);

       int startPos = this.rb.getCurrentPosition();
       double targetPos = degrees * ticksPerDegree;
       pidRotateOd.reset();
       pidRotateOd.setSetpoint(targetPos);
       pidRotateOd.setInputRange(0, targetPos*2);
       pidRotateOd.setOutputRange(.15, power);
       pidRotateOd.setTolerance(.15);
       pidRotateOd.enable();

       // Proportional factor can be found by dividing the max desired pid output by
       // the setpoint or target. Here 30% power is divided by 90 degrees (.30 / 90)
       // to get a P factor of .003. This works for the robot we testing this code with.
       // Your robot may vary but this way finding P works well in most situations.
       double p = Math.abs(power/targetPos);

       // Integrative factor can be approximated by diving P by 100. Then you have to tune
       // this value until the robot turns, slows down and stops accurately and also does
       // not take too long to "home" in on the setpoint. Started with 100 but robot did not
       // slow and overshot the turn. Increasing I slowed the end of the turn and completed
       // the turn in a timely manner
       double i = p / 200.0;

       //Set PID parameters based on power and ticks to travel.
//       pidRotateOd.setPID(p, i, 0);

       mOpMode.telemetry.addData("rotateOd1", "startPos:  %d   targetPos: %f ", startPos,targetPos);
       mOpMode.telemetry.update();

       Logging.log("#rotateOd startPos:  %d   targetPos: %f ", startPos,targetPos);

       int onTargetCount = 0;

       do
       {
          power = pidRotateOd.performPID(this.rb.getCurrentPosition() - startPos); // power will be + on left turn.
          this.driveMotors(0, power, 0, 1);

          //mOpMode.telemetry.addData("rotateOd2", "startPos:  %d   targetPos: %f ", startPos,targetPos);
          //mOpMode.telemetry.addData("rotateOd2", "power: %f currPos:  %d", power, this.rb.getCurrentPosition() - startPos);
          //mOpMode.telemetry.update();

          Logging.log("#rotateOd targetPos: %f power: %f currPos: %d degrees: %f",targetPos, power, this.rb.getCurrentPosition() - startPos, (this.rb.getCurrentPosition() - startPos)/ticksPerDegree);

          if (pidRotateOd.onTarget())
          {
             onTargetCount++;
             Logging.log("#onTargetCount: %d",onTargetCount);

          }

       } while (mOpMode.opModeIsActive() && (onTargetCount < 8));

       //Kill motors
       this.driveMotors(0, 0, 0, 0);

       mOpMode.telemetry.addData("rotateOd3", "startPos:  %d   targetPos: %f ", startPos,targetPos);
       mOpMode.telemetry.addData("rotateOd3", "currPos:  %d  degrees: %f", this.rb.getCurrentPosition() - startPos, (this.rb.getCurrentPosition() - startPos)/ticksPerDegree);
       mOpMode.telemetry.update();

       Logging.log("#rotateOd complete  currPos:  %d  degrees: %f",  this.rb.getCurrentPosition() - startPos, (this.rb.getCurrentPosition() - startPos)/ticksPerDegree);
    }

    public void rotateLeft90(ImuHardware imuControl) throws InterruptedException, IOException
    {
       double degrees = 90.0;
       double power = .5;

       //pidRotateImu = new PIDController(.035, .0002, .06);
       pidRotateImu = new PIDController(.04, .0001, .11);

       pidRotateImu.reset();
       pidRotateImu.setSetpoint(90);
       pidRotateImu.setInputRange(0, 90 + 90 / 10);
       pidRotateImu.setOutputRange(0, 1);
       pidRotateImu.setTolerance(.3);
       pidRotateImu.enable();

       int onTargetCount = 0;
       int onTargetCountTotal = 0;
       // restart imu angle tracking.
       imuControl.resetAngle();

       double currAngle = 0.0;
       double remainingAngle = 0.0;

       do
       {
          currAngle = imuControl.getAngle();
          remainingAngle = degrees - currAngle;     // 90-60 = 30


          power = pidRotateImu.performPID(currAngle); // power will be + on left turn.
          this.driveMotors(0, -power, 0, 1);
          Logging.log("%.2f Deg. (Heading)  power: %f  getAngle() %f", imuControl.getHeading(), power, imuControl.getAngle());

          if (pidRotateImu.onTarget())
          {
             //pidRotateImu.setOutputRange(.05, 1);
             onTargetCount++;
             onTargetCountTotal++;
             Logging.log("onTargetCount %d", onTargetCount);
          }
          else
          {
             onTargetCount = 0;
          }

       }
       while (mOpMode.opModeIsActive() && onTargetCount < 5 && onTargetCountTotal < 10);

       this.driveMotors(0, 0, 0, 1);
       Logging.log("completed rotate of angle %f", degrees);
       mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", imuControl.getHeading());
       mOpMode.telemetry.update();
    }

    public void rotateMez(double degrees, double power, ImuHardware imuControl) throws InterruptedException, IOException
    {
       pidRotateImu.reset();
       pidRotateImu.setSetpoint(degrees);
       pidRotateImu.setInputRange(0, degrees + degrees / 10);
       pidRotateImu.setOutputRange(.14, 1);
       pidRotateImu.setTolerance(.4);
       pidRotateImu.enable();

       int onTargetCount = 0;
       int onTargetCountTotal = 0;
       // restart imu angle tracking.
       imuControl.resetAngle();

       double currAngle = 0.0;
       double remainingAngle = 0.0;
       if (degrees < 0)
       {
          // On right turn we have to get off zero first.
          while (mOpMode.opModeIsActive() && imuControl.getAngle() == 0)
          {
             this.driveMotors(0, power, 0, 1);
             sleep(100);

          }
       }
       else
       {
          do
          {
             currAngle = imuControl.getAngle();
             remainingAngle = degrees - currAngle;

             power = -1.0;//pidRotateImu.performPID(currAngle); // power will be + on left turn.
             this.driveMotors(0, -1, 0, 1);
             Logging.log("%.2f Deg. (Heading)  power: %f  getAngle() %f", imuControl.getHeading(), power, imuControl.getAngle());

          }
          while (mOpMode.opModeIsActive() && remainingAngle > 30.0);

          Logging.log("remaining angle %f", remainingAngle);
          pidRotateImu.setOutputRange(.13, 1);
          do
          {
             currAngle = imuControl.getAngle();
             remainingAngle = degrees - currAngle;     // 90-60 = 30
             pidRotateImu.setSetpoint(degrees);

             power = pidRotateImu.performPID(currAngle); // power will be + on left turn.
             this.driveMotors(0, -power, 0, 1);
             Logging.log("%.2f Deg. (Heading)  power: %f  getAngle() %f", imuControl.getHeading(), power, imuControl.getAngle());

             if (pidRotateImu.onTarget())
             {
                pidRotateImu.setOutputRange(.05, 1);
                onTargetCount++;
                onTargetCountTotal++;
                Logging.log("onTargetCount %d", onTargetCount);
             }
             else
             {
                onTargetCount = 0;
             }

          }
          while (mOpMode.opModeIsActive() && onTargetCount < 5 && onTargetCountTotal < 10);
       }
       // turn the motors off.
       this.driveMotors(0, 0, 0, 1);
       Logging.log("completed rotate of angle %f", degrees);
       mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", imuControl.getHeading());
       mOpMode.telemetry.update();



    }

   /**
    * Rotate left or right the number of degrees. Does not support turning more than 359 degrees.
    * @param degrees Degrees to turn, + is left - is right
    */
   public void rotate(int degrees, double power, ImuHardware imuControl) throws InterruptedException, IOException
   {
      //Logging.setup();
      mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", imuControl.getHeading());
      mOpMode.telemetry.update();

      // restart imu angle tracking.
      imuControl.resetAngle();

//      sleep(3000);

      // if degrees > 359 we cap at 359 with same sign as original degrees.
      if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

      // start pid controller. PID controller will monitor the turn angle with respect to the
      // target angle and reduce power as we approach the target angle. This is to prevent the
      // robots momentum from overshooting the turn after we turn off the power. The PID controller
      // reports onTarget() = true when the difference between turn angle and target angle is within
      // 1% of target (tolerance) which is about 1 degree. This helps prevent overshoot. Overshoot is
      // dependant on the motor and gearing configuration, starting power, weight of the robot and the
      // on target tolerance. If the controller overshoots, it will reverse the sign of the output
      // turning the robot back toward the setpoint value.

      pidRotateImu.reset();
      pidRotateImu.setSetpoint(degrees);
      pidRotateImu.setInputRange(0, degrees + degrees / 10);
      pidRotateImu.setOutputRange(.15, 1);
      pidRotateImu.setTolerance(.25);
      pidRotateImu.enable();

      // getAngle() returns + when rotating counter clockwise (left) and - when rotating
      // clockwise (right).

      // rotate until turn is completed.
      int onTargetCount = 0;

      if (degrees < 0)
      {
         // On right turn we have to get off zero first.
         while (mOpMode.opModeIsActive() && imuControl.getAngle() == 0)
         {
            this.driveMotors(0, power, 0, 1);
            sleep(100);

         }



         do
         {
            power = pidRotateImu.performPID(imuControl.getAngle()); // power will be - on right turn.
            this.driveMotors(0, power, 0, 1);

            //mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", imuControl.getHeading());
            //mOpMode.telemetry.update();
            Logging.log("%.2f Deg. (Heading)  power: %f  getAngle() %f", imuControl.getHeading(), power, imuControl.getAngle());

            if (pidRotateImu.onTarget())
            {
               onTargetCount++;
               Logging.log("onTargetCount %d", onTargetCount);
            }
            else
            {
               onTargetCount = 0;
            }

         } while (mOpMode.opModeIsActive() && onTargetCount < 5);
      }
      else    // left turn.
         do
         {
            power = pidRotateImu.performPID(imuControl.getAngle()); // power will be + on left turn.
            this.driveMotors(0, -power, 0, 1);
            Logging.log("%.2f Deg. (Heading)  power: %f  getAngle() %f", imuControl.getHeading(), power, imuControl.getAngle());

            if (pidRotateImu.onTarget())
            {
               onTargetCount++;
               Logging.log("onTargetCount %d", onTargetCount);
            }
            else
            {
               onTargetCount = 0;
            }

         } while (mOpMode.opModeIsActive() && onTargetCount < 5);

      // turn the motors off.
      this.driveMotors(0, 0, 0, 1);

      rotation = imuControl.getAngle();

      mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)", imuControl.getHeading());
      mOpMode.telemetry.update();

      // wait for rotation to stop.
      sleep(500);

      // reset angle tracking on new heading.
      imuControl.resetAngle();
   }


   public void rotationTest(ImuHardware imuControl) throws IOException
   {
      int startPos = rb.getCurrentPosition();
      double startHeading = imuControl.getHeading();
      double power = .5;
      double targetPos = 90.0;
      pidRotateOd.reset();
      pidRotateOd.setSetpoint(targetPos);
      pidRotateOd.setInputRange(0, targetPos*2);
      pidRotateOd.setOutputRange(.15, power);
      pidRotateOd.setTolerance(.15);
      pidRotateOd.enable();

      Logging.setup();
      int onTargetCount = 0;

      do
      {
         power = pidRotateOd.performPID(this.rb.getCurrentPosition() - startPos); // power will be + on left turn.
         this.driveMotors(0, power, 0, 1);

         this.driveMotors(0, .5, 0, 1);
         int currPos = startPos - rb.getCurrentPosition();
         mOpMode.telemetry.addData("Yaw (Z)", "%.2f Deg. (Heading)  wheel: %d", imuControl.getHeading(), currPos);
         mOpMode.telemetry.update();
         Logging.log("%.2f Deg. (Heading)  wheel: %d", imuControl.getHeading(), currPos);

         if (pidRotateOd.onTarget())
         {
            onTargetCount++;
            Logging.log("#onTargetCount: %d",onTargetCount);
         }
      }
      while (onTargetCount < 8 && mOpMode.opModeIsActive());


      this.driveMotors(0, 0, 0, 1);
      Logging.log("STOP!  %.2f Deg. (Heading)  wheel: %d", startHeading - imuControl.getHeading(), startPos - rb.getCurrentPosition());
   }
}

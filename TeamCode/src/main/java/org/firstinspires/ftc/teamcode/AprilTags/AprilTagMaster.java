package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;

public class AprilTagMaster
{
    // Adjust these numbers to suit your robot.
    double desiredDistance = 0; //  this is how close the camera should get to the target (inches)
    double strafeDif = 0;

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN = 0.075;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN = 0.06;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN = 0.05;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.7;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE = 0.8;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.6;   //  Clip the turn speed to this max value (adjust for your robot)

    private static final boolean USE_WEBCAM = true;  // Set true to use a webcam, or false for a phone camera
    private static  int desiredTagID = -1;// Choose the tag you want to approach or set to -1 for ANY tag.

//    private VisionPortal visionPortal;               // Used to manage the video source.
    private AprilTagProcessor aprilTag;              // Used for managing the AprilTag detection process.
    private AprilTagDetection desiredTag = null;     // Used to hold the data for a detected AprilTag
    private MechanicalDriveBase mechanicalDriveBase;
    WebcamName webcamName;
    private double rangeError = 0 ;
    private double headingError = 0;
    private double yawError = 0;


    public AprilTagMaster(MechanicalDriveBase mechanicalDriveBase, AprilTagProcessor aprilTag)
    {
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.aprilTag = aprilTag;
    }

    public void tagsTelemetry(Telemetry telemetry)
    {
        // Push telemetry to the Driver Station.
        telemetryAprilTag(telemetry);
        telemetry.update();
    }

    public void findTag(double range, double yaw, int target, Telemetry telemetry)
    {
        desiredDistance = range + 1;
        strafeDif = yaw;
        boolean targetFound = false;    // Set to true when an AprilTag target is detected
        double  drive = 0;        // Desired forward power/speed (-1 to +1)
        double  strafe = 0;        // Desired strafe power/speed (-1 to +1)
        double  turn = 0;        // Desired turning power/speed (-1 to +1)
        //The aprilTag you want to find
        desiredTagID = target;

        targetFound = false;
        desiredTag  = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections)
        {
            if ((detection.metadata != null) &&
                    ((desiredTagID < 0) || (detection.id == desiredTagID)))
            {
                targetFound = true;
                desiredTag = detection;
                break;  // don't look any further.
            }
            else
            {
                telemetry.addData("Unknown Target", "Tag ID %d is not in TagLibrary\n", detection.id);
            }
        }

        // Tell the driver what we see, and what to do.
        if (targetFound)
        {
            telemetry.addData("Target", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
            telemetry.addData("Range", "%5.1f inches", desiredTag.ftcPose.range);
            telemetry.addData("Bearing", "%3.0f degrees", desiredTag.ftcPose.bearing);
            telemetry.addData("Yaw", "%3.0f degrees", desiredTag.ftcPose.yaw);
        }

        // If Left Bumper is being pressed, AND we have found the desired target, Drive to target Automatically .
        if (targetFound)
        {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            rangeError = (desiredTag.ftcPose.range - desiredDistance);
            headingError = desiredTag.ftcPose.bearing;
            yawError = (desiredTag.ftcPose.yaw - strafeDif);

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            strafe = Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

            telemetry.addData("Auto", "Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
        }

//        telemetry.update();

        // Apply desired axes motions to the drivetrain.
        mechanicalDriveBase.driveMotors(drive, turn, strafe, 1);
    }

    private void telemetryAprilTag(Telemetry telemetry)
    {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections)
        {
            if (detection.metadata != null)
            {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            }
            else
            {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");

    }   // end method telemetryAprilTag()

    public double getX()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.x;
    }

    public double getY()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.x;
    }

    // Shouldn't need Z

    public double getPitch()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.pitch;
    }

    public double getRoll()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.roll;
    }

    public double getYaw()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.yaw;
    }

    public double getBearing()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.bearing;
    }

    public double getRange()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.range;
    }

    public double getElevation()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.elevation;
    }

    public int getDetectionID()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections)
        {
            if (detection.metadata != null)
            {
                return detection.id;
            }
        }
        return -1;
    }

    public boolean aprilTagDetected()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections)
        {
            if (detection.metadata != null)
            {
                return true;
            }
        }
        return false;
    }

//    public void closeAprilTags()
//    {
////        visionPortal.close();
//    }
//
//    /**
//     * Initialize the AprilTag processor.
//     */
//    private void initAprilTag(HardwareMap hardwareMap)
//    {
//        // Create the AprilTag processor by using a builder.
//        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
//
//        // Create the vision portal the easy way.
//        if (USE_WEBCAM)
//        {
//            visionPortal = VisionPortal.easyCreateWithDefaults(
//                    hardwareMap.get(WebcamName.class, "Bottom"), aprilTag);
//        }
//        else
//        {
//            visionPortal = VisionPortal.easyCreateWithDefaults(
//                    BuiltinCameraDirection.BACK, aprilTag);
//        }
//    }

}
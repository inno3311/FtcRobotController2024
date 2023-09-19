package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class driveRelativeToTag
{
    MechanicalDriveBase mechanicalDriveBase;
    DetectAprilTag detectAprilTag;

    final double SPEED_GAIN  =  0.02  ;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN =  0.015 ;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN   =  0.02  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.4;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE = 0.4;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.4;   //  Clip the turn speed to this max value (adjust for your robot)

    double drive = 0;
    double turn = 0;
    double strafe = 0;

    public driveRelativeToTag(MechanicalDriveBase mechanicalDriveBase, DetectAprilTag detectAprilTag)
    {
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.detectAprilTag = detectAprilTag;
    }

    public void drive(int target, double xTarget, double yTarget, double bearingTarget)
    {
        boolean targetFound = false;    // Set to true when an AprilTag target is detected
        //The aprilTag you want to find

        targetFound = false;

        // Step through the list of detected tags and look for a matching tag
        if (detectAprilTag.getDetectionID() == target)
        {
            targetFound = true;
        }

        // If Left Bumper is being pressed, AND we have found the desired target, Drive to target Automatically .
        if (targetFound)
        {
            double yError = (detectAprilTag.getY() - yTarget);
            double xError = (detectAprilTag.getX() - xTarget);
            double bearingError = -(detectAprilTag.getBearing() - bearingTarget);

            drive = Range.clip(yError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn = Range.clip(bearingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            strafe = Range.clip(xError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
        }

        mechanicalDriveBase.driveMotors(drive, turn, strafe, 1);
    }
    // Or?

    public void driveTwo(int target, double rangeDif, double bearingDif, double yawDif)
    {
        boolean targetFound = false;    // Set to true when an AprilTag target is detected
        //The aprilTag you want to find

        targetFound = false;

        // Step through the list of detected tags and look for a matching tag
        if (detectAprilTag.getDetectionID() == target)
        {
            targetFound = true;
        }

        // If Left Bumper is being pressed, AND we have found the desired target, Drive to target Automatically .
        if (targetFound)
        {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double  rangeError = (detectAprilTag.getRange() - rangeDif);
            double  headingError = -(detectAprilTag.getBearing() - bearingDif);
            double  yawError = (detectAprilTag.getYaw() - yawDif);

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
            strafe = Range.clip(yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

        }

        // Apply desired axes motions to the drivetrain.
        mechanicalDriveBase.driveMotors(drive, turn, strafe, 1);
    }
}

///* Copyright (c) 2023 FIRST. All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without modification,
// * are permitted (subject to the limitations in the disclaimer below) provided that
// * the following conditions are met:
// *
// * Redistributions of source code must retain the above copyright notice, this list
// * of conditions and the following disclaimer.
// *
// * Redistributions in binary form must reproduce the above copyright notice, this
// * list of conditions and the following disclaimer in the documentation and/or
// * other materials provided with the distribution.
// *
// * Neither the name of FIRST nor the names of its contributors may be used to endorse or
// * promote products derived from this software without specific prior written permission.
// *
// * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
// * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
// * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
// * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
//
//package org.firstinspires.ftc.teamcode.util;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
//import org.firstinspires.ftc.teamcode.Autonomous.AutonomousBase;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//import org.firstinspires.ftc.vision.tfod.TfodProcessor;
//
//import java.util.List;
//
//
///*
// * This OpMode illustrates the basics of using both AprilTag recognition and TensorFlow
// * Object Detection.
// *
// * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
// * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
// */
////@TeleOp(name = "Concept: Double Vision", group = "Concept")
////@Disabled
//public class WebCamDoubleVision
//{
//    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////
//    //TFOF data values
//
//    //private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/red_rev1.tflite";
//    private static final String TFOD_MODEL_FILE_RED = "/sdcard/FIRST/tflitemodels/Red_10-27.tflite";
//    private static final String TFOD_MODEL_FILE_BLUE = "/sdcard/FIRST/tflitemodels/RedBlue11_25.tflite";
//
//    private static final String[] LABELS =
//    {
//       "Trash Panda"
//    };
//
//    String mTfodFile = null;
//
//    /**
//     * The variable to store our instance of the AprilTag processor.
//     */
//    private AprilTagProcessor aprilTag;
//
//    /**
//     * The variable to store our instance of the TensorFlow Object Detection processor.
//     */
//    private TfodProcessor tfod;
//
//    /**
//     * The variable to store our instance of the vision portal.
//     */
//    private VisionPortal myVisionPortal;
//
//    /**
//     *  Declare OpMode members.
//     **/
//    private LinearOpMode mOpMode = null;   // gain access to methods in the calling OpMode.
//
//    public WebCamDoubleVision (LinearOpMode opmode, int color)
//    {
//        if (color == 1)
//        {
//            mTfodFile = TFOD_MODEL_FILE_BLUE;
//        }
//        else
//        {
//            mTfodFile = TFOD_MODEL_FILE_RED;
//        }
//
//        mOpMode = opmode;
//
//        initDoubleVision();
//    }
//
//    public void checkVision()
//    {
////        if (opModeInInit())
//        {
//            mOpMode.telemetry.addData("DS preview on/off","3 dots, Camera Stream");
//            mOpMode.telemetry.addLine();
//            mOpMode.telemetry.addLine("----------------------------------------");
//        }
//
//        if (myVisionPortal.getProcessorEnabled(aprilTag)) {
//            // User instructions: Dpad left or Dpad right.
//            mOpMode.telemetry.addLine("Dpad Left to disable AprilTag");
//            mOpMode.telemetry.addLine();
//            telemetryAprilTag();
//        } else {
//            mOpMode.telemetry.addLine("Dpad Right to enable AprilTag");
//        }
//        mOpMode.telemetry.addLine();
//        mOpMode.telemetry.addLine("----------------------------------------");
//        if (myVisionPortal.getProcessorEnabled(tfod)) {
//            mOpMode.telemetry.addLine("Dpad Down to disable TFOD");
//            mOpMode.telemetry.addLine();
//            telemetryTfod();
//        } else {
//            mOpMode.telemetry.addLine("Dpad Up to enable TFOD");
//        }
//
//        // Push telemetry to the Driver Station.
//        mOpMode.telemetry.update();
//
////        if (gamepad1.dpad_left) {
////            myVisionPortal.setProcessorEnabled(aprilTag, false);
////        } else if (gamepad1.dpad_right) {
////            myVisionPortal.setProcessorEnabled(aprilTag, true);
////        }
////        if (gamepad1.dpad_down) {
////            myVisionPortal.setProcessorEnabled(tfod, false);
////        } else if (gamepad1.dpad_up) {
////            myVisionPortal.setProcessorEnabled(tfod, true);
////        }
//
//    }
//
//    public void enableTfod()
//    {
//        myVisionPortal.setProcessorEnabled(tfod, false);
//    }
//
//    public void disableTfod()
//    {
//        myVisionPortal.setProcessorEnabled(tfod, true);
//    }
//
//
//    /**
//     * Initialize AprilTag and TFOD.
//     */
//    private void initDoubleVision()
//    {
//        // -----------------------------------------------------------------------------------------
//        // AprilTag Configuration
//        // -----------------------------------------------------------------------------------------
//
//        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
//
//        // -----------------------------------------------------------------------------------------
//        // TFOD Configuration
//        // -----------------------------------------------------------------------------------------
//
//        tfod = new TfodProcessor.Builder()
//              // Use setModelAssetName() if the TF Model is built in as an asset.
//              // Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
//              //.setModelAssetName(TFOD_MODEL_ASSET)
//              .setModelFileName(mTfodFile)
//              .setModelLabels(LABELS)
//              .setModelAspectRatio(16.0 / 9.0)
//            .build();
//
//        tfod.setMinResultConfidence(0.7f);
//        tfod.setClippingMargins(0, 100, 0, 100);
//        //tfod.setZoom(1.2);
//
//        // -----------------------------------------------------------------------------------------
//        // Camera Configuration
//        // -----------------------------------------------------------------------------------------
//
//        if (USE_WEBCAM)
//        {
//            myVisionPortal = new VisionPortal.Builder()
//                .setCamera(mOpMode.hardwareMap.get(WebcamName.class, "Top"))
//                .addProcessors(tfod, aprilTag)
//                .build();
//        }
//        else
//        {
//            myVisionPortal = new VisionPortal.Builder()
//                .setCamera(BuiltinCameraDirection.BACK)
//                .addProcessors(tfod, aprilTag)
//                .build();
//        }
//    }   // end initDoubleVision()
//
//    /**
//     * Add telemetry about AprilTag detections.
//     */
//    private void telemetryAprilTag()
//    {
//        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
//        mOpMode.telemetry.addData("# AprilTags Detected", currentDetections.size());
//
//        // Step through the list of detections and display info for each one.
//        for (AprilTagDetection detection : currentDetections)
//        {
//            if (detection.metadata != null)
//            {
//                mOpMode.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
//                mOpMode.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
//                mOpMode.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
//                mOpMode.telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
//            } else
//            {
//                mOpMode.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
//                mOpMode.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
//            }
//        }   // end for() loop
//
//    }   // end method telemetryAprilTag()
//
//    /**
//     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
//     */
//    public void telemetryTfod()
//    {
//        List<Recognition> currentRecognitions = tfod.getRecognitions();
//        mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());
//
//        // Step through the list of recognitions and display info for each one.
//        for (Recognition recognition : currentRecognitions)
//        {
//            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
//            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
//
//            mOpMode.telemetry.addData(""," ");
//            mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
//            mOpMode.telemetry.addData("- Position", "%.0f / %.0f", x, y);
//            //mOpMode.telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
//        }   // end for() loop
//
//    }   // end method telemetryTfod()
//
//    public AprilTagProcessor getAprilTag()
//    {
//        return aprilTag;
//    }
//
//
//    /**
//     * Function to find the best Recognition of the team prop.
//     * @return Recognition
//     */
//    public Recognition findObject()
//    {
//        Recognition bestRecognition = null;
//        Recognition currentRecognition = null;
//
//        List<Recognition> currentRecognitions = tfod.getRecognitions();
//        mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());
//
//        // Step through the list of recognitions and display info for each one.
//        for (Recognition recognition : currentRecognitions)
//        {
//            double x = (recognition.getLeft() + recognition.getRight()) / 2;
//            double y = (recognition.getTop() + recognition.getBottom()) / 2;
//
//            if (bestRecognition == null)
//            {
//                bestRecognition = recognition;
//            }
//            else
//            {
//                if (bestRecognition.getConfidence() < recognition.getConfidence())
//                {
//                    bestRecognition = recognition;
//                }
//                else
//                {
//
//                }
//            }
//        }
//
//        if (!(currentRecognitions.isEmpty()))
//        {
//            mOpMode.telemetry.addData("", " ");
//            mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", bestRecognition.getLabel(), bestRecognition.getConfidence() * 100);
////            x = ;
////            y = (bestRecognition.getTop() + bestRecognition.getBottom()) / 2;
//            mOpMode.telemetry.addData("- Position", "%.0f / %.0f", (bestRecognition.getLeft() + bestRecognition.getRight()) / 2);
//            //mOpMode.telemetry.addData("- Size", "%.0f x %.0f", bestRecognition.getWidth(), bestRecognition.getHeight());
//
//
//            return bestRecognition;
//        }
//        else
//        {
//            return null;
//        }
//
//    }
//
//
//    /**
//     * @param x The X coordinate of the detected object.
//     * @return AutonomousBase.SpikeLineEnum The zone in which the team prop was found.
//     */
//    public AutonomousBase.SpikeLineEnum findTarget(double x)
//    {
//        //This is supposed to find the target's position. (Made more sense than writing plain code.)
//        AutonomousBase.SpikeLineEnum targetPosition = AutonomousBase.SpikeLineEnum.UNKNOWN; //("targetPosition" means "position of the target", not "goal" position)
//
//        int leftMaximum = 220;
//        int centerMinimum = 221;
//        int centerMaximum = 459;
//        int rightMinimum = 460;
//
//        if(x < leftMaximum)
//        {   //Range for left 50-220
//            targetPosition = AutonomousBase.SpikeLineEnum.LEFT_SPIKE;
//        }
//        else if(x > centerMinimum && x <= centerMaximum)
//        {
//            //Range for the center 221 - 459
//            targetPosition = AutonomousBase.SpikeLineEnum.CENTER_SPIKE;
//        }
//        else if(x >= rightMinimum)
//        {
//            //Range for the right
//            targetPosition = AutonomousBase.SpikeLineEnum.RIGHT_SPIKE;
//        }
//        else
//        {
//            //telemetry.addData("Adjust values", "");
//        }
//
//        return targetPosition;
//    }
//}   // end class

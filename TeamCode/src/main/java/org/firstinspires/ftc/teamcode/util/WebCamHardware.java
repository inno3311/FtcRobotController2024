package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class WebCamHardware
{

   private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

   private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/red_rev1.tflite";

   private static final String[] LABELS = {
         "Trash Panda"
   };

   /**
    *  Declare OpMode members.
    *  */
   private LinearOpMode mOpMode = null;   // gain access to methods in the calling OpMode.


   /**
    * The variable to store our instance of the TensorFlow Object Detection processor.
    */
   private TfodProcessor tfod;

   /**
    * The variable to store our instance of the vision portal.
    */
   private VisionPortal visionPortal;

   public WebCamHardware (LinearOpMode opmode)
   {
      mOpMode = opmode;
   }

   /**
    * Initialize the TensorFlow Object Detection processor.
    */
   public void initTfod() {

      // Create the TensorFlow processor by using a builder.
      //tfod = TfodProcessor.easyCreateWithDefaults();

      tfod = new TfodProcessor.Builder()
//
//            // Use setModelAssetName() if the TF Model is built in as an asset.
//            // Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
            //.setModelAssetName(TFOD_MODEL_ASSET)
            .setModelFileName(TFOD_MODEL_FILE)
//
            .setModelLabels(LABELS)
//            //.setIsModelTensorFlow2(true)
//            //.setIsModelQuantized(true)
//            //.setModelInputSize(300)
.setModelAspectRatio(16.0 / 9.0)
//
            .build();

      // Create the vision portal by using a builder.
      VisionPortal.Builder builder = new VisionPortal.Builder();

      // Set the camera (webcam vs. built-in RC phone camera).
      if (USE_WEBCAM) {
         builder.setCamera(mOpMode.hardwareMap.get(WebcamName.class, "TFod"));
      } else {
         builder.setCamera(BuiltinCameraDirection.BACK);
      }

      // Choose a camera resolution. Not all cameras support all resolutions.
      //builder.setCameraResolution(new Size(640, 480));

      // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
      //builder.enableCameraMonitoring(true);

      // Set the stream format; MJPEG uses less bandwidth than default YUY2.
      //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

      // Choose whether or not LiveView stops if no processors are enabled.
      // If set "true", monitor shows solid orange screen if no processors enabled.
      // If set "false", monitor shows camera view without annotations.
      //builder.setAutoStopLiveView(false);

      // Set and enable the processor.
      builder.addProcessor(tfod);

      // Build the Vision Portal, using the above settings.
      visionPortal = builder.build();

      // Set confidence threshold for TFOD recognitions, at any time.
      tfod.setMinResultConfidence(0.25f);

      // Disable or re-enable the TFOD processor at any time.
      //visionPortal.setProcessorEnabled(tfod, true);

      tfod.setZoom(1.35);

   }   // end method initTfod()

   /**
    * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
    */
   public void telemetryTfod()
   {

      List<Recognition> currentRecognitions = tfod.getRecognitions();
      mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());

      // Step through the list of recognitions and display info for each one.
      for (Recognition recognition : currentRecognitions) {
         double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
         double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

         mOpMode.telemetry.addData(""," ");
         mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
         mOpMode.telemetry.addData("- Position", "%.0f / %.0f", x, y);
         mOpMode.telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
      }   // end for() loop

   }   // end method telemetryTfod()


   public Recognition findObject()
   {
      List<Recognition> currentRecognitions = tfod.getRecognitions();
      mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());

      // Step through the list of recognitions and display info for each one.
      for (Recognition recognition : currentRecognitions)
      {
         double x = (recognition.getLeft() + recognition.getRight()) / 2;
         double y = (recognition.getTop() + recognition.getBottom()) / 2;

         mOpMode.telemetry.addData("", " ");
         mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
         mOpMode.telemetry.addData("- Position", "%.0f / %.0f", x, y);
         mOpMode.telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());

         if (!(currentRecognitions.isEmpty()))
         {
            return currentRecognitions.get(0);
         }
         else
         {
            return null;
         }
      }
      return null;
   }

   public void closeWebcam()
   {
      tfod.shutdown();
   }

}

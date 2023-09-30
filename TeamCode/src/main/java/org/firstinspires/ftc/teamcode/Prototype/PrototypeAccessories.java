package org.firstinspires.ftc.teamcode.Prototype;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PrototypeAccessories
{
    DcMotor arm = null;
    Servo clawRight, clawLeft;

    public PrototypeAccessories(HardwareMap hardwareMap)
    {
        arm = hardwareMap.get(DcMotor.class, "arm");
        clawRight = hardwareMap.servo.get("clawR");
        clawLeft = hardwareMap.servo.get("clawL");
        arm.setDirection(DcMotor.Direction.REVERSE);
    }

    public void arm()
    {

    }
}

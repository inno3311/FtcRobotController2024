package org.firstinspires.ftc.teamcode.ColorSwitch;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColorSwitch
{
    DigitalChannel colorSwitch;

    public ColorSwitch(HardwareMap hardwareMap)
    {
        colorSwitch = hardwareMap.get(DigitalChannel.class, "switch");
        colorSwitch.setMode(DigitalChannel.Mode.INPUT);
    }

    /**
     * Blue team = true
     * Red team = false
     */
    public boolean getTeam()
    {
        return colorSwitch.getState();
    }
}

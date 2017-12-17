package org.firstinspires.ftc.bailey.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;


/**
 * Created by Unnas Husasin on 12/12/17.
 *
 */

public class BTouchSensor {

    //"Real" sensor
    private DigitalChannel sensor;

    // State used for updating telemetry and getting angles
    private boolean pressed;

    /**
     * Custom gyro class used for getting angles and acceleration of robot
     *
     * @param s "real" touch sensor
     */
    public BTouchSensor(DigitalChannel s) {
        this.sensor = s;
    }

    public boolean isPressed() {
        return sensor.getState();
    }

}

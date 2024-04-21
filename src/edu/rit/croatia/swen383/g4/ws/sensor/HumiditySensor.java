package edu.rit.croatia.swen383.g4.ws.sensor;

import edu.rit.marasovic.swen383.thirdparty.sensor.HumidityReader;

/**
 * Implements the Sensor interface to provide a method of reading humidity levels.
 * This class encapsulates functionality specific to humidity measurement through
 * a humidity sensor.
 */
public class HumiditySensor implements Sensor {
    private HumidityReader reader = new HumidityReader();

    /**
     * Constructs a HumiditySensor object.
     */
    public HumiditySensor() {
    }

    /**
     * Reads the current humidity from the sensor.
     * 
     * @return the current humidity as an integer
     */
    @Override
    public int read() {
        return reader.get(); 
    }
}

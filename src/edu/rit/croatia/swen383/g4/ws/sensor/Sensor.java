package edu.rit.croatia.swen383.g4.ws.sensor;

/**
 * Interface for sensor devices used in the weather station.
 * This interface mandates the implementation of the read method,
 * which is used to obtain sensor-specific data.
 */
public interface Sensor {
    /**
     * Reads the current value from the sensor.
     * 
     * @return the sensor's current reading as an integer. The meaning and unit
     * of this value depend on the specific sensor type implementing this interface
     * (e.g., temperature in Celsius, pressure in mbar, humidity in percent).
     */
    int read();
}

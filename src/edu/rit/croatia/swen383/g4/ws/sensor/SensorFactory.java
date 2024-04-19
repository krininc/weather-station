package edu.rit.croatia.swen383.g4.ws.sensor;

import edu.rit.croatia.swen383.g4.ws.util.SensorType;

/**
 * Factory class for creating sensors of various types.
 * Utilizes the Factory Design Pattern to encapsulate the instantiation
 * of sensor objects.
 */
public class SensorFactory {

    /**
     * Creates a sensor of the specified type.
     * 
     * @param type The type of sensor to create, as defined in SensorType enum.
     * @return Sensor The newly created sensor object corresponding to the given type.
     * @throws IllegalArgumentException if the provided type is not supported or unknown.
     */
    public static Sensor createSensor(SensorType type) {
        switch (type) {
            case TEMPERATURE:
                return new TemperatureSensor();
            case PRESSURE:
                return new PressureSensor();
            case HUMIDITY:
                return new HumiditySensor();
            default:
                throw new IllegalArgumentException("Unknown sensor type: " + type);
        }
    }
}

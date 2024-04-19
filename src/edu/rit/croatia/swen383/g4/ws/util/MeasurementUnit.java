package edu.rit.croatia.swen383.g4.ws.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration of measurement units used for sensor data conversion.
 * This enum provides a method to convert raw sensor readings into meaningful units.
 */
public enum MeasurementUnit {
    KELVIN(SensorType.TEMPERATURE, 1, 0),
    CELSIUS(SensorType.TEMPERATURE, 1, -27315),
    FAHRENHEIT(SensorType.TEMPERATURE, 1.8, -45967),
    INHG(SensorType.PRESSURE, 1, 0),
    MBAR(SensorType.PRESSURE, 33.864, 0),
    PCT(SensorType.HUMIDITY, 100, 0);

    private final SensorType type;
    private final double cf1;  // Conversion factor 1
    private final double cf2;  // Conversion factor 2 (offset)

    /**
     * Constructs a MeasurementUnit with specified sensor type and conversion factors.
     * 
     * @param type The type of sensor (e.g., TEMPERATURE, PRESSURE, HUMIDITY)
     * @param cf1 The primary conversion factor
     * @param cf2 The offset added after conversion
     */
    MeasurementUnit(SensorType type, double cf1, double cf2) {
        this.type = type;
        this.cf1 = cf1;
        this.cf2 = cf2;
    }

    /**
     * Converts a raw sensor reading into a unit-specific value.
     * 
     * @param reading The raw sensor reading.
     * @return double The converted reading using this unit's conversion factors.
     */
    public double get(int reading) {
        return (reading * cf1 + cf2) / 100.0;
    }

    /**
     * Retrieves all MeasurementUnits of a specific SensorType.
     * 
     * @param type The SensorType to filter by.
     * @return List<MeasurementUnit> A list of MeasurementUnits corresponding to the specified type.
     */
    public static List<MeasurementUnit> valuesOf(SensorType type) {
        List<MeasurementUnit> list = new ArrayList<>();
        for (MeasurementUnit unit : values()) {
            if (unit.type.equals(type)) {
                list.add(unit);
            }
        }
        return list;
    }
}

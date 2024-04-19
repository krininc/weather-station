package edu.rit.croatia.swen383.g4.ws.sensor;

import java.util.Random;

/**
 * Represents a pressure sensor that simulates atmospheric pressure readings within a defined range.
 * This sensor models pressure changes by randomly adjusting the current pressure value within 
 * the limits, mimicking natural fluctuations in atmospheric pressure.
 */
public class PressureSensor implements Sensor {
    private static final int MIN = 950;       // Minimum pressure value (millibars)
    private static final int MAX = 1050;      // Maximum pressure value (millibars)
    private static final int DEFAULT = 1013;  // Default starting pressure value (millibars)

    private int currentPressure;  // Current simulated pressure
    private boolean increasing = true;  // Direction of pressure change, true if increasing
    private Random rand = new Random();  // Random number generator for pressure fluctuation

    /**
     * Constructs a PressureSensor and initializes the current pressure to the default value.
     */
    public PressureSensor() {
        currentPressure = DEFAULT;
    }

    /**
     * Reads the current pressure. The method simulates natural fluctuations by randomly 
     * increasing or decreasing the current pressure value. The change is constrained between
     * a minimum and maximum value to emulate realistic atmospheric pressure changes.
     *
     * @return the current pressure value in millibars
     */
    @Override
    public int read() {
        // Determine if pressure should start increasing or decreasing at the boundaries
        if (currentPressure <= MIN) {
            increasing = true;
        } else if (currentPressure >= MAX) {
            increasing = false;
        }

        // Apply a random change to the current pressure, direction based on the 'increasing' flag
        if (increasing) {
            currentPressure += rand.nextInt(10);  // Increment pressure randomly between 0 and 9
        } else {
            currentPressure -= rand.nextInt(10);  // Decrement pressure randomly between 0 and 9
        }

        // Ensure the new pressure does not exceed defined boundaries
        currentPressure = Math.min(MAX, currentPressure);
        currentPressure = Math.max(MIN, currentPressure);

        return currentPressure;
    }
}

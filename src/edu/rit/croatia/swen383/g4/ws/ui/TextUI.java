package edu.rit.croatia.swen383.g4.ws.ui;

import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.util.MeasurementUnit;

/**
 * Text-based User Interface (UI) that displays sensor readings in the console.
 * Implements the Observer interface to receive updates from the WeatherStation.
 */
public class TextUI implements Observer {
    private WeatherStation ws;

    /**
     * Constructs a TextUI with a reference to a WeatherStation to observe.
     * 
     * @param ws The WeatherStation this UI will observe for data updates.
     */
    public TextUI(WeatherStation ws) {
        this.ws = ws;
        ws.attach(this);  // Attach this UI as an observer to the WeatherStation
    }

    /**
     * Called by the WeatherStation when new sensor data is available.
     * This method prints the updated readings to the console.
     */
    @Override
    public void update() {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            System.out.printf("Reading for %s is %6.2f%n", unit, ws.getReading(unit));
        }
    }
}

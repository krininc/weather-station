package edu.rit.croatia.swen383.g4.ws;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.observer.Subject;
import edu.rit.croatia.swen383.g4.ws.sensor.Sensor;

import edu.rit.croatia.swen383.g4.ws.ui.UIFactory;
import edu.rit.croatia.swen383.g4.ws.util.DataLogger;
import edu.rit.croatia.swen383.g4.ws.util.MeasurementUnit;
import edu.rit.croatia.swen383.g4.ws.util.SensorType;

/**
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a sensor
 * that reports the temperature as a 16-bit number (0 to 65535) representing the
 * Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 *
 * @author Michael J. Lutz
 * @author Kristina Marasovic
 * @version 1
 */
public class WeatherStation extends Subject implements Runnable {

    private final long PERIOD = 1000; // 1 sec = 1000 ms.
    private EnumMap<SensorType, Sensor> sensorMap;
    private EnumMap<MeasurementUnit, Double> readingMap;
    private Observer text = UIFactory.createUI("text", this);
    private Observer swing = UIFactory.createUI("swing", this);
    private DataLogger logger;

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use.
     */
    public WeatherStation(EnumMap<SensorType, Sensor> sensorMap, String logFilePath) {
        this.sensorMap = sensorMap;
        readingMap = new EnumMap<>(MeasurementUnit.class);
        try {
            logger = new DataLogger(logFilePath);
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }

    private void getSensorReadings() {
        for (SensorType type : SensorType.values()) {
            Sensor sensor = sensorMap.get(type);
            int reading = sensor.read();
            List<MeasurementUnit> units = MeasurementUnit.valuesOf(type);
            for (MeasurementUnit unit : units) {
                double convertedReading = unit.get(reading);
                readingMap.put(unit, convertedReading);
            }
        }
        try {
            if (logger != null) logger.logData(readingMap);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public double getReading(MeasurementUnit unit) {

        return readingMap.get(unit);
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from
     * its sensor, and reports this as a formatted output string.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            getSensorReadings();
            notifyObservers();
            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  
                break;
            }
        }
    }
}
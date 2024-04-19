package edu.rit.croatia.swen383.g4.ws;

import java.util.EnumMap;
import edu.rit.croatia.swen383.g4.ws.sensor.Sensor;
import edu.rit.croatia.swen383.g4.ws.sensor.SensorFactory;
import edu.rit.croatia.swen383.g4.ws.util.SensorType;

public class WeatherStationRunner {
    /*
     * Initial main method.
     * Create the WeatherStation (Runnable) with a logging file path.
     * Embed the WeatherStation in a Thread.
     * Start the Thread.
     */
    public static void main(String[] args) {
        EnumMap<SensorType, Sensor> sensorMap = new EnumMap<>(SensorType.class);
        for (SensorType type : SensorType.values()) {
            sensorMap.put(type, SensorFactory.createSensor(type));  
        }
      
        String logFilePath = "logfile.txt";  

        WeatherStation ws = new WeatherStation(sensorMap, logFilePath);
        Thread thread = new Thread(ws);
        thread.start();
    }
}

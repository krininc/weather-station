package edu.rit.croatia.swen383.g4.ws.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import edu.rit.croatia.swen383.g4.ws.util.MeasurementUnit;

/**
 * Handles the logging of weather data to a file. Data entries are appended with a formatted timestamp
 * and sensor readings in a specified format.
 */
public class DataLogger {
    private BufferedWriter writer;
    private String filePath;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a DataLogger that writes to the specified file path.
     *
     * @param filePath the path of the file where data will be logged
     * @throws IOException if an I/O error occurs while opening the file for writing
     */
    public DataLogger(String filePath) throws IOException {
        this.filePath = filePath;
        writer = new BufferedWriter(new FileWriter(filePath, true));
    }

    /**
     * Logs a single entry of sensor data along with a current timestamp to the file.
     *
     * @param data an EnumMap containing sensor types and their corresponding readings
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void logData(EnumMap<MeasurementUnit, Double> data) throws IOException {
        String formattedDate = dateFormatter.format(new Date());
        writer.write(formattedDate);  
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            writer.write(", " + String.format("%.2f", data.get(unit))); 
        }
        writer.newLine();  
        writer.flush();  
    }

    /**
     * Closes the BufferedWriter stream used for writing data to the file.
     * It is important to call this method after all data has been written to avoid resource leaks.
     *
     * @throws IOException if an I/O error occurs while closing the writer
     */
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}

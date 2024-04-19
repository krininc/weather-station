package edu.rit.croatia.swen383.g4.ws.util;

import java.io.*;

/**
 * Utility class that provides methods to export data from a text file to CSV or JSON format.
 */
public class DataExporter {

    /**
     * Exports data from a specified input file to a new file in CSV format.
     *
     * Each line in the input file is assumed to be formatted with fields separated by ", ".
     * The output file will contain the same data with fields separated by ",".
     *
     * @param inputFile  the path of the file to read the data from
     * @param outputFile the path of the file to write the CSV data to
     * @throws IOException if an I/O error occurs reading from the file or writing to the file
     */
    public static void exportAsCSV(String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.replace(", ", ","));  // Correct spacing for CSV format
                writer.newLine();
            }
        }
    }

    /**
     * Exports data from a specified input file to a new file in JSON format.
     *
     * Each line in the input file is assumed to contain data fields separated by ", ".
     * The output file will format these fields into JSON objects within an array.
     *
     * @param inputFile  the path of the file to read the data from
     * @param outputFile the path of the file to write the JSON data to
     * @throws IOException if an I/O error occurs reading from the file or writing to the file
     */
    public static void exportAsJSON(String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            writer.write("[\n");
            StringBuilder jsonBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                jsonBuilder.append("    {\n");
                jsonBuilder.append(String.format("        \"Timestamp\": \"%s\",\n", parts[0]));
                jsonBuilder.append(String.format("        \"Temperature(K)\": \"%s\",\n", parts[1]));
                jsonBuilder.append(String.format("        \"Temperature(C)\": \"%s\",\n", parts[2]));
                jsonBuilder.append(String.format("        \"Pressure(mbar)\": \"%s\",\n", parts[3]));
                jsonBuilder.append(String.format("        \"Humidity(%%)\": \"%s\"\n", parts[4]));
                jsonBuilder.append("    },\n"); 
            }
            
            if (jsonBuilder.length() > 0) {
                
                jsonBuilder.delete(jsonBuilder.lastIndexOf(","), jsonBuilder.length());
            }
            
            writer.write(jsonBuilder.toString());
            writer.write("\n]");
        }
    }
}
 
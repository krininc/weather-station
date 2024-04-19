package edu.rit.croatia.swen383.g4.ws.ui;

import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;

/**
 * Factory class for creating UI components.
 * This class simplifies the creation of different types of user interfaces
 * for displaying weather station data.
 */
public class UIFactory {

    /**
     * Creates a UI component based on the specified type.
     *
     * @param type The type of UI to create ("text" or "swing").
     * @param ws The WeatherStation instance that the created UI will observe.
     * @return Observer The newly created UI component as an Observer.
     * @throws IllegalArgumentException if the specified UI type is unknown.
     */
    public static Observer createUI(String type, WeatherStation ws) {
        if ("text".equalsIgnoreCase(type)) {
            return new TextUI(ws);
        } else if ("swing".equalsIgnoreCase(type)) {
            return new SwingUI(ws);
        } else {
            throw new IllegalArgumentException("Unknown UI Type: " + type);
        }
    }
}

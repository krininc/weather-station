package edu.rit.croatia.swen383.g4.ws.observer;

public interface Observer {
     /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's observers
     * notified of the change by invoking this method.
     */
    void update();
}

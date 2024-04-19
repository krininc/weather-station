package edu.rit.croatia.swen383.g4.ws.observer;
import java.util.ArrayList;

public class Subject {
    private ArrayList<Observer> observers =  new ArrayList<>();

      /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    public void attach(Observer o){
        if (o == null){
            throw new NullPointerException();
        }
        if(!observers.contains(o)){
            observers.add(o);
        }
    }

      /**
     * Removes an observer from the set of observers of this object.
     *
     * @param o the observer to be deleted.
     */
    public void dettach(Observer o){
        observers.remove(o);
    }

      /**
     * If this object's state has changed, then invoke this method to notify all
     * of its observers.Each observer has its <code>update</code> method called
     * by this method.
     */
    public void notifyObservers(){
        observers.forEach(observer ->{
            observer.update();
        });
    }

}

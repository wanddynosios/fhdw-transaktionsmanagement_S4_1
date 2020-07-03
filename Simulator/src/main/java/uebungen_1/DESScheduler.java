package uebungen_1;

import uebungen_1.events.EndEvent;
import uebungen_1.events.Event;
import util.Semaphore;

import java.util.*;

public class DESScheduler extends Thread {
    private Timer timer = new Timer();

    public synchronized Map<Long, Event> getTimeSubscribtions() {
        return timeSubscribtions;
    }

    private Map<Long, Event> timeSubscribtions = new HashMap<Long, Event>();
    private Semaphore mutex = new Semaphore(1);

    public synchronized long getTime(){
        return this.timer.getCurrentTime();
    }

    @Override
    public void run() {
        getTimeSubscribtions().put(0l, new Event(this));
        getTimeSubscribtions().put(2l, new Event(this));
        getTimeSubscribtions().put(10000l, new EndEvent(this));
        while (!this.isInterrupted()){
            try {
                this.mutex.down();
            } catch (InterruptedException e) {
                this.interrupt();
            }
            try {
                Event t = getTimeSubscribtions().get(getTime());
                t.startProcessing();
            } catch (NullPointerException e){
                this.mutex.up();
            }
            timer.incrementTime();
        }
    }

    public synchronized void subscribeToTime(Long time, Event event) throws Exception{
        try{
            if (!getTimeSubscribtions().get(time).equals(null)){
                throw new Exception("Slot already busy");
            }
            if (time <= getTime()){
                throw new Exception("Desired time has already passed");
            }
            getTimeSubscribtions().put(time, event);
        } catch (NullPointerException e){
        }
        this.mutex.up();
    }

}

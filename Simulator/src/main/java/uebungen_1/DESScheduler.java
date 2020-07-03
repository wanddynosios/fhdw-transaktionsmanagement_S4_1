package uebungen_1;

import uebungen_1.events.EndEvent;
import uebungen_1.events.Event;
import util.Semaphore;

import java.util.*;

public class DESScheduler extends Thread {
    private Timer timer = new Timer();
    public synchronized Map<Long, Event> getTimeSubscriptions() {
        return timeSubscriptions;
    }
    private Map<Long, Event> timeSubscriptions = new HashMap<Long, Event>();
    private Semaphore mutex = new Semaphore(1);

    public synchronized long getTime(){
        return this.timer.getCurrentTime();
    }

    @Override
    public void run() {
        getTimeSubscriptions().put(0l, new Event(this));
        getTimeSubscriptions().put(2l, new Event(this));
        getTimeSubscriptions().put(10000l, new EndEvent(this));
        while (!this.isInterrupted()){
            try {
                this.mutex.down();
            } catch (InterruptedException e) {
                this.interrupt();
            }
            try {
                getTimeSubscriptions().get(getTime()).startProcessing();
            } catch (Exception e){
                System.out.println(e.getMessage());
                this.mutex.up();
            }
            timer.incrementTime();
        }
        System.out.println(timeSubscriptions);
    }

    public synchronized void subscribeToTime(Long time, Event event) throws Exception{
        try{
            if (!getTimeSubscriptions().get(time).equals(null)){
                throw new Exception("Slot already busy");
            }
            if (time <= getTime()){
                throw new Exception("Desired time has already passed");
            }
        } catch (NullPointerException e){
        }
        getTimeSubscriptions().put(time, event);
        this.mutex.up();
    }

}

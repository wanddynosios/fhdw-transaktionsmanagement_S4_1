package Übungen_1;

import java.util.*;

public class DESScheduler extends Thread implements Comparator{
    private PriorityQueue<Event> queue;
    private Timer timer = new Timer();

    public synchronized long getTime(){
        return this.timer.getCurrentTime();
    }

    public Timer getTimer() {
        return this.timer;
    }
    public DESScheduler(){
        queue = new PriorityQueue<Event>(100, this);
        System.out.println(1);
        timer.start();
    }

    @Override
    public void run() {
        while (true) {
            if (getTime() == 2) break;
        }
        queue.add(new Event(this));

        while (true) {
            if (getTime() == 4) break;
        }
        queue.add(new Event(this));

        System.out.println("Running");
        while (queue.iterator().hasNext()){
            queue.poll().startProcessing();
        }
        this.stopTheClock();
    }

    public void stopTheClock() {
        timer.interrupt();
    }

    public int compare(Object o1, Object o2) {
       return ((Event) o1).compareTo((Event) o2);
    }
}

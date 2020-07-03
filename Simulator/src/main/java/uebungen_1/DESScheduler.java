package uebungen_1;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import uebungen_1.events.EndEvent;
import uebungen_1.events.Event;
import uebungen_1.events.InitEvent;

import java.util.*;

public class DESScheduler extends Thread implements Comparator {
    private PriorityQueue<Event> queue = new PriorityQueue<Event>();
    private int executionCounter = 0;
    private Event lastEvent;
    private ExponentialDistribution distribution = new ExponentialDistribution(10);

    public synchronized long getTime(){
        try {
            return this.lastEvent.getPriority();
        } catch (NullPointerException e){
            return 0l;
        }
    }

    @Override
    public void run() {
        queue.add(new InitEvent(this, 0l));
        queue.add(new InitEvent(this, 2l));
        queue.add(new EndEvent(this, 1000000));
        while (!this.isInterrupted()){
            lastEvent = queue.peek();
            Event toAdd = queue.poll().startProcessing();
            addToQueueWithMath(toAdd);
        }
        System.out.println("Has been executed "+executionCounter);
    }

    private void addToQueueWithMath(Event toAdd) {
        toAdd.setPriority(this.getTime() + Math.round(distribution.sample()));
        queue.add(toAdd);
    }

    public synchronized void countExecution(){
        executionCounter++;
    }

    public int compare(Object o1, Object o2) {
        return ((Event) o1).compareTo((Event) o2);
    }

}

package uebungen_1;

import org.apache.commons.math3.distribution.RealDistribution;
import uebungen_1.events.EndEvent;
import uebungen_1.events.Event;
import uebungen_1.events.InitEvent;

import java.util.*;

public class DESScheduler extends Thread implements Comparator {
    private PriorityQueue<Event> queue = new PriorityQueue<Event>();
    private int executionCounter = 0;
    private Event lastEvent;
    private RealDistribution distribution;

    public DESScheduler(RealDistribution distribution){
        this.distribution = distribution;
    }

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
        //queue.add(new InitEvent(this, 2l));
        while (!this.isInterrupted()){
            lastEvent = queue.peek();
            Event toAdd = queue.poll().startProcessing();
            addToQueueWithMath(toAdd);
        }
        //System.out.print(executionCounter+", ");
    }

    private void addToQueueWithMath(Event toAdd) {
        //toAdd.setPriority(this.getTime() + Math.round(distribution.sample()));
        Long random = Math.round(distribution.sample());
        if (random < 1 ){random = random * -1 + 1;}
        queue.add(new EndEvent(this, this.getTime() + random));
        //queue.add(toAdd);
    }

    public synchronized void countExecution(){
        executionCounter++;
    }

    public int compare(Object o1, Object o2) {
        return ((Event) o1).compareTo((Event) o2);
    }

}

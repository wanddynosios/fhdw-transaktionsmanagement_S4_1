package uebungen_1.events;

import uebungen_1.DESScheduler;

public class Event implements Comparable{
    Long priority;

    DESScheduler dESScheduler;
    public Event(DESScheduler DESScheduler){
        //System.out.println("Erstellt");
        this.dESScheduler = DESScheduler;
    }

    public Event startProcessing() {
        dESScheduler.countExecution();
        //System.out.println("EVENT started at "+ dESScheduler.getTime());
        return new Event(dESScheduler);
    }

    public int compareTo(Object o) {
        Event other = (Event) o;
        return this.priority.compareTo(other.getPriority());
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}

package uebungen_1.events;

import uebungen_1.DESScheduler;

public class InitEvent extends Event{
    public InitEvent(DESScheduler desScheduler, long priority){
        super(desScheduler);
        this.priority = priority;
    }
}

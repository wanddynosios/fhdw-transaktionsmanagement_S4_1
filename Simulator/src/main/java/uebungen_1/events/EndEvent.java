package uebungen_1.events;

import uebungen_1.DESScheduler;
import uebungen_2.StaticTimeCounter;

public class EndEvent extends Event {

    @Override
    public Event startProcessing() {
        StaticTimeCounter.addMyTime(priority);
        dESScheduler.countExecution();
        dESScheduler.interrupt();
        return super.startProcessing();
    }

    public EndEvent(DESScheduler DESScheduler, long priority) {
        super(DESScheduler);
        this.priority = priority;
    }


}

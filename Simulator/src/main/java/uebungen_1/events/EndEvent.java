package uebungen_1.events;

import uebungen_1.DESScheduler;

public class EndEvent extends Event {

    @Override
    public Event startProcessing() {
        dESScheduler.interrupt();
        return super.startProcessing();
    }

    public EndEvent(DESScheduler DESScheduler, long priority) {
        super(DESScheduler);
        this.priority = priority;
    }


}

package uebungen_1.events;

import uebungen_1.DESScheduler;

public class EndEvent extends Event {

    public EndEvent(DESScheduler DESScheduler) {
        super(DESScheduler);
    }

    @Override
    public void startProcessing() throws Exception{
        dESScheduler.interrupt();
        super.startProcessing();
    }


}

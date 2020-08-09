package vorlesung.version2.scheduler.specialEvents;

import vorlesung.version2.scheduler.DESOperation;
import vorlesung.version2.scheduler.DESScheduler;

public class DESTerminationEvent implements DESOperation {
    @Override
    public void process() {
        DESScheduler.getScheduler().terminate();
    }
}

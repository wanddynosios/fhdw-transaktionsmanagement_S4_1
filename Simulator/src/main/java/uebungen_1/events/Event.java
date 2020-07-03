package uebungen_1.events;

import uebungen_1.DESScheduler;

public class Event {
    Long creationTime;
    Long desiredTime;
    DESScheduler dESScheduler;
    public Event(DESScheduler DESScheduler){
        System.out.println("Erstellt");
        this.dESScheduler = DESScheduler;
        creationTime = dESScheduler.getTime();
    }

    public void startProcessing() throws Exception{
        System.out.println("EVENT with creationTime "+ creationTime +" started at "+ dESScheduler.getTime());
        desiredTime = dESScheduler.getTime() + 10;
            dESScheduler.subscribeToTime(desiredTime, new Event(dESScheduler));
            System.out.println(creationTime+ " subscribing at "+ dESScheduler.getTime() + " for " + desiredTime);
    }
}

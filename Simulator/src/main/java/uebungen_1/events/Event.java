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

    public void startProcessing(){
        System.out.println("EVENT with creationTime "+ creationTime +" started at "+ dESScheduler.getTime());
        desiredTime = dESScheduler.getTime() + 10;
        try {
            dESScheduler.subscribeToTime(desiredTime, new Event(dESScheduler));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

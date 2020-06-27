package Übungen_1;

public class Event {
    private Timer timer;
    private long time;
    private Scheduler scheduler;
    public Event(Scheduler scheduler){
        this.scheduler = scheduler;
        timer = scheduler.getTimer();
        time = timer.getCurrentTime();
    }

    public Event startProcessing(){
        Long startTime = timer.getCurrentTime();
        while (time < startTime + 10){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = timer.getCurrentTime();
        }
        System.out.println("EVENT: "+scheduler.getTime());
        System.out.println("Scheduler: "+scheduler);
        return new Event(scheduler);
    }
}

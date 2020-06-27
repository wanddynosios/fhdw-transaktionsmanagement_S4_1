package Übungen_1;

public class Event implements Comparable {
    private Timer timer;
    private Long startTime;
    private long time;
    private DESScheduler DESScheduler;
    public Event(DESScheduler DESScheduler){
        System.out.println("Erstellt");
        this.DESScheduler = DESScheduler;
        timer = DESScheduler.getTimer();
        time = timer.getCurrentTime();
        startTime = timer.getCurrentTime();
    }

    public Event startProcessing(){
        while (time < startTime + 10){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = timer.getCurrentTime();
        }
        System.out.println("EVENT with startTime "+startTime+" started at "+ DESScheduler.getTime());
        return new Event(DESScheduler);
    }

    public int compareTo(Object o) {
        Event other = (Event) o;
        return this.startTime.compareTo(other.getStartTime());
    }

    private Long getStartTime() {
        return this.startTime;
    }
}

package uebungen_1;

public class Event implements Comparable {
    private Timer timer;
    private Long creationTime;
    private long time;
    private DESScheduler DESScheduler;
    public Event(DESScheduler DESScheduler){
        System.out.println("Erstellt");
        this.DESScheduler = DESScheduler;
        timer = DESScheduler.getTimer();
        time = timer.getCurrentTime();
        creationTime = timer.getCurrentTime();
    }

    public Event startProcessing(){
        while (time < creationTime + 10){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = timer.getCurrentTime();
        }
        System.out.println("EVENT with creationTime "+ creationTime +" started at "+ DESScheduler.getTime());
        return new Event(DESScheduler);
    }

    public int compareTo(Object o) {
        Event other = (Event) o;
        return this.creationTime.compareTo(other.getCreationTime());
    }

    private Long getCreationTime() {
        return this.creationTime;
    }
}

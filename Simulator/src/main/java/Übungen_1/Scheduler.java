package Übungen_1;

public class Scheduler {
    private long time = 0l;
    private Timer timer = new Timer();

    public long getTime(){
        return this.timer.getCurrentTime();
    }

    public Timer getTimer() {
        return this.timer;
    }
    public Scheduler(){
        timer.start();
    }

    public void stopTheClock() {
        timer.interrupt();
    }
}

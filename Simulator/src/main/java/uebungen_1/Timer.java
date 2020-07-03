package uebungen_1;

public class Timer {
    private static long currentTime = 0l;

    public synchronized long getCurrentTime() {
        return currentTime;
    }

    public synchronized void incrementTime(){
        currentTime++;
        System.out.println("Time: "+currentTime);
    }
}

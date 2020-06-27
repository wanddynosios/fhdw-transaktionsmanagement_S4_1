package Übungen_1;

public class Timer extends Thread{
    private long currentTime = 0l;

    public long getCurrentTime() {
        return currentTime;
    }

    @Override
    public void run() {
        System.out.println("Starting clock");
        while (!this.isInterrupted()){
            currentTime++;
            try {
                this.sleep(200);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}

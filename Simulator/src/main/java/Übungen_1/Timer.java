package Übungen_1;

public class Timer extends Thread{
    private long currentTime = 0l;

    public long getCurrentTime() {
        return currentTime;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()){
            currentTime++;
            try {
                this.sleep(85);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}

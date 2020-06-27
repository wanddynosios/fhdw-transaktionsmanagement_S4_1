package Übungen_1;

public class Runner {
    private static Scheduler scheduler = new Scheduler();

    public static void main(String[] args) {
        Event event = new Event(scheduler);
        for (int i = 0; i < 10 ; i++) {
            event = event.startProcessing();
        }
        scheduler.stopTheClock();
    }
}

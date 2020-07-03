package uebungen_2;

public class StaticTimeCounter {
    private static long count = 0;
    public static synchronized void addMyTime(long time){
        count += time;
    }
    public static long getCount() {
        return count;
    }
    public static void resetCount(){
        count = 0;
    }
}

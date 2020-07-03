package uebungen_1;

import java.util.Random;

public class Runner {

    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(5);
        for (int i = 0; i < 100; i++) {
            int mean = random.nextInt(30);
            if (mean < 1 ) {
                mean = mean * -1 + 1;
            }
            new DESScheduler(mean).start();
            System.out.println("Mean: "+ mean);
        }
    }
}

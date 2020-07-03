package uebungen_2;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import uebungen_1.DESScheduler;

import java.util.ArrayList;
import java.util.List;

public class BakeACake {

    public static void main(String[] args) {
        List<Long> totalTimesMulti = new ArrayList<Long>();
        for (int i = 0; i < 1000; i++) {
            List<Long> totalTimes = new ArrayList<Long>();
            for (int j = 0; j < 15; j++) {
                //Rezept lesen
                Thread rezeptLesen = new DESScheduler(new NormalDistribution(500.0, 200.0));
                rezeptLesen.start();

                //Einkaufen
                Thread einkaufen = new DESScheduler(new ExponentialDistribution(7500));
                einkaufen.start();

                //Abwiegen
                Thread abwiegen = new DESScheduler(new UniformRealDistribution(100, 200));
                abwiegen.start();

                //Rühren
                Thread rühren = new DESScheduler(new NormalDistribution(100.0, 5.0));
                rühren.start();

                //Backen
                Thread backen = new DESScheduler(new NormalDistribution(1000.0, 0.0000000001));
                backen.start();

                try {
                    rezeptLesen.join();
                    einkaufen.join();
                    abwiegen.join();
                    rühren.join();
                    backen.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalTimes.add(StaticTimeCounter.getCount());
                StaticTimeCounter.resetCount();
            }
            Long sum = 0l;
            for (Long l : totalTimes)
                sum += l;
            //System.out.println("Sum: "+sum);
            Long avg = sum / totalTimes.size();
            System.out.println("Avg: "+ avg);
            double[] arr = new double[totalTimes.size()];
            for (int j =0; j < totalTimes.size(); j++)
                arr[j] = totalTimes.get(j);
            double standardabweichung = new StandardDeviation().evaluate(arr);
            //System.out.println("Standardabweichung: " + standardabweichung);
            System.out.println("\t relativ: "+ avg / standardabweichung);
            totalTimesMulti.add(sum);
        }
        Long sum = 0l;
        for (Long l : totalTimesMulti)
            sum += l;
        Long avg = sum / totalTimesMulti.size();
        System.out.println("MultiAvg: "+ avg);


    }
    //Result Aufgabe 4a + b:
    /*218, 1533, 191, 103, 1000,
    59, 5851, 176, 97, 1000,
    635, 3201, 125, 1000, 100,
    433, 2682, 169, 97, 1000,
    702, 5860, 146, 100, 1000,
    272, 5960, 143, 103, 1000,
    132, 18127, 138, 105, 1000,
    3267, 94, 1000, 150, 226,
    544, 1696, 120, 109, 1000,
    395, 1810, 172, 98, 1000,
    29539, 192, 673, 1000, 97,
    483, 3296, 101, 98, 1000,
    789, 5998, 137, 1000, 97,
    585, 11751, 166, 89, 1000,
    678, 10020, 195, 1000, 107, */

    //Result Aufgabe 4c:
    /*[3030, 19372, 4519, 17214, 1788, 12161, 5231, 3280, 10784, 2968, 3932, 7575, 5296, 5218, 30175]
    Sum: 132543
    Avg: 8836
    Standardabweichung: 7941.668160495537
         relativ: 1.112612592396288*/

    //Result Aufgabe 4d:
    //Bei 10: MultiAvg: 148822
    //Bei 100: MultiAvg: 141660
    //Bei 1000: MultiAvg: 137835


}


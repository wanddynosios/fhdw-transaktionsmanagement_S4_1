package uebungen_3;

import vorlesung.version1.modelling.ProcessStep;
import vorlesung.version1.modelling.ProcessStepDelay;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

public class BakeACakeNew {
    //rezept lesen
    @ProcessStepDelay(0)
    public long rezeptLesenDelay(){
        double sample = new NormalDistribution(500,200).sample();
        System.out.println(sample);
        return (long) sample;
    }

    @ProcessStep(0)
    public void rezeptLesen(){
        System.out.println("Lese Rezept");
    }

    //einkaufen
    @ProcessStepDelay(1)
    public long einkaufenDelay(){
        double sample = new ExponentialDistribution(7500).sample();
        System.out.println(sample);
        return (long) sample;
    }

    @ProcessStep(1)
    public void einkaufen(){
        System.out.println("Kaufe ein");
    }

    //abwiegen
    @ProcessStepDelay(2)
    public long abwiegenDelay(){
        double sample = new UniformRealDistribution(100, 200).sample();
        System.out.println(sample);
        return (long) sample;
    }

    @ProcessStep(2)
    public void abwiegen(){
        System.out.println("wiege ab");
    }

    //ruehren
    @ProcessStepDelay(3)
    public long ruehrenDelay(){
        double sample = new NormalDistribution(100, 5).sample();
        System.out.println(sample);
        return (long) sample;
    }

    @ProcessStep(3)
    public void ruehren(){
        System.out.println("ruehre");
    }

    //backen
    @ProcessStepDelay(4)
    public long backenDelay(){
        long sample = 1000l;
        System.out.println(sample);
        return sample;
    }

    @ProcessStep(4)
    public void backen(){
        System.out.println("backe");
    }


}

package trafficLight.demo;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import trafficLight.model.*;
import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.SimulationResult;

public class TrafficSimulation implements Simulation {
    private double arrivalBaseRate;
    private long maxTime;

    public TrafficSimulation(long arrivalBaseRate, long maxTime) {
        this.arrivalBaseRate = arrivalBaseRate;
        this.maxTime = maxTime;
    }


    @Override
    public void start() {
        DESScheduler.setFixedTermination(this.maxTime);
        SimulationResult.setProperty("ArrivalBaseRate", Double.toString(this.arrivalBaseRate));
    }

    @Override
    public void injectStart() {

        Street street1 = new Street("Street-1" , new TrafficGenerator(new ExponentialDistribution(DESScheduler.getRandom(), this.arrivalBaseRate), new Car()));
        Street street2 = new Street("Street-2" , new TrafficGenerator(new ExponentialDistribution(DESScheduler.getRandom(), this.arrivalBaseRate * 2), new Car()));
        Street street3 = new Street("Street-3" , new TrafficGenerator(new ExponentialDistribution(DESScheduler.getRandom(), this.arrivalBaseRate), new Car()));
        Street street4 = new Street("Street-4" , new TrafficGenerator(new ExponentialDistribution(DESScheduler.getRandom(), this.arrivalBaseRate * 2), new Car()));

        street1.defineNext(street2);
        street2.defineNext(street3);
        street3.defineNext(street4);
        street4.defineNext(street1);

        TrafficController controller = new BasicTrafficControl(street1);

        Crossing crossing = new Crossing(street1, street2, street3, street4);

    }

    @Override
    public void finish() {

    }
}

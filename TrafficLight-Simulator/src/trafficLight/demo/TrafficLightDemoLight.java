package trafficLight.demo;

import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.SimulationResult;
import vorlesung.version2.scheduler.Simulator;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TrafficLightDemoLight {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        DESScheduler.setDebug(true);

        Simulator simulator = new Simulator(0);

        simulator.simulate(new TrafficSimulation(20000, 1000000));
        simulator.terminate();

        List<SimulationResult> results = simulator.readResults();
        int simID = 0;
        for (SimulationResult r : results) {
            System.out.println("Simulation: " + simID++ + " rate=" + r.getStaticProperties().getProperty("ArrivalBaseRate"));
            r.printResults();
        }
    }
}

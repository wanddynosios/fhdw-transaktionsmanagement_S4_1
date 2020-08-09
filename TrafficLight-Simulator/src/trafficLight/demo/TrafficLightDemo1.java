package trafficLight.demo;

import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.SimulationResult;
import vorlesung.version2.scheduler.Simulator;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TrafficLightDemo1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        DESScheduler.setDebug(false);

        Simulator simulator = new Simulator(0);

        for (int i = 1; i <= 130; i++) {
            simulator.simulate(new TrafficSimulation(i * 100 + 2000, 100000000L));
        }
        simulator.terminate();

        List<SimulationResult> results = simulator.readResults();
        int simID = 0;
        for (SimulationResult r : results) {
            System.out.println("Simulation: " + simID++ + " rate=" + r.getStaticProperties().getProperty("ArrivalBaseRate"));
            r.printResults();

            //Ergebnis 7A: ca. 12,5 sek
        }
    }
}

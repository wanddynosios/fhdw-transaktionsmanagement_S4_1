package uebungen_3;

import vorlesung.version1.modelling.ModelProcess;
import vorlesung.version1.scheduler.DESScheduler;
import vorlesung.version1.scheduler.Simulation;
import vorlesung.version1.scheduler.Simulator;

public class BakeACakeSim {
    public static void main(String[] args) {
        DESScheduler.setDebug(true);

        Simulator simulator = new Simulator(0);

        Simulation sim = new Simulation() {
            @Override
            public void injectStart() {
                ModelProcess.scheduleProcess(new BakeACakeNew());
            }

            @Override
            public void start() {
                System.out.println("Start...");
            }

            @Override
            public void finish() {
                System.out.println("Fertig...");
            }
        };

        for (int i = 0; i < 15; i++) {
            simulator.simulate(sim);
        }
        simulator.terminate();
    }
}

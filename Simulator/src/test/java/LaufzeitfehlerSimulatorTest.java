import vorlesung.version1.scheduler.DESScheduler;
import vorlesung.version1.scheduler.Simulation;
import vorlesung.version1.scheduler.Simulator;
import vorlesung.version1.spielwiese.TestEventExponential;
import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LaufzeitfehlerSimulatorTest {
    Simulation sim;
    Simulator simulator;
    @Before
    public void setUp(){
        sim = new Simulation() {

            public void injectStart() {
                DESScheduler.schedule(new TestEventExponential(), 0l);
                DESScheduler.schedule(new TestEventExponential(), 2l);
            }

            public void start() {
                System.out.println("Start...");
            }

            public void finish() {
                System.out.println("Fertig");
            }
        };

        simulator = new Simulator(0);
    }

    @Test
    public void test1(){
         //Arrange
        doThrow(new NullPointerException()).when(DESScheduler.getScheduler()).execute(any(Simulation.class), any(RandomGenerator.class));

        //Act
        for (int i = 0; i < 2; i++) {
            simulator.simulate(sim);
        }
        simulator.terminate();

         //Assert
        //verify(DESScheduler.getScheduler().execute(any(Simulation.class), any(RandomGenerator.class)), times(2));


     }

}

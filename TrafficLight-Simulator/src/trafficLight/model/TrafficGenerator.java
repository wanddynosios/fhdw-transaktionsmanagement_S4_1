package trafficLight.model;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.modelling.ModelProcess;

public class TrafficGenerator {
    private AbstractRealDistribution randomDistribution;
    private Vehicle prototype;
    private Street street;

    public TrafficGenerator(AbstractRealDistribution randomDistribution, Vehicle prototype) {
        this.randomDistribution = randomDistribution;
        this.prototype = prototype;
    }

    public void attachStreet(Street street) {
        this.street = street;
        ModelProcess.scheduleProcess(this);
    }

    @ProcessStepDelay(0)
    public long startDelay() {
        return (long) randomDistribution.sample();
    }

    @ProcessStep(0)
    public void scheduleNext() {
        this.street.addVehicle(this.prototype.createNewVehicle());
        ModelProcess.scheduleProcess(this);
    }
}

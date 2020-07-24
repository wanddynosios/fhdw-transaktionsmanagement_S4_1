package trafficLightModel;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import vorlesung.version1.modelling.ProcessStep;
import vorlesung.version1.modelling.ProcessStepDelay;
import vorlesung.version2.modelling.ModelProcess;

public class TrafficGenerator {
    private final AbstractRealDistribution randomDistribution;
    private final Vehicle prototype;
    private Street street;

    public TrafficGenerator(AbstractRealDistribution randomDistribution, Vehicle prototype){
        this.randomDistribution = randomDistribution;
        this.prototype = prototype;
    }

    public void attachStreet(Street street){
        this.street = street;
        ModelProcess.scheduleProcess(this);
    }

    @ProcessStepDelay(0)
    public long startDelay(){
        return (long) randomDistribution.sample();
    }

    @ProcessStep(0)
    public void scheduleNext(){
        this.street.addVehicle(this.prototype.createNewVehicle());
        ModelProcess.scheduleProcess(this);
    }
}

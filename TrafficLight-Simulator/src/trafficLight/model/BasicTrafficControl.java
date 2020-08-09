package trafficLight.model;

import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;

public class BasicTrafficControl implements TrafficController {

    private static final long STATIC_CYCLE_TIME = 20000;  // ~20s
    private Street currentStreet;


    public BasicTrafficControl(Street greenStreet) {
        this.currentStreet = greenStreet;
        ModelProcess.scheduleProcess(this);
    }

    @Override
    public long getGreenTime(Street street) {
        //TODO Task 7c
        return STATIC_CYCLE_TIME;
    }

    @ProcessStepDelay(0)
    public long transitionDelay() {
        return TrafficController.LightTransitionTime;
    }

    @ProcessStep(0)
    public void switchGreen() {
        this.currentStreet.switchToGreen();
    }

    @ProcessStepDelay(1)
    public long greenDelay() {
        return STATIC_CYCLE_TIME;
    }

    @ProcessStep(1)
    public void switchRed() {
        this.currentStreet.switchToRed();
        this.currentStreet = currentStreet.getNextStreet();
        ModelProcess.scheduleProcess(this);
    }
}

package trafficLightModel;

import vorlesung.version1.modelling.ProcessStepDelay;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;

public class BasicTrafficControl implements TrafficController {
    private static final long STATIC_CYCLE_TIME = 20000; //20 sek
    private final Street currentStreet;

    public BasicTrafficControl(Street greenStreet){
        this.currentStreet = greenStreet;
        ModelProcess.scheduleProcess(this);
    }

    @ProcessStepDelay(0)
    public long transitionDelay(){
        return TrafficController.LightTransitionTime;
    }

    @ProcessStep(0)
    public void switchGreen(){
        this.currentStreet.switchToGreen();
    }

    @Override
    public long getGreenTime(Street street) {
        return STATIC_CYCLE_TIME;
    }
}

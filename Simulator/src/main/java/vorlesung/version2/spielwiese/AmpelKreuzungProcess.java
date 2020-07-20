package vorlesung.version2.spielwiese;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import vorlesung.version2.evaluation.EvaluationInterval;
import vorlesung.version2.evaluation.aggregation.MaxCharacteristic;
import vorlesung.version2.evaluation.aggregation.MeanCharacteristic;
import vorlesung.version2.evaluation.aggregation.StandardDeviationCharacteristic;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.scheduler.DESScheduler;

import java.util.*;

public class AmpelKreuzungProcess {
    private String name;
//    private EvaluationInterval ampel1 = new EvaluationInterval("Ampel 1 ", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic());
//    private EvaluationInterval ampel2 = new EvaluationInterval("Ampel 2 ", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic());
//    private EvaluationInterval ampel3 = new EvaluationInterval("Ampel 3 ", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic());
//    private EvaluationInterval ampel4 = new EvaluationInterval("Ampel 4 ", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic());
    private EvaluationInterval wartezeit = new EvaluationInterval("Global ", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic());
    private ExponentialDistribution hauptstrasse;
    private ExponentialDistribution nebenstrasse;
    private List<Long> warteschlange1 = new ArrayList<Long>();
    private List<Long> warteschlange2 = new ArrayList<Long>();
    private List<Long> warteschlange3 = new ArrayList<Long>();
    private List<Long> warteschlange4 = new ArrayList<Long>();


    public AmpelKreuzungProcess(String name, double nebenstrasseH){
        this.name = name;
        this.hauptstrasse= new ExponentialDistribution(DESScheduler.getRandom(), nebenstrasseH * 2);
        this.nebenstrasse= new ExponentialDistribution(DESScheduler.getRandom(), nebenstrasseH);
    }

    @ProcessStepDelay(0)
    public long ampel1Delay() {
        return 20;
    }
    @ProcessStep(0)
    public void ampel1() {
        DESScheduler.log("  Ampel 1 / " + DESScheduler.getSimulationTime());
        autosDurchlassen(warteschlange1, hauptstrasse);
    }
    @ProcessStepDelay(1)
    public long pause1Delay(){
        return 5;
    }
    @ProcessStep(1)
    public void pause1(){
        DESScheduler.log("  Pause 1 / " + DESScheduler.getSimulationTime());
    }
    @ProcessStepDelay(2)
    public long ampel2Delay() {
        return 20;
    }
    @ProcessStep(2)
    public void ampel2() {
        DESScheduler.log("  Ampel 2 / " + DESScheduler.getSimulationTime());
        autosDurchlassen(warteschlange2, nebenstrasse);
    }
    @ProcessStepDelay(3)
    public long pause2Delay(){
        return 5;
    }
    @ProcessStep(3)
    public void pause2(){
        DESScheduler.log("  Pause 2 / " + DESScheduler.getSimulationTime());
    }
    @ProcessStepDelay(4)
    public long ampel3Delay() {
        return 20;
    }
    @ProcessStep(4)
    public void ampel3() {
        DESScheduler.log("  Ampel 3 / " + DESScheduler.getSimulationTime());
        autosDurchlassen(warteschlange3, hauptstrasse);
    }
    @ProcessStepDelay(5)
    public long pause3Delay(){
        return 5;
    }
    @ProcessStep(5)
    public void pause3(){
        DESScheduler.log("  Pause 3 / " + DESScheduler.getSimulationTime());
    }
    @ProcessStepDelay(6)
    public long ampel4Delay() {
        return 20;
    }
    @ProcessStep(6)
    public void ampel4() {
        DESScheduler.log("  Ampel 4 / " + DESScheduler.getSimulationTime());
        autosDurchlassen(warteschlange4, nebenstrasse);
    }
    @ProcessStepDelay(7)
    public long pause4Delay(){
        return 5;
    }
    @ProcessStep(7)
    public void pause4(){
        DESScheduler.log("  Pause 4 / " + DESScheduler.getSimulationTime());
        if (DESScheduler.getSimulationTime() < 100000000){
            ModelProcess.scheduleProcess(this);
        }
        if (DESScheduler.getSimulationTime() > 1000){
            wartezeit.intervalStop();
        }
    }

    public void autosDurchlassen(List<Long> warteschlange, ExponentialDistribution distr){
        Long newCars = Double.valueOf(distr.sample()).longValue();
        for (int i = 0; i < newCars - 1; i++) {
            warteschlange.add(DESScheduler.getSimulationTime());
        }
        Collections.sort(warteschlange, Collections.reverseOrder());
        for (int i = 0; i < 10; i++) {
            if (!warteschlange.isEmpty()){
                long toTrigger = warteschlange.remove(0);
                wartezeit.trigger(toTrigger);
            }
        }
    }
}

package vorlesung.version2.evaluation;

import vorlesung.version2.evaluation.aggregation.EvaluationPersistantCharacteristic;

public class EvaluationValue extends SimulationEvaluatorWithStore {
    public EvaluationValue(String name, Object sut, EvaluationPersistantCharacteristic... characteristics) {
        super(name, sut, characteristics);
    }

    public void trigger(double value){
        this.addData(value);
    }
}

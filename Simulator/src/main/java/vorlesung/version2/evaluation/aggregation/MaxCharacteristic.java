package vorlesung.version2.evaluation.aggregation;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class MaxCharacteristic implements EvaluationPersistantCharacteristic{
    @Override
    public double eval(ResizableDoubleArray data) {
        return data.compute(new Max());
    }

    public String getDescription() {
        return "Max=";
    }
}

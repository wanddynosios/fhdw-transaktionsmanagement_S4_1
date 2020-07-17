package vorlesung.version2.evaluation.aggregation;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class MeanCharacteristic implements EvaluationPersistantCharacteristic {

	@Override
	public double eval(ResizableDoubleArray data) {
		return data.compute(new Mean());
	}

	@Override
	public String getDescription() {
		return "Mean";
	}

}

package vorlesung.version2.scheduler;

import vorlesung.version2.evaluation.SimulationEvaluator;

import java.util.*;
import java.util.stream.Collectors;


public class SimulationResult {


	private static void setResult(SimulationResult result) {
		((SimulatorThread)Thread.currentThread()).setResult(result);
	}

	public static SimulationResult getResult() {
		return ((SimulatorThread)Thread.currentThread()).getResult();
	}

	private Simulation simulation;
	private HashMap<Class<?>, HashMap<Object, List<SimulationEvaluator>>> resultMap;
	private Properties properties;


	public SimulationResult(Simulation sim) {
		this.simulation = sim;
		this.resultMap = new HashMap<Class<?>, HashMap<Object, List<SimulationEvaluator>>>();
		this.properties = new Properties();
	}

	public static void setProperty(String key, String value) {
		Thread t = Thread.currentThread();
		if(! (t instanceof SimulatorThread)) {
			throw new SimulationException("Simulation thread type invalid");
		}
		SimulationResult.getResult().addStaticProperty(key, value);
	}

	public static void registerResult(SimulationResult result) {
		Thread t = Thread.currentThread();
		if(! (t instanceof SimulatorThread)) {
			throw new SimulationException("Simulation thread type invalid");
		}
		SimulationResult.setResult(result);
	}

	public void register(SimulationEvaluator simulationEvaluator, Object sut, String name) {
		Class<? extends Object> sutClass = sut.getClass();
		HashMap<Object, List<SimulationEvaluator>> classMap = this.resultMap.get(sutClass);
		if (classMap == null) {
			classMap = new HashMap<Object, List<SimulationEvaluator>>();
			this.resultMap.put(sutClass, classMap);
		}
		List<SimulationEvaluator> objectList = classMap.get(sut);
		if( objectList == null) {
			objectList = new LinkedList<SimulationEvaluator>();
			classMap.put(sut, objectList);
		}
		objectList.add(simulationEvaluator);
	}

	public void printResults() {
		List<Class<?>> classList = this.resultMap.keySet().stream().sorted(Comparator.comparing(Class::toString)).collect(Collectors.toList());
		for (Class<?> c : classList) {
			this.printResults(c);
		}
	}

	private void printResults(Class<?> c) {
		List<Object> objectList =  this.resultMap.get(c).keySet().stream().sorted(Comparator.comparing(Object::toString)).collect(Collectors.toList());
		for (Object o : objectList) {
			printResults(c, o);
		}
	}

	private void printResults(Class<?> c, Object o) {
		List<SimulationEvaluator> evaluatorList = this.resultMap.get(c).get(o);
		for (SimulationEvaluator e : evaluatorList) {
			System.out.println(c.toString() + ": " + o.toString() + ": " + e.toString() + " = " + e.eval());
		}

	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void addStaticProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}

	public Properties getStaticProperties() {
		return this.properties;
	}
}

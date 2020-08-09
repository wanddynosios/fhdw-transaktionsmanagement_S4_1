package trafficLight.model;

import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.evaluation.EvaluationValue;
import vorlesung.version2.evaluation.aggregation.CountCharacteristic;
import vorlesung.version2.evaluation.aggregation.MaxCharacteristic;
import vorlesung.version2.evaluation.aggregation.MeanCharacteristic;
import vorlesung.version2.evaluation.aggregation.StandardDeviationCharacteristic;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.scheduler.DESScheduler;

import java.util.LinkedList;
import java.util.Queue;

public class Street {
    private String name;
    private boolean isGreen;
    private Queue<Vehicle> queue;
    private Street sequenceNext;

    private EvaluationValue carStats;
    private EvaluationValue queueStats;

    public Street(String name, TrafficGenerator... generator) {
        this.name = name;
        for (TrafficGenerator g : generator) {
            g.attachStreet(this);
        }
        this.isGreen = false;
        this.queue = new LinkedList<Vehicle>();

        this.carStats = new EvaluationValue("WaitTime", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic(), new CountCharacteristic());
        this.queueStats = new EvaluationValue("QueueSize", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new MaxCharacteristic(), new CountCharacteristic());
    }

    public void defineNext(Street sequenceNext) {
        this.sequenceNext = sequenceNext;
    }

    public Street getNextStreet() {
        return this.sequenceNext;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public void switchToGreen() {
        this.isGreen = true;
        ModelProcess.scheduleProcess(this);

        DESScheduler.log("Traffic light green (" + this.name + ") t=" + DESScheduler.getSimulationTime());

        this.queueStats.trigger(this.getQueueSize());
    }

    public void switchToRed() {
        this.isGreen = false;
        DESScheduler.log("Traffic light red (" + this.name + ") t=" + DESScheduler.getSimulationTime());
    }

    public void addVehicle(Vehicle v) {
        v.arrival();
        DESScheduler.log("Car arrival @ " + this.name + " t=" + DESScheduler.getSimulationTime());

        if( isGreen ) {
            if( ! this.queue.isEmpty()) {
                this.queue.offer(v);
            }else {
                long waitTime = v.departure();
                this.carStats.trigger(waitTime);
            }
        }else {
            this.queue.offer(v);
        }
    }

    @ProcessStepDelay(0)
    public long startDelay() {
        //TODO Task 7b (if first in queue = different)
        return (long) TrafficController.VehiclePassTime;
    }

    @ProcessStep(0)
    public void nextCar() {
        if (this.isGreen && ! this.queue.isEmpty()) {
            Vehicle vehicle = this.queue.poll();

            long waitTime = vehicle.departure();
            this.carStats.trigger(waitTime);

            DESScheduler.log("Car depature @ " + this.name + " t=" + DESScheduler.getSimulationTime());
            ModelProcess.scheduleProcess(this);
        }
    }


    @Override
    public String toString() {
        return this.name;
    }

}

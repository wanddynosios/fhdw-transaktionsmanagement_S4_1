package trafficLightModel;

import vorlesung.version1.modelling.ProcessStep;
import vorlesung.version1.modelling.ProcessStepDelay;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.scheduler.DESScheduler;

import java.util.LinkedList;
import java.util.Queue;

public class Street {
    private boolean isGreen;
    private final Queue<Vehicle> queue;
    private String name;
    private Street sequenceNext;

     public Street(String name, TrafficGenerator... generators){
         this.name = name;
         for (TrafficGenerator trafficGenerator : generators)
             trafficGenerator.attachStreet(this);
         this.isGreen = false;
         this.queue = new LinkedList<Vehicle>();
         //TODO init logging
     }

     public void addVehicle(Vehicle newVehicle) {
        newVehicle.arrival();
         DESScheduler.log("Car arrival @ "+this.name+", t="+DESScheduler.getSimulationTime());

         if (isGreen){
            if (!this.queue.isEmpty()){
                this.queue.offer(newVehicle);
            } else {
                long waitTime = newVehicle.departure();
                //TODO logging
            }
        } else{
            this.queue.offer(newVehicle);
        }

    }

    public void defineNext(Street sequenceNext){
         this.sequenceNext = sequenceNext;
    }

    public void switchToGreen(){
        this.isGreen = true;
        ModelProcess.scheduleProcess(this);
        //TODO logging
    }

    public void switchToRed(){
         this.isGreen = false;
         DESScheduler.log("Traffic light green ("+this.name+") t="+DESScheduler.getSimulationTime());
    }

    public Street getNextStreet(){
         return this.sequenceNext;
    }
    public int getQueueSize(){
         return queue.size();
    }

    @ProcessStepDelay(0)
    public long startDelay(){
         return TrafficController.VehiclePassTime;
    }

    @ProcessStep(0)
    public void nextCar(){
         if (isGreen && !queue.isEmpty()){
             Vehicle vehicle = this.queue.poll();
             long waitTime = vehicle.departure();
             //TODO logging
             DESScheduler.log("Car deaprture @ "+this.name+", t="+DESScheduler.getSimulationTime());
             ModelProcess.scheduleProcess(this); //neuer Prozess wird immer weiter aufgerufen, solange Ampel grün
         }
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                '}';
    }
}

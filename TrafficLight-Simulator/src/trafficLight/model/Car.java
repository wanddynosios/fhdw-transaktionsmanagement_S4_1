package trafficLight.model;

import vorlesung.version2.scheduler.DESScheduler;

public class Car implements Vehicle {

    private long arrivalTime;

    @Override
    public Vehicle createNewVehicle() {
        return new Car();
    }

    @Override
    public void arrival() {
        this.arrivalTime = DESScheduler.getSimulationTime();
    }

    @Override
    public long departure() {
        return DESScheduler.getSimulationTime() - this.arrivalTime;
    }
}

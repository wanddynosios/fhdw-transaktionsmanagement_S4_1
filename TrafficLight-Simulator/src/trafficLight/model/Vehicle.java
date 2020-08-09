package trafficLight.model;

public interface Vehicle {
    Vehicle createNewVehicle();
    void arrival();
    long departure();
}

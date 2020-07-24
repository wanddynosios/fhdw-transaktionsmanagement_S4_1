package trafficLightModel;

public interface Vehicle {
    Vehicle createNewVehicle();
    void arrival();
    long departure();
}

package trafficLight.model;

public interface TrafficController {
    long VehiclePassTime = 2000;        //ms
    long LightTransitionTime = 5000;    //ms

    long getGreenTime(Street street);
}

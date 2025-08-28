package IA;

public class Vehicle {
    private String vehicleId;
    private String serialNum;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleColor;
    private String status;
    private boolean helmetsConfirmed;
    private boolean lightsConfirmed;

    public Vehicle(String status, String vehicleColor, String vehicleBrand,
                   String vehicleType, String serialNum, String vehicleId) {
        this.status = status;
        this.vehicleColor = vehicleColor;
        this.vehicleBrand = vehicleBrand;
        this.vehicleType = vehicleType;
        this.serialNum = serialNum;
        this.vehicleId = vehicleId;
    }

}

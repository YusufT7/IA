package IA;

public class Vehicle {
    private String vehicleId;
    private String serialNum;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleColor;
    private String vehicleSecondColor;
    private String status;
    private boolean helmetsConfirmed;
    private boolean lightsConfirmed;

    public Vehicle(String status, String vehicleColor, String vehicleSecondColor, String vehicleBrand,
                   String vehicleType, String serialNum) {
        this.status = status;
        this.vehicleColor = vehicleColor;
        this.vehicleSecondColor = vehicleSecondColor;
        this.vehicleBrand = vehicleBrand;
        this.vehicleType = vehicleType;
        this.serialNum = serialNum;
    }

    public Vehicle(String vehicleId, String serialNum, String vehicleType, String status) {
        this.vehicleId = vehicleId;
        this.serialNum = serialNum;
        this.vehicleType = vehicleType;
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleSecondColor() {
        return vehicleSecondColor;
    }

    public void setVehicleSecondColor(String vehicleSecondColor) {
        this.vehicleSecondColor = vehicleSecondColor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHelmetsConfirmed() {
        return helmetsConfirmed;
    }

    public void setHelmetsConfirmed(boolean helmetsConfirmed) {
        this.helmetsConfirmed = helmetsConfirmed;
    }

    public boolean isLightsConfirmed() {
        return lightsConfirmed;
    }

    public void setLightsConfirmed(boolean lightsConfirmed) {
        this.lightsConfirmed = lightsConfirmed;
    }
    @Override
    public String toString() {
        return vehicleType + " | " + vehicleId + " | " + serialNum + " | " + status + "\n";
    }
}

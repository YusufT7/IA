package IA;

import java.util.ArrayList;

public class Registered {
    private static Registered instance = new Registered(); // shared object
    private ArrayList<User> users = new ArrayList<>();
    private static int registeredVehicleCount = 0;

    private Registered() {} // private constructor

    public static Registered getInstance() {
        return instance;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public void addVehicle(Vehicle vehicle, int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                user.getVehicles().add(vehicle);
                registeredVehicleCount++;
                break;
            }
        }
    }

    public ArrayList<Vehicle> getRegisteredVehicles(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user.getVehicles();
            }
        }
        return new ArrayList<>();
    }
}

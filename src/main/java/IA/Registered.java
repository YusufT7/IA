package IA;

import java.util.ArrayList;

public class Registered {
    private ArrayList<User> users;
    public Registered(){
        users = new ArrayList<User>();
    }
    public void addUser(User user){
        users.add(user);
    }
    public void addVehicle(Vehicle vehicle, int id){
        for(int i=0; i<users.size(); i++){
            if(id == users.get(i).getUserId()){
                users.get(i).getVehicles().add(vehicle);
            }
        }

    }
}

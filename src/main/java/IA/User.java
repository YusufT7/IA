package IA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String phoneNum;
    private String userType; //user admin
    private String password;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static User currentUser;

    public User(int userId, String email, String phoneNum, String first_name, String last_name) {
        this.userId = userId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }


    public static void storeCurrentUser(String email, String password){
        User user = new User(email, password);
        User.setCurrentUser(user);
    }

    //finds and returns the userId (of the current user using the website) from database
    public static int getUserIdDB(Connection conn, String email) throws SQLException {
            int userId = 0;
            String userQuery = "SELECT user_id FROM users WHERE email = ?";
            PreparedStatement userPs = conn.prepareStatement(userQuery);
            userPs.setString(1, email);
            userPs.executeQuery();
            ResultSet rs = userPs.getResultSet();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
            return userId;
    }

}

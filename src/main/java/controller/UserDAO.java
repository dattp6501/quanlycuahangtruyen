package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO extends DAO{
    public UserDAO(){
        super();
    }
    public boolean checkLogin(User u) {
        String sql ="SELECT * FROM tbluser WHERE username = ? AND password = ?";
        try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, u.getUsername());
                preparedStatement.setString(2, u.getPassword());
                ResultSet result = preparedStatement.executeQuery();
                boolean ok = false;
                if(result.next()){
                    u.setId(result.getString("uid"));
                    u.setFullname(result.getString("fullname"));
                    u.setPosition(result.getString("position"));
                    ok = true;
                }
                result.close();
                preparedStatement.close();
                connection.close();
                return ok;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addUser(User u){
        String sql = "INSERT INTO tbluser(uid,fullname,username,password,position) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,u.getId());
            preparedStatement.setString(2,u.getFullname());
            preparedStatement.setString(3,u.getUsername());
            preparedStatement.setString(4,u.getPassword());
            preparedStatement.setString(5,u.getPosition());
            int ok = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return ok>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

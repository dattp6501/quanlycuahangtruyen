package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.NCC;

public class NCCDAO extends DAO{

    public NCCDAO() {
        super();
    }
    public ArrayList<NCC> searchNCC(String key){
        ArrayList<NCC> listSup = new ArrayList<>();
        String sql = "SELECT * FROM tblncc WHERE name LiKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+key+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listSup.add(new NCC(resultSet.getString("sid"),
                                    resultSet.getString("name"),
                                    resultSet.getString("address"),
                                    resultSet.getString("phone"),
                                    resultSet.getString("email"),
                                    resultSet.getString("described"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSup;
    }
    public boolean addNCC(NCC ncc){
        String sql = "INSERT INTO tblncc(sid,name,address,phone,email,described) "
        + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,ncc.getId());
            preparedStatement.setString(2,ncc.getName());
            preparedStatement.setString(3,ncc.getAddress());
            preparedStatement.setString(4,ncc.getPhone());
            preparedStatement.setString(5,ncc.getEmail());
            preparedStatement.setString(6,ncc.getDescribed());
            int ok = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                ncc.setId(resultSet.getString(1));
            }
            resultSet.close();
            preparedStatement.close();
            // connection.close();
            return ok>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void close() throws SQLException{
        super.close();
    }
    public Connection getConnection(){
        return super.getConnection();
    }
}

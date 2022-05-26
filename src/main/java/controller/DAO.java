package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DAO
 */
public class DAO {
    protected Connection connection = null;
    public DAO(){
        if(connection != null)
            return;
        try {
            String jdbcUrl = "jdbc:sqlite:/C:\\Users\\Administrator\\quanlycuahangthuetruyen.db"; 
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public void close() throws SQLException{
        connection.close();
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}

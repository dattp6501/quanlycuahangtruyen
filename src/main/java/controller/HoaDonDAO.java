package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.HoaDon;
import model.TruyenDuocNhap;

public class HoaDonDAO extends DAO{

    public HoaDonDAO() {
        super();
    }
    public boolean saveHoaDon(HoaDon hd){
        String sql = "INSERT INTO tblhoadon(ngay,mota,sid,uid) "
        +"VALUES(?,?,?,?)";
        try {
            // luu hoa don
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            preparedStatement.setString(1, format.format(new Date()));
            preparedStatement.setString(2, hd.getMota());
            preparedStatement.setString(3, hd.getNcc().getId());
            preparedStatement.setString(4,hd.getUser().getId());
            int ok = preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                System.out.println("id = "+res.getInt(1));
                hd.setId(res.getInt(1));
            }
            res.close();
            preparedStatement.close();
            // them truyen duoc nhap
            sql =  "INSERT INTO tbltruyenduocnhap(soluong,gia,mota,tid,bid) "
            +"VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for(TruyenDuocNhap t : hd.getDs()){
                preparedStatement.setInt(1, t.getSoluong());
                preparedStatement.setDouble(2, t.getGianhap());
                preparedStatement.setString(3, t.getMota());
                preparedStatement.setString(4, t.getTruyen().getId());
                preparedStatement.setInt(5, hd.getId());
                ok += preparedStatement.executeUpdate();
            }
            preparedStatement.close();

            TruyenDAO truyenDAO = new TruyenDAO();
            truyenDAO.setConnection(connection);
            if(truyenDAO.updateSoLuongTruyen(hd.getDs(), hd.getNcc())){
                System.out.println("cap nhat so luong truyen thanh cong");
            }
            // connection.close();
            return ok>hd.getDs().size();
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

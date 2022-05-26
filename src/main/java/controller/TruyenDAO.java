package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.NCC;
import model.Truyen;
import model.TruyenDuocCungCap;
import model.TruyenDuocNhap;

public class TruyenDAO extends DAO{

    public TruyenDAO() {
    }
    public ArrayList<TruyenDuocCungCap> seachTruyen(NCC ncc,String key){
        ArrayList<TruyenDuocCungCap> listTruyen = new ArrayList<>();
        String sql = "SELECT t.tid,t.ten,t.loai,t.giathue AS gt,t.gianhap AS gn,t.soluong AS sl,t.mota AS mt,tdcc.gianhap AS gncc,tdcc.soluong AS slcc,tdcc.mota AS mtcc "
        +"FROM tblncc AS ncc "
        +"INNER JOIN tbltruyenduoccungcap AS tdcc ON ncc.sid = tdcc.sid "
        +"INNER JOIN tbltruyen AS t ON tdcc.tid = t.tid "
        +"WHERE ncc.sid = ? AND t.ten LIKE ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ncc.getId());
            preparedStatement.setString(2, "%"+key+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //truyen cua cua hang
                String tid = resultSet.getString("tid");
                String tten = resultSet.getString("ten");
                String tloai = resultSet.getString("loai");
                float tgiathue = resultSet.getFloat("gt");
                float tgianhap = resultSet.getFloat("gn");
                int tsoluong = resultSet.getInt("sl");
                String tmota = resultSet.getString("mt");
                Truyen truyen = new Truyen(tid, tten, tloai, tgiathue, tgianhap, tsoluong, tmota);
                //

                //truyen cua nha cung cap
                int soluong = resultSet.getInt("slcc");
                float gianhap = resultSet.getFloat("gncc");
                String mota = resultSet.getString("mtcc");
                listTruyen.add(new TruyenDuocCungCap(ncc, truyen, soluong, gianhap, mota)); 
            }
            resultSet.close();
            preparedStatement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTruyen;
    }
    public boolean addTruyen(TruyenDuocCungCap t){
        int oktruyencuanhacungcap = 0;
        int oktruyencuacuahang = 1;
        try {
            String sqlcheck = "SELECT tid FROM tbltruyen WHERE tid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlcheck);
            // kiem tra xem truyen da ton tai trong cua hang chua
            preparedStatement.setString(1,t.getTruyen().getId().toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean yes = false;
            if(resultSet.next()){
                yes = true;
            }
            resultSet.close();
            preparedStatement.close();
            //xu ly truyen cua ca cua hang
            if(!yes){// truyen chua co trong danh sach truyen cua cua hang
                // them truyen moi vao danh sach truyen cua cua hang
                String sqlthemtruyen = "INSERT INTO tbltruyen(tid,ten,loai,giathue,gianhap,soluong,mota) VALUES(?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlthemtruyen);
                preparedStatement.setString(1,t.getTruyen().getId());
                preparedStatement.setString(2,t.getTruyen().getTen());
                preparedStatement.setString(3,t.getTruyen().getLoai());
                preparedStatement.setFloat(4,t.getTruyen().getGiaThue());
                preparedStatement.setFloat(5,t.getGianhap());
                preparedStatement.setInt(6,t.getSoluong());
                preparedStatement.setString(7,t.getMota());
                oktruyencuacuahang = preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            // them truyen vao danh sach truyen cua nha cung cap
            String sqlthemtruyenduoccungcap = "INSERT INTO tbltruyenduoccungcap(soluong,gianhap,mota,tid,sid) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlthemtruyenduoccungcap);
            preparedStatement.setInt(1,t.getSoluong());
            preparedStatement.setFloat(2,t.getGianhap());
            preparedStatement.setString(3,t.getMota());
            preparedStatement.setString(4,t.getTruyen().getId());
            preparedStatement.setString(5,t.getNcc().getId());
            oktruyencuanhacungcap = preparedStatement.executeUpdate();
            preparedStatement.close();

            // connection.close();
            return oktruyencuacuahang>0 && oktruyencuanhacungcap>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Truyen getTruyen(String key){
        Truyen truyen = null;
        String sql = "SELECT * FROM tbltruyen WHERE tid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,key);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                truyen = new Truyen(
                    res.getString("tid"),
                    res.getString("ten"), 
                    res.getString("loai"), 
                    res.getFloat("giathue"), 
                    res.getFloat("gianhap"), 
                    res.getInt("soluong"), 
                    res.getString("mota")
                );
            }
            res.close();
            preparedStatement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return truyen;
    }
    public boolean checkTruyenDuocCungCap(String tid, String sid){
        boolean ok = false;
        String sql = "SELECT * FROM tbltruyenduoccungcap WHERE tid = ? AND sid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tid);
            preparedStatement.setString(2,sid);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                ok = true;
            }
            res.close();
            preparedStatement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }
    public boolean updateSoLuongTruyen(ArrayList<TruyenDuocNhap> ds,NCC ncc){
        String sql = "UPDATE tbltruyenduoccungcap "
        +"SET soluong = soluong + ? "
        +"WHERE tid = ? AND sid = ? ";
        try {
            int ok = 0;
            // truyen đuoc cung cap
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(TruyenDuocNhap t : ds){
                preparedStatement.setInt(1, t.getSoluong());
                preparedStatement.setString(2, t.getTruyen().getId());
                preparedStatement.setString(3, ncc.getId());
                ok += preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            // truyen cua ca cua hang
            sql = "UPDATE tbltruyen "
            +"SET soluong = soluong + ? "
            +"WHERE tid = ?";
            preparedStatement = connection.prepareStatement(sql);
            for(TruyenDuocNhap t : ds){
                preparedStatement.setInt(1, t.getSoluong());
                preparedStatement.setString(2, t.getTruyen().getId());
                ok += preparedStatement.executeUpdate();
            }
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
    public void setConnection(Connection connection){
        super.setConnection(connection);
    }
}
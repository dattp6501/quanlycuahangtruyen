import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import controller.HoaDonDAO;
import model.HoaDon;
import model.NCC;
import model.Truyen;
import model.TruyenDuocNhap;
import model.User;

public class HoaDonDAOTest {
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private Connection connection = hoaDonDAO.getConnection();
    @Test
    public void saveHoaDonTest1(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        User u = new User("1", "", "", "", "");
        Truyen truyen = new Truyen("1", "", "", 10000, 150000, 10, "");
        TruyenDuocNhap truyenDuocNhap = new TruyenDuocNhap(truyen, "", 0, 0, 0);
        ArrayList<TruyenDuocNhap> ds = new ArrayList<>();
        ds.add(truyenDuocNhap);

        HoaDon hd = new HoaDon(1, "", u, ncc, new Date(), ds, 0);

        try {
            connection.setAutoCommit(false);
            Assert.assertTrue(hoaDonDAO.saveHoaDon(hd));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(!connection.getAutoCommit()){
                    connection.rollback();
                    connection.setAutoCommit(true); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveHoaDonTest2(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        User u = new User("1", "", "", "", "");
        
        ArrayList<TruyenDuocNhap> ds = new ArrayList<>();
        Truyen truyen1 = new Truyen("1", "", "", 10000, 150000, 10, "");
        TruyenDuocNhap truyenDuocNhap1 = new TruyenDuocNhap(truyen1, "", 0, 0, 0);
        ds.add(truyenDuocNhap1);
        Truyen truyen2 = new Truyen("2", "", "", 10000, 150000, 10, "");
        TruyenDuocNhap truyenDuocNhap2 = new TruyenDuocNhap(truyen2, "", 0, 0, 0);
        ds.add(truyenDuocNhap2);
        HoaDon hd = new HoaDon(1, "", u, ncc, new Date(), ds, 0);

        try {
            connection.setAutoCommit(false);
            Assert.assertTrue(hoaDonDAO.saveHoaDon(hd));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(!connection.getAutoCommit()){
                    connection.rollback();
                    connection.setAutoCommit(true); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

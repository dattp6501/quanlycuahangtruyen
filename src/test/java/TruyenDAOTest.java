import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import controller.TruyenDAO;
import model.NCC;
import model.Truyen;
import model.TruyenDuocCungCap;

public class TruyenDAOTest {
    TruyenDAO truyenDAO = new TruyenDAO();
    Connection connection = truyenDAO.getConnection();
    public TruyenDAOTest() {
    }
    @Test
    public void testTimTruyenStandard1(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        String key = "con";
        ArrayList<TruyenDuocCungCap> ds = truyenDAO.seachTruyen(ncc, key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(1, ds.size());
        for(TruyenDuocCungCap t : ds){
            Assert.assertTrue(t.getTruyen().getTen().toLowerCase().contains(key.toLowerCase()));
        }
    }
    @Test
    public void testTimTruyen1(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        String key = "xxxxxxxxxxxxxxx";
        ArrayList<TruyenDuocCungCap> ds = truyenDAO.seachTruyen(ncc, key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(0, ds.size());
    }
    @Test
    public void testTimTruyen2(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        String key = "abcdef";
        ArrayList<TruyenDuocCungCap> ds = truyenDAO.seachTruyen(ncc, key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(0, ds.size());
    }

    public void testThemTruyen1(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        Truyen truyen = new Truyen("7", "", "", 10000, 150000, 10, "");
        TruyenDuocCungCap truyenDuocCungCap = new TruyenDuocCungCap(ncc, truyen, 10, 150000, "");
        try {
            connection.setAutoCommit(false);
            Assert.assertTrue(truyenDAO.addTruyen(truyenDuocCungCap));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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
    public void testThemTruyen2(){
        NCC ncc = new NCC("1", "", "", "", "", "");
        Truyen truyen = new Truyen("19", "", "", 10000, 150000, 10, "");
        TruyenDuocCungCap truyenDuocCungCap = new TruyenDuocCungCap(ncc, truyen, 10, 150000, "");
        try {
            connection.setAutoCommit(false);
            Assert.assertTrue(truyenDAO.addTruyen(truyenDuocCungCap));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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

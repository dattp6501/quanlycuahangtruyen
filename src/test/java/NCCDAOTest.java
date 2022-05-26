import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import controller.NCCDAO;
import model.NCC;

public class NCCDAOTest{
    private NCCDAO nccdao = new NCCDAO(); 
    private Connection connection = null;
    public NCCDAOTest() {
    }
    @Test
    public void testTimNCCStandard1(){
        String key = "a";
        ArrayList<NCC> ds = nccdao.searchNCC(key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(2, ds.size());
        for(NCC ncc : ds){
            Assert.assertTrue(ncc.getName().toLowerCase().contains(key));
        }
    }
    @Test
    public void testTimNCCStandard2(){
        String key = "h";
        ArrayList<NCC> ds = nccdao.searchNCC(key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(1, ds.size());
        for(NCC ncc : ds){
            Assert.assertTrue(ncc.getName().toLowerCase().contains(key));
        }
    }

    @Test
    public void testTimNCC1(){
        String key = "xxxxxx";
        ArrayList<NCC> ds = nccdao.searchNCC(key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(0,ds.size());
    }
    @Test
    public void testTimNCC2(){
        String key = "abcdefgh";
        ArrayList<NCC> ds = nccdao.searchNCC(key);
        Assert.assertNotNull(ds);
        Assert.assertEquals(0,ds.size());
    }

    @Test
    public void testthemNCC1(){
        NCC ncc = new NCC("7", "test", "Quảng Ninh", "1111111111", "test@gmail.com", "test");
        connection = nccdao.getConnection();
        try {
            connection.setAutoCommit(false);
            Assert.assertTrue(nccdao.addNCC(ncc));// them thanh cong -> true
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

package model;

import java.util.ArrayList;
import java.util.Date;

public class HoaDon {
    private int id;
    private String mota;
    private User u;
    private NCC ncc;
    private Date ngay;
    private ArrayList<TruyenDuocNhap> ds;
    private float tongtien;
    public HoaDon(int id, String mota, User u, NCC ncc, Date ngay, ArrayList<TruyenDuocNhap> ds, float tongtien) {
        this.id = id;
        this.mota = mota;
        this.u = u;
        this.ncc = ncc;
        this.ngay = ngay;
        this.ds = ds;
        this.tongtien = tongtien;
    }
    public HoaDon() {
        super();
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public ArrayList<TruyenDuocNhap> getDs() {
        return ds;
    }
    public void setDs(ArrayList<TruyenDuocNhap> ds) {
        this.ds = ds;
    }
    public float getTongtien() {
        return tongtien;
    }
    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
    public User getUser() {
        return u;
    }
    public void setUser(User u) {
        this.u = u;
    }
    public NCC getNcc() {
        return ncc;
    }
    public void setNcc(NCC ncc) {
        this.ncc = ncc;
    }
    public Date getNgay() {
        return ngay;
    }
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    public int getId() {
        return id;
    }
    public void setId(int i) {
        this.id = i;
    }
    @Override
    public String toString() {
        return "HoaDon [ds=" + ds + ", id=" + id + ", mota=" + mota + ", ncc=" + ncc + ", ngay=" + ngay + ", tongtien="
                + tongtien + ", u=" + u + "]";
    }
}

package model;

public class Truyen{
    private String id,ten,loai,mota;
    private float giaThue,giaNhap;
    private int SL;
    public Truyen(String id, String ten, String loai, float giathue, float gianhap, int sl,String mota) {
        this.id = id;
        this.ten = ten;
        this.loai = loai;
        this.mota = mota;
        this.giaThue = giathue;
        this.giaNhap = gianhap;
        this.SL = sl;
    }
    public Truyen() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getLoai() {
        return loai;
    }
    public void setLoai(String loai) {
        this.loai = loai;
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public float getGiaThue() {
        return giaThue;
    }
    public void setGiaThue(float giaThue) {
        this.giaThue = giaThue;
    }
    public float getGiaNhap() {
        return giaNhap;
    }
    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }
    public int getSL() {
        return SL;
    }
    public void setSL(int sL) {
        SL = sL;
    }
    @Override
    public String toString() {
        return id+", "+ten+", "+loai+", "+giaThue+", "+giaNhap+", "+SL+", "+mota;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Truyen))
            return false;
        Truyen other = (Truyen) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

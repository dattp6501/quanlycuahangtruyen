package model;


public class TruyenDuocCungCap {
    private NCC ncc;
    private Truyen truyen;
    private int soluong;
    private float gianhap;
    private String mota;
    public TruyenDuocCungCap(NCC ncc, Truyen truyen, int soluong, float gianhap, String mota) {
        this.ncc = ncc;
        this.truyen = truyen;
        this.soluong = soluong;
        this.gianhap = gianhap;
        this.mota = mota;
    }
    public TruyenDuocCungCap() {
    }
    public NCC getNcc() {
        return ncc;
    }
    public void setNcc(NCC ncc) {
        this.ncc = ncc;
    }
    public Truyen getTruyen() {
        return truyen;
    }
    public void setTruyen(Truyen truyen) {
        this.truyen = truyen;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public float getGianhap() {
        return gianhap;
    }
    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    @Override
    public String toString() {
        return + gianhap + ", " + mota + ", " + ncc + ", " + soluong+ ", " + truyen;
    }
    
}

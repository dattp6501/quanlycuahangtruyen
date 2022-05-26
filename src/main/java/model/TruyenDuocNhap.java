package model;


public class TruyenDuocNhap {
    private Truyen truyen;
    private String mota;
    private double gianhap,thanhtien;
    private int soluong;
    public TruyenDuocNhap(Truyen truyen, String mota, double gianhap, int soluong, double thanhtien) {
        this.truyen = truyen;
        this.mota = mota;
        this.gianhap = gianhap;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }
    public TruyenDuocNhap() {
    }
    public Truyen getTruyen() {
        return truyen;
    }

    public void setTruyen(Truyen truyen) {
        this.truyen = truyen;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public double getGianhap() {
        return gianhap;
    }

    public void setGianhap(double gianhap) {
        this.gianhap = gianhap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getThanhtien() {
        return thanhtien;
    }
    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    @Override
    public String toString() {
        return "TruyenDuocNhap [gianhap=" + gianhap + ", mota=" + mota + ", soluong=" + soluong + ", truyen=" + truyen
                + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((truyen == null) ? 0 : truyen.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TruyenDuocNhap))
            return false;
        TruyenDuocNhap other = (TruyenDuocNhap) obj;
        if (truyen == null) {
            if (other.truyen != null)
                return false;
        } else if (!truyen.equals(other.truyen))
            return false;
        return true;
    }
}

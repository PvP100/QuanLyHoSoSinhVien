package vn.itplus.quanlysinhvien.model;

public class Nganh {
    private String maNganh, tenNganh;
    private int soLuongHoSo;

    public Nganh(String maNganh, String tenNganh, int soLuongHoSo) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.soLuongHoSo = soLuongHoSo;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public int getSoLuongHoSo() {
        return soLuongHoSo;
    }

    public void setSoLuongHoSo(int soLuongHoSo) {
        this.soLuongHoSo = soLuongHoSo;
    }

    @Override
    public String toString() {
        return this.getTenNganh();
    }
}

package vn.itplus.quanlysinhvien.model;

import java.io.Serializable;

public class SinhVien implements Serializable{
    private String maSV, hoTen, gioiTinh, soDT, CMT, ngayCap, noiCap, ngaySinh, maNganh, nganh, HKTT, danToc, tonGiao, quocTich, noiSinh, doiTuong, ngayVaoDoan, ngayVaoDang;
    private int khoa;

    public SinhVien(String maSV, String hoTen, String gioiTinh, String soDT, String CMT, String ngayCap, String noiCap, String ngaySinh, String maNganh, String nganh, String HKTT, String danToc, String tonGiao, String quocTich, String noiSinh, String doiTuong, String ngayVaoDoan, String ngayVaoDang, int khoa) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.CMT = CMT;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
        this.ngaySinh = ngaySinh;
        this.maNganh = maNganh;
        this.nganh = nganh;
        this.HKTT = HKTT;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.noiSinh = noiSinh;
        this.doiTuong = doiTuong;
        this.ngayVaoDoan = ngayVaoDoan;
        this.ngayVaoDang = ngayVaoDang;
        this.khoa = khoa;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getCMT() {
        return CMT;
    }

    public void setCMT(String CMT) {
        this.CMT = CMT;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getHKTT() {
        return HKTT;
    }

    public void setHKTT(String HKTT) {
        this.HKTT = HKTT;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getNgayVaoDoan() {
        return ngayVaoDoan;
    }

    public void setNgayVaoDoan(String ngayVaoDoan) {
        this.ngayVaoDoan = ngayVaoDoan;
    }

    public String getNgayVaoDang() {
        return ngayVaoDang;
    }

    public void setNgayVaoDang(String ngayVaoDang) {
        this.ngayVaoDang = ngayVaoDang;
    }

    public int getKhoa() {
        return khoa;
    }

    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }
}

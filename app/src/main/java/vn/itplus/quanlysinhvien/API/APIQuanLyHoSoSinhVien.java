package vn.itplus.quanlysinhvien.API;

public class APIQuanLyHoSoSinhVien {
    public static String host = "ung-dung-nghe-nhac.000webhostapp.com";

    public static String addSV = "https://" + host +"/qlyHSSV/addSinhVien.php";
    public static String getUser = "https://" + host +"/qlyHSSV/getUser.php?id=";
    public static String getNganh = "https://" + host + "/qlyHSSV/getNganh.php";
    public static String getLastSV = "https://" + host + "/qlyHSSV/getLastSV.php?maNganh=";
    public static String getSV = "https://" + host + "/qlyHSSV/getSinhVien.php?maSV=";
    public static String editSV = "https://" + host + "/qlyHSSV/editSV.php";
    public static String editNV = "https://" + host + "/qlyHSSV/editNV.php";
    public static String updateMatKhau = "https://" + host + "/qlyHSSV/updateMatKhau.php";
    public static String getNhanVien = "https://" + host + "/qlyHSSV/getNhanVien.php?id=";
    public static String getSvByNganh = "https://" + host + "/qlyHSSV/getSinhVien.php?maNganh=";
    public static String deleteSV = "https://" + host + "/qlyHSSV/deleteSV.php";
}

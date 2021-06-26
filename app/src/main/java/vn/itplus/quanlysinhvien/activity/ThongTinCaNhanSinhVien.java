package vn.itplus.quanlysinhvien.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;
import vn.itplus.quanlysinhvien.model.Nganh;

public class ThongTinCaNhanSinhVien extends AppCompatActivity {

    Button btnSaveThongTinCaNhan;
    String maNganh;
    String maUser;
    Toolbar toolbarDetail;
    EditText edtHoten, edtMaSV, edtGioiTinh, edtNgaySinh, edtNgayVaoDang, edtNgayVaoDoan, edtSDTLienHe, edtKhoa, edtNganh, edtNoiSinh, edtCMTND, edtNgayCap, edtNoiCap, edtHKTT, edtDanToc, edtTonGiao, edtQuocTich, edtDoiTuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan_sinh_vien);


        maUser = getIntent().getStringExtra("maUser");

        mapping();
        initActionBar();
        getDetail();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhanSinhVien.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String monthS = "";
                        String dayS = "";
                        String date = "";
                        if (month < 10) {
                            monthS = "0" + String.valueOf(month);
                        } else {
                            monthS = String.valueOf(month);
                        }

                        if (dayOfMonth < 10){
                            dayS = "0" + String.valueOf(dayOfMonth);
                        } else {
                            dayS = String.valueOf(dayOfMonth);
                        }

                        date = dayS + "/" + monthS + "/" + year;

                        edtNgaySinh.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        edtNgayCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhanSinhVien.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String monthS = "";
                        String dayS = "";
                        String date = "";
                        if (month < 10) {
                            monthS = "0" + String.valueOf(month);
                        } else {
                            monthS = String.valueOf(month);
                        }

                        if (dayOfMonth < 10){
                            dayS = "0" + String.valueOf(dayOfMonth);
                        } else {
                            dayS = String.valueOf(dayOfMonth);
                        }

                        date = dayS + "/" + monthS + "/" + year;
                        edtNgayCap.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        edtNgayVaoDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhanSinhVien.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String monthS = "";
                        String dayS = "";
                        String date = "";
                        if (month < 10) {
                            monthS = "0" + String.valueOf(month);
                        } else {
                            monthS = String.valueOf(month);
                        }

                        if (dayOfMonth < 10){
                            dayS = "0" + String.valueOf(dayOfMonth);
                        } else {
                            dayS = String.valueOf(dayOfMonth);
                        }

                        date = dayS + "/" + monthS + "/" + year;
                        edtNgayVaoDang.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        edtNgayVaoDoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhanSinhVien.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String monthS = "";
                        String dayS = "";
                        String date = "";
                        if (month < 10) {
                            monthS = "0" + String.valueOf(month);
                        } else {
                            monthS = String.valueOf(month);
                        }

                        if (dayOfMonth < 10){
                            dayS = "0" + String.valueOf(dayOfMonth);
                        } else {
                            dayS = String.valueOf(dayOfMonth);
                        }

                        date = dayS + "/" + monthS + "/" + year;
                        edtNgayVaoDoan.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

    }

    public void luuThongTinMoiChinhSua(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.editSV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(ThongTinCaNhanSinhVien.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinCaNhanSinhVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maSV", edtMaSV.getText().toString().trim());
                map.put("hoTen", edtHoten.getText().toString().trim());
                map.put("gioiTinh", edtGioiTinh.getText().toString().trim());
                map.put("soDTLienHe", edtSDTLienHe.getText().toString().trim());
                map.put("CMTND", edtCMTND.getText().toString().trim());
                map.put("ngayCap", edtNgayCap.getText().toString().trim());
                map.put("noiCap", edtNoiCap.getText().toString().trim());
                map.put("ngaySinh", edtNgaySinh.getText().toString().trim());
                map.put("khoa", edtKhoa.getText().toString().trim());
                map.put("maNganhMoi", maNganh);
                map.put("maNganhCu", maNganh);
                map.put("nganh", edtNganh.getText().toString().trim());
                map.put("hoKhauThuongTru", edtHKTT.getText().toString().trim());
                map.put("danToc", edtDanToc.getText().toString().trim());
                map.put("tonGiao", edtTonGiao.getText().toString().trim());
                map.put("quocTich", edtQuocTich.getText().toString().trim());
                map.put("noiSinh", edtNoiSinh.getText().toString().trim());
                map.put("doiTuong", edtDoiTuong.getText().toString().trim());
                map.put("ngayVaoDoanTNCSHCM", edtNgayVaoDoan.getText().toString().trim());
                map.put("ngayVaoDangCSVN", edtNgayVaoDang.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getDetail() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getSV + maUser, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String maSV = jsonObject.getString("maSV");
                    String hoTen = jsonObject.getString("hoTen");
                    String gioiTinh = jsonObject.getString("gioiTinh");
                    String soDTLienHe = jsonObject.getString("soDTLienHe");
                    String CMTND = jsonObject.getString("CMTND");
                    String ngayCap = jsonObject.getString("ngayCap");
                    String noiCap = jsonObject.getString("noiCap");
                    String ngaySinh = jsonObject.getString("ngaySinh");
                    int khoa = jsonObject.getInt("khoa");
                    maNganh = jsonObject.getString("maNganh");
                    String nganh = jsonObject.getString("nganh");
                    String hoKhauTT = jsonObject.getString("hoKhauThuongTru");
                    String danToc = jsonObject.getString("danToc");
                    String tonGiao = jsonObject.getString("tonGiao");
                    String quocTich = jsonObject.getString("quocTich");
                    String noiSinh = jsonObject.getString("noiSinh");
                    String doiTuong = jsonObject.getString("doiTuong");
                    String ngayVaoDoan = jsonObject.getString("ngayVaoDoanTNCSHCM");
                    String ngayVaoDang = jsonObject.getString("ngayVaoDangCSVN");

                    setDetail(maSV, hoTen, gioiTinh, soDTLienHe, CMTND, ngayCap, noiCap, ngaySinh, khoa, maNganh, nganh, hoKhauTT, danToc, tonGiao, quocTich, noiSinh, doiTuong, ngayVaoDoan, ngayVaoDang);
                } catch (Exception e) {
                    Toast.makeText(ThongTinCaNhanSinhVien.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinCaNhanSinhVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void setDetail(String maSV, String hoTen, String gioiTinh, String soDT, String CMTND, String ngayCap, String noiCap, String ngaySinh, int khoa, String maNganh, String nganh, String HKTT, String danToc, String tonGiao, String quocTich, String noiSinh, String doiTuong, String ngayVaoDoan, String ngayVaoDang) {
        edtMaSV.setText(maSV);
        edtMaSV.setEnabled(false);
        edtHoten.setText(hoTen);
        edtHoten.setKeyListener(null);
        edtGioiTinh.setText(gioiTinh);
        edtSDTLienHe.setText(soDT);
        edtCMTND.setText(CMTND);
        edtNgayCap.setText(ngayCap);
        edtNoiCap.setText(noiCap);
        edtNgaySinh.setText(ngaySinh);
        edtKhoa.setText(String.valueOf(khoa));
        edtKhoa.setKeyListener(null);
        edtNganh.setText(nganh);
        edtNganh.setKeyListener(null);
        edtHKTT.setText(HKTT);
        edtDanToc.setText(danToc);
        edtTonGiao.setText(tonGiao);
        edtQuocTich.setText(quocTich);
        edtNoiSinh.setText(noiSinh);
        edtDoiTuong.setText(doiTuong);
        edtDoiTuong.setKeyListener(null);
        edtNgayVaoDoan.setText(ngayVaoDoan);
        edtNgayVaoDang.setText(ngayVaoDang);
    }

    private void initActionBar() {
        setSupportActionBar(toolbarDetail);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanSinhVien.this, DashboardActivity.class);
                intent.putExtra("maUser", maUser);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        edtNganh = findViewById(R.id.edtNganhHoSoSinhVien);
        btnSaveThongTinCaNhan = findViewById(R.id.btnSaveThongTinCaNhan);
        toolbarDetail = findViewById(R.id.toolbar_detail);
        edtMaSV = findViewById(R.id.edtMaSinhVien);
        edtHoten = findViewById(R.id.edtHoTen);
        edtDanToc = findViewById(R.id.edtDanToc);
        edtNgayVaoDang = findViewById(R.id.edtNgayVaoDangCSVN);
        edtNgayVaoDoan = findViewById(R.id.edtNgayVaoDoanTNCSHCM);
        edtDoiTuong = findViewById(R.id.edtDoiTuong);
        edtNoiSinh = findViewById(R.id.edtNoiSinh);
        edtQuocTich = findViewById(R.id.edtQuocTich);
        edtTonGiao = findViewById(R.id.edtTonGiao);
        edtHKTT = findViewById(R.id.edtHoKhauThuongTru);
        edtKhoa = findViewById(R.id.edtKhoa);
        edtCMTND = findViewById(R.id.edtCMTND);
        edtSDTLienHe = findViewById(R.id.edtSĐTLienHe);
        edtNgayCap = findViewById(R.id.edtNgayCap);
        edtNoiCap = findViewById(R.id.edtNoiCap);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
    }
}
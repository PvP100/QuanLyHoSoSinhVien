package vn.itplus.quanlysinhvien.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class ThongTinHoSoSinhVien extends AppCompatActivity {

    Spinner spinner;
    Button btnSaveThongTinCaNhan;
    List<Nganh> listNganh;
    ArrayAdapter<Nganh> adapterSpinner;
    String maSV, maUser;
    Toolbar toolbarDetail;
    String maNganh;
    EditText edtHoten, edtMaSV, edtGioiTinh, edtNgaySinh, edtNgayVaoDang, edtNgayVaoDoan, edtSDTLienHe, edtKhoa, edtNoiSinh, edtCMTND, edtNgayCap, edtNoiCap, edtHKTT, edtDanToc, edtTonGiao, edtQuocTich, edtDoiTuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ho_so_sinh_vien);


        maUser = getIntent().getStringExtra("maUser");
        maSV = getIntent().getStringExtra("maSV");

        mapping();

        initActionBar();
        listNganh = new ArrayList<>();
        getNganh();

        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNganh);
        spinner.setAdapter(adapterSpinner);
        getDetail();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinHoSoSinhVien.this, new DatePickerDialog.OnDateSetListener() {
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
                        ThongTinHoSoSinhVien.this, new DatePickerDialog.OnDateSetListener() {
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
                        ThongTinHoSoSinhVien.this, new DatePickerDialog.OnDateSetListener() {
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
                        ThongTinHoSoSinhVien.this, new DatePickerDialog.OnDateSetListener() {
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

    private void getNganh() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getNganh, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String maNganh = jsonObject.getString("maNganh");
                        String tenNganh = jsonObject.getString("tenNganh");
                        int soLuongHoSo = jsonObject.getInt("soLuongHoSo");

                        listNganh.add(new Nganh(maNganh, tenNganh, soLuongHoSo));
                    } catch(Exception exception) {
                        Toast.makeText(ThongTinHoSoSinhVien.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
                adapterSpinner.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinHoSoSinhVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDetail() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getSV + maSV, null, new Response.Listener<JSONArray>() {
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
                    Toast.makeText(ThongTinHoSoSinhVien.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinHoSoSinhVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void setDetail(String maSV, String hoTen, String gioiTinh, String soDT, String CMTND, String ngayCap, String noiCap, String ngaySinh, int khoa, String maNganh, String nganh, String HKTT, String danToc, String tonGiao, String quocTich, String noiSinh, String doiTuong, String ngayVaoDoan, String ngayVaoDang) {
        edtMaSV.setText(maSV);
        edtMaSV.setEnabled(false);
        edtHoten.setText(hoTen);
        edtGioiTinh.setText(gioiTinh);
        edtSDTLienHe.setText(soDT);
        edtCMTND.setText(CMTND);
        edtNgayCap.setText(ngayCap);
        edtNoiCap.setText(noiCap);
        edtNgaySinh.setText(ngaySinh);
        edtKhoa.setText(String.valueOf(khoa));
        edtHKTT.setText(HKTT);
        edtDanToc.setText(danToc);
        edtTonGiao.setText(tonGiao);
        edtQuocTich.setText(quocTich);
        edtNoiSinh.setText(noiSinh);
        edtDoiTuong.setText(doiTuong);
        edtNgayVaoDoan.setText(ngayVaoDoan);
        edtNgayVaoDang.setText(ngayVaoDang);

        for (int i = 0; i < listNganh.size(); i++) {
            String checkNganh = listNganh.get(i).getMaNganh();
            if (maNganh.equals(checkNganh)) {
                spinner.setSelection(i);
            }
        }
    }

    private void initActionBar() {
        setSupportActionBar(toolbarDetail);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinHoSoSinhVien.this, TimKiemActivity.class);
                intent.putExtra("maUser", maUser);
                intent.putExtra("maNganh", maNganh);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnSaveThongTinCaNhan = findViewById(R.id.btnSaveThongTinHoSo);
        toolbarDetail = findViewById(R.id.toolbarHoSoSinhVien);
        edtMaSV = findViewById(R.id.edtMaSinhVienHoSo);
        edtHoten = findViewById(R.id.edtHoTenHoSo);
        edtDanToc = findViewById(R.id.edtDanTocHoSo);
        edtNgayVaoDang = findViewById(R.id.edtNgayVaoDangCSVNHoSo);
        edtNgayVaoDoan = findViewById(R.id.edtNgayVaoDoanTNCSHCMHoSo);
        edtDoiTuong = findViewById(R.id.edtDoiTuongHoSo);
        edtNoiSinh = findViewById(R.id.edtNoiSinhHoSo);
        edtQuocTich = findViewById(R.id.edtQuocTichHoSo);
        edtTonGiao = findViewById(R.id.edtTonGiaoHoSo);
        edtHKTT = findViewById(R.id.edtHoKhauThuongTruHoSo);
        edtKhoa = findViewById(R.id.edtKhoaHoSo);
        spinner = findViewById(R.id.spinnerNganhHoSoChinhSuaHoSo);
        edtCMTND = findViewById(R.id.edtCMTNDHoSo);
        edtSDTLienHe = findViewById(R.id.edtSĐTLienHeHoSo);
        edtNgayCap = findViewById(R.id.edtNgayCapHoSo);
        edtNoiCap = findViewById(R.id.edtNoiCapHoSo);
        edtGioiTinh = findViewById(R.id.edtGioiTinhHoSo);
        edtNgaySinh = findViewById(R.id.edtNgaySinhHoSo);
    }

    public void luuThongTinMoiChinhSuaHoSo(View view) {
        String nganh = spinner.getSelectedItem().toString();
        String maNganhMoi = "";

        for (int i = 0; i < listNganh.size(); i++) {
            if (nganh.equals(listNganh.get(i).getTenNganh())) {
                maNganhMoi = listNganh.get(i).getMaNganh();
            }
        }
        String check = maNganhMoi;


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.editSV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(ThongTinHoSoSinhVien.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinHoSoSinhVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
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
                map.put("maNganhMoi", check);
                map.put("maNganhCu", maNganh);
                map.put("nganh", nganh);
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
}
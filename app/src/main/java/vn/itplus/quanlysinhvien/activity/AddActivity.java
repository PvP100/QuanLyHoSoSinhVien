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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;
import vn.itplus.quanlysinhvien.model.Nganh;

public class AddActivity extends AppCompatActivity {

    private Spinner spinnerAddNganh;
    private EditText edtKhoaAdd, edtHoTenAdd, edtGioiTinh, edtSDT, edtCMTNDAdd, edtNgayCap, edtNoiCap, edtNgaySinh, edtHKTTAdd, edtDanTocAdd, edtQuocTich, edtNoiSinh, edtDoiTuong;
    private Toolbar toolbarAdd;
    String maUser;
    private Button btnAdd;
    private ArrayAdapter<Nganh> adapterNganh;
    List<Nganh> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        maUser = getIntent().getStringExtra("maUser");

        mapping();

        initActionBar();
        initSpinner();
        getNganh();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    private void initActionBar() {
        setSupportActionBar(toolbarAdd);
        toolbarAdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, DashboardDaoTao.class);
                intent.putExtra("maUser", maUser);
                startActivity(intent);
            }
        });
    }

    private void initSpinner() {
        list = new ArrayList<>();
        adapterNganh = new ArrayAdapter<>(AddActivity.this, android.R.layout.simple_spinner_item, list);
        spinnerAddNganh.setAdapter(adapterNganh);
    }

    public void getNganh() {
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

                        list.add(new Nganh(maNganh, tenNganh, soLuongHoSo));
                    } catch(Exception exception) {
                        Toast.makeText(AddActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
                adapterNganh.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public String getMaNganh(String tenNganh) {
        String ma = "";
        for (int i = 0; i < list.size(); i++) {
            if (tenNganh.equals(list.get(i).getTenNganh())) {
                ma = list.get(i).getMaNganh();
            }
        }

        return ma;
    }

    public String getMaSinhVien(int khoa, String maNganh, int stt) {
        String maSV = khoa + maNganh + stt;
        return maSV;
    }

    public void addSinhVien(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getLastSV + getMaNganh(spinnerAddNganh.getSelectedItem().toString()) + "&khoa=" + edtKhoaAdd.getText().toString().trim(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int newId = 0;
                    if (response.toString().equals("[]")) {
                        newId = 20001;
                    } else {
                        JSONObject jsonObject = response.getJSONObject(0);
                        String id = jsonObject.getString("maSV");
                        newId = Integer.parseInt(id.substring(id.length() - 5));
                        newId++;
                    }

                    String khoa = edtKhoaAdd.getText().toString().trim();
                    String hoTenSV = edtHoTenAdd.getText().toString().trim();
                    String gioiTinh = edtGioiTinh.getText().toString().trim();
                    String SDT = edtSDT.getText().toString().trim();
                    String CMT = edtCMTNDAdd.getText().toString().trim();
                    String ngayCap = edtNgayCap.getText().toString().trim();
                    String noiCap = edtNoiCap.getText().toString().trim();
                    String ngaySinh = edtNgaySinh.getText().toString().trim();
                    String nganh = spinnerAddNganh.getSelectedItem().toString();
                    String maNganh = getMaNganh(nganh);
                    String HKTT = edtHKTTAdd.getText().toString().trim();
                    String danToc = edtDanTocAdd.getText().toString().trim();
                    String quocTich = edtQuocTich.getText().toString().trim();
                    String noiSinh = edtNoiSinh.getText().toString().trim();
                    String doiTuong = edtDoiTuong.getText().toString().trim();

                    if (khoa.matches("") || hoTenSV.matches("") || gioiTinh.matches("") || SDT.matches("") || CMT.matches("") || ngayCap.matches("") || noiCap.matches("") || ngaySinh.matches("") || HKTT.matches("") || danToc.matches("") || quocTich.matches("") || noiSinh.matches("") || doiTuong.matches("")) {
                        Toast.makeText(AddActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        String maSV = getMaSinhVien(Integer.parseInt(khoa), maNganh, newId);
                        addDataToMySQL(maSV, Integer.parseInt(khoa), hoTenSV, gioiTinh, SDT, CMT, ngayCap, noiCap, ngaySinh, nganh, maNganh, HKTT, danToc, quocTich, noiSinh, doiTuong);
                        edtHKTTAdd.setText("");
                        edtHoTenAdd.setText("");
                        edtNgaySinh.setText("");
                        edtDoiTuong.setText("");
                        edtSDT.setText("");
                        edtNoiSinh.setText("");
                        edtQuocTich.setText("");
                        edtDanTocAdd.setText("");
                        edtCMTNDAdd.setText("");
                        edtNgayCap.setText("");
                        edtNoiCap.setText("");
                        edtKhoaAdd.setText("");
                        edtGioiTinh.setText("");

                    }
                } catch (JSONException e) {
                    Toast.makeText(AddActivity.this, "Loi!!!" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);


    }

    public void addDataToMySQL(String maSV, int khoa, String hoTenSV, String gioiTinh, String sdt, String cmt, String ngayCap, String noiCap, String ngaySinh, String nganh, String maNganh, String hktt, String danToc, String quocTich, String noiSinh, String doiTuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.addSV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddActivity.this, "Có lỗi khi add", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maSV", maSV);
                map.put("hoTen", hoTenSV);
                map.put("gioiTinh", gioiTinh);
                map.put("soDTLienHe", sdt);
                map.put("CMTND", cmt);
                map.put("ngayCap", ngayCap);
                map.put("noiCap", noiCap);
                map.put("ngaySinh", ngaySinh);
                map.put("khoa", String.valueOf(khoa));
                map.put("maNganh", maNganh);
                map.put("nganh", nganh);
                map.put("hoKhauThuongTru", hktt);
                map.put("danToc", danToc);
                map.put("tonGiao", "Không");
                map.put("quocTich", quocTich);
                map.put("noiSinh", noiSinh);
                map.put("doiTuong", doiTuong);
                map.put("ngayVaoDoanTNCSHCM", "Không");
                map.put("ngayVaoDangCSVN", "Không");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void mapping() {
        toolbarAdd = findViewById(R.id.toolbarAdd);
        spinnerAddNganh= findViewById(R.id.spinnerAddNganh);
        btnAdd = findViewById(R.id.btn_add);
        edtKhoaAdd = findViewById(R.id.edtKhoaAdd);
        edtHoTenAdd = findViewById(R.id.edtHoTenAdd);
        edtGioiTinh = findViewById(R.id.edtGioiTinhAdd);
        edtCMTNDAdd = findViewById(R.id.edtCMTNDAdd);
        edtDoiTuong = findViewById(R.id.edtDoiTuongAdd);
        edtDanTocAdd = findViewById(R.id.edtDanTocAdd);
        edtNgayCap = findViewById(R.id.edtNgayCapAdd);
        edtNoiCap = findViewById(R.id.edtNoiCapAdd);
        edtNoiSinh = findViewById(R.id.edtNoiSinhAdd);
        edtQuocTich = findViewById(R.id.edtQuocTichAdd);
        edtSDT = findViewById(R.id.edtSDTAdd);
        edtNgaySinh = findViewById(R.id.edtNgaySinhAdd);
        edtHKTTAdd = findViewById(R.id.edtHKTTAdd);
    }
}
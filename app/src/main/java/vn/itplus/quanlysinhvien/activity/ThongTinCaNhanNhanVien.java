package vn.itplus.quanlysinhvien.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;

public class ThongTinCaNhanNhanVien extends AppCompatActivity {

    EditText edtHoTenNhanVien, edtMaNV, edtNgaySinh, edtSDT, edtGioiTinh, edtEmail;
    ImageView imgDoiMatKhauNhanVien;
    Toolbar toolbarNV;
    String maNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan_nhan_vien);

        maNhanVien = getIntent().getStringExtra("maUser");

        mapping();
        inintActionBar();
        getNhanVien(maNhanVien);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhanNhanVien.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    private void inintActionBar() {
        setSupportActionBar(toolbarNV);
        toolbarNV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanNhanVien.this, DashboardDaoTao.class);
                intent.putExtra("maUser", maNhanVien);
                startActivity(intent);
            }
        });
    }

    public void doiMatKhauNhanVien(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getUser + maNhanVien, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String matKhauNhanVien = jsonObject.getString("matKhau");
                    String chucVu = jsonObject.getString("chucVu");

                    Intent intent = new Intent(ThongTinCaNhanNhanVien.this, DoiMatKhauActivity.class);
                    intent.putExtra("maUser", maNhanVien);
                    intent.putExtra("matKhau", matKhauNhanVien);
                    intent.putExtra("chucVu", chucVu);
                    startActivity(intent);
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);

    }

    private void getNhanVien(String maNV) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getNhanVien + maNV, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String maNVCheck = jsonObject.getString("maNV");
                    String hoTen = jsonObject.getString("hoTen");
                    String gioiTinh = jsonObject.getString("gioiTinh");
                    String soDT = jsonObject.getString("soDT");
                    String email = jsonObject.getString("email");
                    String ngaySinh = jsonObject.getString("ngaySinh");

                    setDetail(maNVCheck, hoTen, gioiTinh, soDT, email, ngaySinh);
                } catch (Exception e) {
                    Toast.makeText(ThongTinCaNhanNhanVien.this, ""  + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void setDetail(String maNV, String hoTen, String gioiTinh, String soDT, String email, String ngaySinh) {
        edtSDT.setText(soDT);
        edtMaNV.setText(maNV);
        edtMaNV.setEnabled(false);
        edtHoTenNhanVien.setText(hoTen);
        edtNgaySinh.setText(ngaySinh);
        edtEmail.setText(email);
        edtGioiTinh.setText(gioiTinh);
    }

    private void mapping() {
        toolbarNV = findViewById(R.id.toolbarThongTinNhanVien);
        imgDoiMatKhauNhanVien = findViewById(R.id.imgDoiMatKhauNhanVien);
        edtEmail = findViewById(R.id.edtEmailNhanVien);
        edtGioiTinh = findViewById(R.id.edtGioiTinhNhanVien);
        edtHoTenNhanVien = findViewById(R.id.edtHoTenNhanVien);
        edtMaNV = findViewById(R.id.edtMaNhanVien);
        edtNgaySinh = findViewById(R.id.edtNgaySinhNhanVien);
        edtSDT = findViewById(R.id.edtSDTLienHeNhanVien);
    }

    public void luuThongTinNhanVien(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.editNV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(ThongTinCaNhanNhanVien.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinCaNhanNhanVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maNV", edtMaNV.getText().toString().trim());
                map.put("hoTen", edtHoTenNhanVien.getText().toString().trim());
                map.put("gioiTinh", edtGioiTinh.getText().toString().trim());
                map.put("soDT", edtSDT.getText().toString().trim());
                map.put("email", edtEmail.getText().toString().trim());
                map.put("ngaySinh", edtNgaySinh.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
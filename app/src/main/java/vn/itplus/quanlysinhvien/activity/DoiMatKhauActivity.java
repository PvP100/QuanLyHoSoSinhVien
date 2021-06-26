package vn.itplus.quanlysinhvien.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;

public class DoiMatKhauActivity extends AppCompatActivity {

    Toolbar toolbarDoiMatKhau;
    EditText edtMatKhauCu, edtMatKhauMoi, edtNhapLaiMatKhauMoi;
    Button btnDoiMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        mapping();
        initActionBar();

    }

    private void mapping() {
        toolbarDoiMatKhau = findViewById(R.id.toolbar_doi_mat_khau);
        edtMatKhauCu = findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi = findViewById(R.id.edtMatKhauMoi);
        edtNhapLaiMatKhauMoi = findViewById(R.id.edtNhapLaiMatKhauMoi);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
    }

    private void initActionBar() {
        setSupportActionBar(toolbarDoiMatKhau);
        toolbarDoiMatKhau.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void luuMatKhauMoi(View view) {
        String matKhauCu = edtMatKhauCu.getText().toString().trim();
        String matKhauMoi = edtMatKhauMoi.getText().toString().trim();
        String nhapLaiMatKhauMoi = edtNhapLaiMatKhauMoi.getText().toString().trim();
        String maUser = getIntent().getStringExtra("maUser");
        String check = getIntent().getStringExtra("matKhau");
        String chucVu = getIntent().getStringExtra("chucVu");
        
        if (matKhauCu.equals(check) && matKhauMoi.equals(nhapLaiMatKhauMoi) && !matKhauMoi.matches("")) {
            doiMatKhau(edtMatKhauMoi.getText().toString().trim(), maUser);
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            if (chucVu.equals("Sinh viên")) {
                Intent intent = new Intent(DoiMatKhauActivity.this, DashboardActivity.class);
                intent.putExtra("maUser", maUser);
                intent.putExtra("matKhau", matKhauMoi);
                intent.putExtra("chucVu", chucVu);
                startActivity(intent);
            } else {
                Intent intent = new Intent(DoiMatKhauActivity.this, DashboardDaoTao.class);
                intent.putExtra("maUser", maUser);
                intent.putExtra("matKhau", matKhauMoi);
                intent.putExtra("chucVu", chucVu);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập lại đúng yêu cầu", Toast.LENGTH_SHORT).show();
        }
    }

    private void doiMatKhau(String matKhauMoi, String maUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.updateMatKhau, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoiMatKhauActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maUser", maUser);
                map.put("matKhau", matKhauMoi);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
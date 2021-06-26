package vn.itplus.quanlysinhvien.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;

public class DashboardActivity extends AppCompatActivity {

    ImageView imgSearch, imgAdd;
    String maNguoiDung;
    Toolbar toolbarDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        maNguoiDung =  getIntent().getStringExtra("maUser");

        mapping();
        initActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navi_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mapping() {
        imgSearch = findViewById(R.id.imgThongTinCaNhan);
        imgAdd = findViewById(R.id.imgDoiMatKhau);
        toolbarDashboard = findViewById(R.id.toolbarDashboard);
    }

    private void initActionBar() {
        setSupportActionBar(toolbarDashboard);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void thongTinCaNhan(View view) {
        Intent intent = new Intent(DashboardActivity.this, ThongTinCaNhanSinhVien.class);
        intent.putExtra("maUser", maNguoiDung);
        startActivity(intent);
    }

    public void doiMatKhauSinhVien(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getUser + maNguoiDung, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String matKhauSinhVien = jsonObject.getString("matKhau");
                    String chucVu = jsonObject.getString("chucVu");

                    Intent intent = new Intent(DashboardActivity.this, DoiMatKhauActivity.class);
                    intent.putExtra("maUser", maNguoiDung);
                    intent.putExtra("matKhau", matKhauSinhVien);
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
}
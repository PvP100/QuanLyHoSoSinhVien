package vn.itplus.quanlysinhvien.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;
import vn.itplus.quanlysinhvien.adapter.SinhVienAdapter;
import vn.itplus.quanlysinhvien.model.SinhVien;

public class TimKiemActivity extends AppCompatActivity {

    ListView lvSinhVien;
    List<SinhVien> sinhVienList;
    Toolbar toolbarTimKiem;
    String maUser;
    SinhVienAdapter sinhVienAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        String maNganh = getIntent().getStringExtra("maNganh");
        maUser = getIntent().getStringExtra("maUser");

        mapping();
        initActionBar();
        init();
        getAllSinhVien(maNganh);

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TimKiemActivity.this, ThongTinHoSoSinhVien.class);
                intent.putExtra("maUser", maUser);
                intent.putExtra("maSV", sinhVienList.get(position).getMaSV());
                startActivity(intent);
            }
        });
    }

    private void init() {
        sinhVienList = new ArrayList<>();
        sinhVienAdapter = new SinhVienAdapter(this, R.layout.line_sinh_vien, sinhVienList);
        lvSinhVien.setAdapter(sinhVienAdapter);
    }

    private void initActionBar() {
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTimKiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimKiemActivity.this, NganhActivity.class);
                intent.putExtra("maUser", maUser);
                startActivity(intent);
            }
        });
    }

    private void getAllSinhVien(String maNganh) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getSvByNganh + maNganh, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String maSV = jsonObject.getString("maSV");
                        String hoTen = jsonObject.getString("hoTen");
                        String gioiTinh = jsonObject.getString("gioiTinh");
                        String soDTLhe = jsonObject.getString("soDTLienHe");
                        String CMTND = jsonObject.getString("CMTND");
                        String ngayCap = jsonObject.getString("ngayCap");
                        String noiCap = jsonObject.getString("noiCap");
                        String ngaySinh = jsonObject.getString("ngaySinh");
                        int khoa = jsonObject.getInt("khoa");
                        String maNganhSV = jsonObject.getString("maNganh");
                        String nganh = jsonObject.getString("nganh");
                        String HKTT = jsonObject.getString("hoKhauThuongTru");
                        String danToc = jsonObject.getString("danToc");
                        String tonGiao = jsonObject.getString("tonGiao");
                        String quocTich = jsonObject.getString("quocTich");
                        String noiSinh = jsonObject.getString("noiSinh");
                        String doiTuong = jsonObject.getString("doiTuong");
                        String ngayVaoDoan = jsonObject.getString("ngayVaoDoanTNCSHCM");
                        String ngayVaoDang = jsonObject.getString("ngayVaoDangCSVN");
                        sinhVienList.add(new SinhVien(maSV, hoTen, gioiTinh, soDTLhe, CMTND, ngayCap, noiCap, ngaySinh, maNganhSV, nganh, HKTT, danToc, tonGiao, quocTich, noiSinh, doiTuong, ngayVaoDoan, ngayVaoDang, khoa));
                    } catch (Exception e) {
                        Toast.makeText(TimKiemActivity.this, "Lỗi hiển thị", Toast.LENGTH_SHORT).show();
                    }
                }
                sinhVienAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TimKiemActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_tim_kiem, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.navTimKiem).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sinhVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sinhVienAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navTimKiem:
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mapping() {
        toolbarTimKiem = findViewById(R.id.toolbar_tim_kiem);
        lvSinhVien = findViewById(R.id.lvTimKiem);
    }
}
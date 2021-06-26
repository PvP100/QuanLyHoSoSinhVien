package vn.itplus.quanlysinhvien.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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
import vn.itplus.quanlysinhvien.adapter.NganhAdapter;
import vn.itplus.quanlysinhvien.model.Nganh;

public class NganhActivity extends AppCompatActivity {

    Toolbar toolbarTimKiem;
    ListView listViewTimKiem;
    List<Nganh> listNganh;
    NganhAdapter nganhAdapter;
    String maUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nganh);

        maUser = getIntent().getStringExtra("maUser");

        mapping();
        initActionBar();
        init();
        getNganh();

        listViewTimKiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NganhActivity.this, TimKiemActivity.class);
                String maNganh = listNganh.get(position).getMaNganh();
                intent.putExtra("maUser", maUser);
                intent.putExtra("maNganh", maNganh);
                startActivity(intent);
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
                        Toast.makeText(NganhActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
                nganhAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NganhActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void init() {
        listNganh = new ArrayList<>();
        nganhAdapter = new NganhAdapter(listNganh, NganhActivity.this);
        listViewTimKiem.setAdapter(nganhAdapter);
    }

    private void initActionBar() {
        setSupportActionBar(toolbarTimKiem);
        toolbarTimKiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NganhActivity.this, DashboardDaoTao.class);
                intent.putExtra("maUser", maUser);
                startActivity(intent);
            }
        });
    }



    private void mapping() {
        toolbarTimKiem = findViewById(R.id.toolbarTimKiem);
        listViewTimKiem = findViewById(R.id.lvNganhDaoTao);
    }
}
package vn.itplus.quanlysinhvien.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
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
import vn.itplus.quanlysinhvien.adapter.ThongKeAdapter;
import vn.itplus.quanlysinhvien.model.Nganh;

public class ThongKeActivity extends AppCompatActivity {

    ListView lvThongKe;
    List<Nganh> list;
    Toolbar toolbarThongKe;
    ThongKeAdapter thongKeAdapter;
    TextView txtvTongHoSo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        String maUser = getIntent().getStringExtra("maUser");

        mapping();
        initActionBar(maUser);
        init();
        getNganh();
    }

    private void initActionBar(String maUser) {
        setSupportActionBar(toolbarThongKe);
        toolbarThongKe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongKeActivity.this, DashboardDaoTao.class);
                intent.putExtra("maUser", maUser);
                startActivity(intent);
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        thongKeAdapter = new ThongKeAdapter(list, ThongKeActivity.this);
        lvThongKe.setAdapter(thongKeAdapter);
    }

    public void getNganh() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getNganh, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int tongHoSo = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String maNganh = jsonObject.getString("maNganh");
                        String tenNganh = jsonObject.getString("tenNganh");
                        int soLuongHoSo = jsonObject.getInt("soLuongHoSo");

                        list.add(new Nganh(maNganh, tenNganh, soLuongHoSo));
                        tongHoSo+= soLuongHoSo;
                        thongKeAdapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }

                txtvTongHoSo.setText(String.valueOf(tongHoSo));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void mapping() {
        txtvTongHoSo = findViewById(R.id.txtvTongHoSo);
        toolbarThongKe = findViewById(R.id.toolbarThongKe);
        lvThongKe = findViewById(R.id.lvThongKeHoSo);
    }
}
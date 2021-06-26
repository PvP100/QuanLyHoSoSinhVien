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
import android.widget.Toast;

import vn.itplus.quanlysinhvien.R;

public class DashboardDaoTao extends AppCompatActivity {

    public String maUser;
    Toolbar toolbarDashboardDaoTao;
    String maUserDaoTao;
    ImageView imgTimKiem, imgThongKe, imgThemHoSo, imgThongTinCaNhanDaoTao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_dao_tao);

        maUserDaoTao = getIntent().getStringExtra("maUser");

        mapping();
        initActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navi_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                finish();
                startActivity(new Intent(this, LoginActivity.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initActionBar() {
        setSupportActionBar(toolbarDashboardDaoTao);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void mapping() {
        toolbarDashboardDaoTao = findViewById(R.id.toolbarDashboardDaoTao);
        imgThemHoSo = findViewById(R.id.imgThemHoSo);
        imgThongKe = findViewById(R.id.imgThongKeDaoTao);
        imgThongTinCaNhanDaoTao = findViewById(R.id.imgThongTinCaNhanDaoTao);
        imgTimKiem = findViewById(R.id.imgTimKiemDaoTao);
    }

    public void timKiemHoSo(View view) {
        Intent intent = new Intent(DashboardDaoTao.this, NganhActivity.class);
        intent.putExtra("maUser", maUserDaoTao);
        startActivity(intent);
    }

    public void themHoSoSinhVien(View view) {
        Intent intent = new Intent(DashboardDaoTao.this, AddActivity.class);
        intent.putExtra("maUser", maUserDaoTao);
        startActivity(intent);
    }

    public void thongTinNhanVien(View view) {
        Intent intent = new Intent(DashboardDaoTao.this, ThongTinCaNhanNhanVien.class);
        intent.putExtra("maUser", maUserDaoTao);
        startActivity(intent);
    }

    public void thongKeHoSo(View view) {
        Intent intent = new Intent(DashboardDaoTao.this, ThongKeActivity.class);
        intent.putExtra("maUser", maUserDaoTao);
        startActivity(intent);
    }
}
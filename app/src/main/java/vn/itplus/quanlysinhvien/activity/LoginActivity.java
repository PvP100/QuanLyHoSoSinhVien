package vn.itplus.quanlysinhvien.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtname, edtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(edtname.getText());
                String password = String.valueOf(edtpass.getText());

                getUser(username, password);

            }
        });
    }

    private void getUser(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIQuanLyHoSoSinhVien.getUser + username + "&pass=" + password, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        String username = jsonObject.getString("maUser");
                        String chucVu = jsonObject.getString("chucVu");

                        if (chucVu.equals("Sinh viên")) {
                            edtpass.setText("");
                            Toast.makeText(LoginActivity.this, "Login Ok", Toast.LENGTH_SHORT).show();
                            Intent intentSinhVien = new Intent(LoginActivity.this, DashboardActivity.class);
                            intentSinhVien.putExtra("maUser", username);
                            startActivity(intentSinhVien);
                        } else {
                            edtpass.setText("");
                            Toast.makeText(LoginActivity.this, "Login Ok", Toast.LENGTH_SHORT).show();
                            Intent intentDaoTao = new Intent(LoginActivity.this, DashboardDaoTao.class);
                            intentDaoTao.putExtra("maUser", username);
                            startActivity(intentDaoTao);
                        }
                    } catch (Exception e) {
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(LoginActivity.this);
                        dlgAlert.setMessage("username or password are invalid");
                        dlgAlert.setTitle("Error Message...");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void mapping() {
        btnLogin = findViewById(R.id.btnLogin);
        edtname = findViewById(R.id.edtname);
        edtpass = findViewById(R.id.edtpass);
    }
}
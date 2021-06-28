package vn.itplus.quanlysinhvien.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.itplus.quanlysinhvien.API.APIQuanLyHoSoSinhVien;
import vn.itplus.quanlysinhvien.R;
import vn.itplus.quanlysinhvien.model.SinhVien;

public class SinhVienAdapter extends BaseAdapter implements Filterable {

    Context context;
    int layout;
    List<SinhVien> sinhVienList;
    List<SinhVien> sinhVienListFiltered;

    public SinhVienAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
        this.sinhVienListFiltered = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    sinhVienList = sinhVienListFiltered;
                } else {
                    List<SinhVien> list = new ArrayList<>();
                    for (SinhVien sv : sinhVienListFiltered) {
                        if (sv.getHoTen().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(sv);
                        }
                    }

                    sinhVienList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = sinhVienList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                sinhVienList = (List<SinhVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    static class ViewHolder {
        TextView txtvHoTen, txtvMaSV;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtvHoTen = convertView.findViewById(R.id.txtvTenSVTimKiem);
            viewHolder.txtvMaSV = convertView.findViewById(R.id.txtvMaSVTimKiem);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDeleteTimKiem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = sinhVienList.get(position);

        viewHolder.txtvHoTen.setText(sinhVien.getHoTen());
        viewHolder.txtvMaSV.setText(sinhVien.getMaSV());

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClick(position);
            }
        });
        return convertView;
    }

    public void dialogClick(int id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Confirm");
        alert.setMessage("Bạn có muốn xóa không?");
        alert.setIcon(R.drawable.utt);

        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData(sinhVienList.get(id).getMaSV(), sinhVienList.get(id).getMaNganh());
                sinhVienList.remove(id);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    public void deleteData(String maSV, String maNganh) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIQuanLyHoSoSinhVien.deleteSV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Lỗi" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maSV", maSV);
                map.put("maNganh", maNganh);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}

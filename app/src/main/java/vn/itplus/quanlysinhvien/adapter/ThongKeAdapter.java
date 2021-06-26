package vn.itplus.quanlysinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.itplus.quanlysinhvien.R;
import vn.itplus.quanlysinhvien.model.Nganh;

public class ThongKeAdapter extends BaseAdapter {

    List<Nganh> list;
    Context mContext;

    public ThongKeAdapter(List<Nganh> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView txtvTenNganh;
        TextView txtvSoLuongHoSo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Nganh nganh = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.line_thong_ke, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtvSoLuongHoSo = convertView.findViewById(R.id.txtvsoLuongHoSoNganh);
            viewHolder.txtvTenNganh = convertView.findViewById(R.id.txtvTenNganhThongke);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtvTenNganh.setText(nganh.getTenNganh());
        viewHolder.txtvSoLuongHoSo.setText(String.valueOf(nganh.getSoLuongHoSo()));

        return convertView;
    }
}

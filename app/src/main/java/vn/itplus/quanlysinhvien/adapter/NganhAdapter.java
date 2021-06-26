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

public class NganhAdapter extends BaseAdapter {

    List<Nganh> listNganh;
    Context mContext;

    public NganhAdapter(List<Nganh> listNganh, Context mContext) {
        this.listNganh = listNganh;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listNganh.size();
    }

    @Override
    public Object getItem(int position) {
        return listNganh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtvNganh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Nganh nganh = (Nganh) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.line_nganh, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtvNganh = convertView.findViewById(R.id.txtvTenNganh);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtvNganh.setText(nganh.getTenNganh());

        return convertView;
    }
}

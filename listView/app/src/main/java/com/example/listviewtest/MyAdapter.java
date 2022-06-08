package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<CustomDTO> listCustom = new ArrayList<>();

    @Override
    public int getCount() {
        return listCustom.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listitem,
                    null, false);

            holder = new CustomViewHolder();
            holder.textTitle = (TextView) convertView.findViewById(R.id.countryName);
            holder.textContent = (TextView) convertView.findViewById(R.id.regionCount);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);
        holder.textTitle.setText(dto.getTitle());
        holder.textContent.setText(dto.getContent());

        return convertView;

    }

    class CustomViewHolder {
        TextView textTitle;
        TextView textContent;
    }

    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }

}

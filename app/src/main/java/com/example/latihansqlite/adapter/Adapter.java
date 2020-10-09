package com.example.latihansqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.latihansqlite.R;
import com.example.latihansqlite.model.Contact;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Contact> items;

    public Adapter(Activity activity, List<Contact> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_data_row, null);

        TextView id = (TextView) convertView.findViewById(R.id.tv_row_id);
        TextView name = (TextView) convertView.findViewById(R.id.tv_row_name);
        TextView phone_number = (TextView) convertView.findViewById(R.id.tv_row_phone_no);

        Contact contact = items.get(position);

        id.setText(contact.get_id());
        name.setText(contact.get_name());
        phone_number.setText(contact.get_phone_number());

        return convertView;
    }
}

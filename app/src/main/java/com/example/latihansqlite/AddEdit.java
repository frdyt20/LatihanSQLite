package com.example.latihansqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.latihansqlite.helper.SQLiteDBHandler;

public class AddEdit extends AppCompatActivity {
    EditText et_txt_id, et_txt_name, et_txt_phone;
    Button btn_submit, btn_cancel;
    SQLiteDBHandler db = new SQLiteDBHandler(this);
    String id, name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

package com.example.latihansqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.latihansqlite.helper.SQLiteDBHandler;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_phone;
    Button submit, cancel;
    SQLiteDBHandler db = new SQLiteDBHandler(this);
    String id, name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.et_txt_id);
        txt_name = findViewById(R.id.et_txt_name);
        txt_phone = findViewById(R.id.et_txt_phone);
        submit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        phone = getIntent().getStringExtra(MainActivity.TAG_PH_NO);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_phone.setText(phone);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e ("Submit", e.toString());
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_phone.setText(null);
    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                    String.valueOf(txt_phone.getText().equals(null)) || String.valueOf(txt_phone.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
            "Please input name or phone number ...", Toast.LENGTH_LONG).show();
        } else {
            db.addContact(txt_name.getText().toString().trim(),
                    txt_phone.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_phone.getText().equals(null)) || String.valueOf(txt_phone.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or phone number ...", Toast.LENGTH_LONG).show();
        } else {
            db.updateContact()(txt_name.getText().toString().trim(),
                    txt_phone.getText().toString().trim());
            blank();
            finish();
        }
    }
}
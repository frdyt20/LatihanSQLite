package com.example.latihansqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.latihansqlite.helper.SQLiteDBHandler;
import com.example.latihansqlite.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
    ListView listView;
    AlertDialog.Builder dialog;
    List<Contact> itemList = new ArrayList<Contact>();
    Adapter adapter;
    SQLiteDBHandler db = new SQLiteDBHandler(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PH_NO = "phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDBHandler db = new SQLiteDBHandler(this);

        FloatingActionButton fab = findViewById(R.id.btn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, AddEdit.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(MainActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(
                    final AdapterView<?> parent, View view, final int position, long id) {

                final String idx = itemList.get(position).get_id();
                final String name = itemList.get(position).get_name();
                final String phone_number = itemList.get(position).get_phone_number();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddEdit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_PH_NO, phone_number);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deleteContact(Integer.parseInt(idx));
                                itemList.clear();
                                getAllContacts();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllContacts();
    }

    private void getAllContacts() {
        List<Contact> row = SQLiteDBHandler.getAllContacts();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get_id();
            String namex = row.get(i).get_name();
            String phonenumber = row.get(i).get_phone_number();

            Contact contact = new Contact();

            contact.set_id(id);
            contact.set_name(namex);
            contact.set_phone_number(phonenumber);

            itemList.add(contact);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllContacts();
    }
}

//        recyclerView = findViewById(R.id.rv_data);

//    private void showRecyclerView() {
//        recyclerView.layout();
//    }
//}



//        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//
//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();
//
//        for (Contact cn : contacts) {
//            String log = "Id: " + cn.get_id() + " ,Name: " + cn.get_name() + " ,Phone: " +
//                    cn.get_phone_number();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }
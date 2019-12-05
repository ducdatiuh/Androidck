package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList = new ArrayList();
    TextView txtid;
    EditText edtname, edtclass, edtsubject;
    Button btnthem, btnsua, btnxoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvlop);
        txtid = findViewById(R.id.txtid);
        edtname = findViewById(R.id.edtname);
        edtclass = findViewById(R.id.edtclass);
        edtsubject = findViewById(R.id.edtsubject);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        loadConten();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sinhVien = (SinhVien) arrayList.get(i);
                txtid.setText(String.valueOf(sinhVien.getId()));
                edtname.setText(sinhVien.getName());
                edtclass.setText(sinhVien.getClass_name());
                edtsubject.setText(sinhVien.getSubject());
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xoa sinh vien");
                builder.setMessage("Xac nhan xoa");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SinhVien sinhVien = new SinhVien(Integer.parseInt(txtid.getText().toString()), edtname.getText().toString(), edtclass.getText().toString(), edtsubject.getText().toString());
                        delete(sinhVien);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
//                AlertDialog dialog = builder.create();
//                dialog.show();
                return false;
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = new SinhVien(Integer.parseInt(txtid.getText().toString()), edtname.getText().toString(), edtclass.getText().toString(), edtsubject.getText().toString());
                insert(sinhVien);
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = new SinhVien(Integer.parseInt(txtid.getText().toString()), edtname.getText().toString(), edtclass.getText().toString(), edtsubject.getText().toString());
                update(sinhVien);
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = new SinhVien(Integer.parseInt(txtid.getText().toString()), edtname.getText().toString(), edtclass.getText().toString(), edtsubject.getText().toString());
                delete(sinhVien);
            }
        });

    }
    public void insert(SinhVien sinhVien){
        ContentResolver  resolver  = getContentResolver();
        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien?name="+sinhVien.getName()+"&class="+sinhVien.getClass_name()+"&subject="+sinhVien.getSubject()+"#2");
        resolver.query(uri,null, null, null, null);
        loadConten();
    }
    public void update(SinhVien sinhVien){
        ContentResolver  resolver  = getContentResolver();
        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien?id="+sinhVien.getId()+"&name="+sinhVien.getName()+"&class="+sinhVien.getClass_name()+"&subject="+sinhVien.getSubject()+"#3");
        resolver.query(uri,null, null, null, null);
        loadConten();

    }
    public void delete(SinhVien sinhVien){
        ContentResolver  resolver  = getContentResolver();
        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien?id="+sinhVien.getId()+"#4");
        resolver.query(uri,null, null, null, null);
        loadConten();

    }
    public void loadConten(){
        ContentResolver  resolver  = getContentResolver();
//        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien/#1");
        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien?name=AnhTu&tuoi=22#1");
//        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien?id=123#2");
        Cursor cursor = resolver.query(uri,null, null, null, null);
        Toast.makeText(this, cursor.getCount()+"", Toast.LENGTH_SHORT).show();

        arrayList.clear();
        while (cursor.moveToNext()){
            SinhVien sinhVien = new SinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList.add(sinhVien);
        }
        Collections.reverse(arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

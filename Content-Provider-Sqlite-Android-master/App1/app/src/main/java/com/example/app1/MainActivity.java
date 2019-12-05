package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
//    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList = new ArrayList();
    SinhVienManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SinhVienManager(this, "SVDB.sqlite", null, 1);
        db.action("create table if not exists SinhVien(id integer primary key autoincrement, name text, class_name text, subject text)");
        db.action("insert into SinhVien values(null, 'Trinh Duc Dat', 'KTPM12A', 'mssv: 16043051')");

        Cursor cs = db.select("select * from SinhVien");
        Toast.makeText(this, "" + cs.getCount(), Toast.LENGTH_SHORT).show();
        // app 1 không cần giao diện cũng
        // chú ý file AndroidManifest.xml cả 2 app
//        listView =(ListView) findViewById(R.id.lvlop);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        loadDB();

//        ContentResolver  resolver  = getContentResolver();
//        Uri uri = Uri.parse("content://com.example.app1.SV/SinhVien");
//        Cursor cursor = resolver.query(uri,null, null, null, null);
//        Toast.makeText(this, "<<"+cursor.getCount()+">>", Toast.LENGTH_SHORT).show();
    }

//    public void loadDB(){
//        arrayList.clear();
//        Cursor cs = db.select("select * from SinhVien");
//        while (cs.moveToNext()){
//            SinhVien sinhVien = new SinhVien(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3));
//            arrayList.add(sinhVien);
//        }
//        Collections.reverse(arrayList);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
////        Toast.makeText(this, arrayList.size() + "ok", Toast.LENGTH_SHORT).show();
//    }
}

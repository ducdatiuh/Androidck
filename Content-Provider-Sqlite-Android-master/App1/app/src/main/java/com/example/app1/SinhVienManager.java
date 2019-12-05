package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SinhVienManager extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    public SinhVienManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void action(String sql) {
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor select(String sql) {
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public int delete(String id) {
        return sqLiteDatabase.delete("SinhVien", "id=" + id, null);
    }

    public long insert(SinhVien sinhVien) {
        System.out.println(sinhVien);
        ContentValues values = new ContentValues();
        values.put("name", sinhVien.getName());
        values.put("class_name", sinhVien.getClass_name());
        values.put("subject", sinhVien.getSubject());
        return sqLiteDatabase.insert("SinhVien", null, values);
    }

    public int update(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("name", sinhVien.getName());
        values.put("class_name", sinhVien.getClass_name());
        values.put("subject", sinhVien.getSubject());
        return sqLiteDatabase.update("SinhVien", values, "id=" + sinhVien.getId(), null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
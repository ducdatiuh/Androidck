package com.example.app1;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ProviderManager extends ContentProvider {
    public static UriMatcher URI_MATCHER;
    SinhVienManager manager;

    @Override
    public boolean onCreate() {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI("com.example.app1.SV", "SinhVien", 1);
        manager = new SinhVienManager(getContext(), "SVDB.sqlite", null, 1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        System.out.println(uri.getPath());

        System.out.println("id -> " + uri.getQueryParameter("id"));
        System.out.println("name -> " + uri.getQueryParameter("name"));
        System.out.println("class -> " + uri.getQueryParameter("class"));
        System.out.println("subject -> " + uri.getQueryParameter("subject"));
        System.out.println("# ->" + uri.getEncodedFragment());
        switch (Integer.parseInt(uri.getEncodedFragment())) {
            case 1: // select
                Cursor cursor = manager.select("select * from SinhVien");
                System.out.println(cursor.getCount());
                return cursor;
            case 2: // insert
                manager.insert(new SinhVien(uri.getQueryParameter("name"), uri.getQueryParameter("class"), uri.getQueryParameter("subject")));
                return  null;
            case 3: // update
                manager.update(new SinhVien(Integer.parseInt(uri.getQueryParameter("id")), uri.getQueryParameter("name"), uri.getQueryParameter("class"), uri.getQueryParameter("subject")));
                return null;
            case 4: // delete
                manager.delete(uri.getQueryParameter("id"));
                return null;

        }
        Cursor cursor = manager.select("select * from SinhVien");
        System.out.println(cursor.getCount());
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

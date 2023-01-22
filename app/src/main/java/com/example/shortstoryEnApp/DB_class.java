package com.example.shortstoryEnApp;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DB_class extends SQLiteAssetHelper {
    public static final String DB_name="ShortStoriesDB.db";
    public static final String TABLE_ALL_NAME="table_all";
    public static final String TABLE_FABORITE="table_faborite";

    public static final String ID="id";
    public static final String TITLE="titel";
    public static final String STORY_TEXT="story_text";

    public static final int VERSION_1=1;


    public DB_class(  Context context ) {

        super(context, DB_name,null, VERSION_1);
    }
}

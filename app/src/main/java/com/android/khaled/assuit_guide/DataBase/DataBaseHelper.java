package com.android.khaled.assuit_guide.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "AssuitGuide.db";
    private static final int DATABASE_VERSION = 1;

    public static final String GUIDE_TABLE = "Model";
    public static final String PHONE_COLUMN = "Phone";
    public static final String NAME_COLUMN = "Name";
    public static final String ADDRESS_COLUMN = "Address";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + GUIDE_TABLE + "(" + PHONE_COLUMN + " TEXT , " + NAME_COLUMN + " TEXT, " + ADDRESS_COLUMN + " TEXT )";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+GUIDE_TABLE);
        onCreate(db);
    }

}
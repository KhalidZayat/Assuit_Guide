package com.android.khaled.assuit_guide.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.khaled.assuit_guide.Model.Model_Item;

import java.util.ArrayList;

/**
 * Created by khaled on 06/04/16.
 */
public class Favorite_Adapter {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;
    private String TABLE;


    private static final String WHERE_NAME_EQUALS = DataBaseHelper.NAME_COLUMN+ " =?";

    public Favorite_Adapter(Context context,String table) {
        this.mContext = context;
        this.TABLE = table;
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }


    public long save(Model_Item model) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.PHONE_COLUMN, model.getPhone());
        values.put(DataBaseHelper.NAME_COLUMN, model.getName());
        values.put(DataBaseHelper.ADDRESS_COLUMN, model.getAddress());

        return database.insert(TABLE, null, values);
    }

    public int delete() {
        return database.delete(TABLE, null, null);
    }

    public int deleteModel(String id)
    {
        return database.delete(TABLE,WHERE_NAME_EQUALS,new String[]{id});
    }

    public ArrayList<Model_Item> getModels() {
        ArrayList<Model_Item> models = new ArrayList<>();

        Cursor cursor = database.query(TABLE,
                new String[] { DataBaseHelper.PHONE_COLUMN,
                        DataBaseHelper.NAME_COLUMN,
                        DataBaseHelper.ADDRESS_COLUMN,
                        }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Model_Item model = new Model_Item();
            model.setPhone(cursor.getString(0));
            model.setName(cursor.getString(1));
            model.setAddress(cursor.getString(2));
            models.add(model);
        }
        return models;
    }

    public Model_Item getModel(String id) {

        Model_Item model = new Model_Item();

        String sql = "SELECT * FROM " + TABLE
                + " WHERE " + DataBaseHelper.NAME_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            model.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_COLUMN)));
            model.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME_COLUMN)));
            model.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ADDRESS_COLUMN)));
        }
        return model;
    }
}

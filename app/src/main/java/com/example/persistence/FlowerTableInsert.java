package com.example.persistence;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.List;

public class FlowerTableInsert extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flowers.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "flowers";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIES = "species";
    private static final String COLUMN_GENUS = "genus";



    public FlowerTableInsert(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "flowers" table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_NAME + " VARCHAR, "
                + COLUMN_SPECIES + " VARCHAR, "
                + COLUMN_GENUS + " VARCHAR)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if necessary
        // This method is called when DATABASE_VERSION is incremented
    }

    public long insertFlowerData(String name, String species, String genus) {
        SQLiteDatabase db = getWritableDatabase();
        long retvalue;
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_SPECIES, species);
            values.put(COLUMN_GENUS, genus);

            // Insert data into the "flowers" table
            retvalue = db.insert(TABLE_NAME, null, values);
        } finally {
            // Close the database
            db.close();
        }
        return retvalue;
    }
}

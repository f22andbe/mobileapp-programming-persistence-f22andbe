package com.example.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FlowerTableQuery extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flowers.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "flowers";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIES = "species";
    private static final String COLUMN_GENUS = "genus";
    private List<Flower> flowerList;

    public FlowerTableQuery(Context context, List<Flower> flowerList) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.flowerList = flowerList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "flowers" table
        String createTableQuery = "CREATE TABLE flowers (name VARCHAR, species VARCHAR, genus VARCHAR)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if necessary
        // This method is called when DATABASE_VERSION is incremented
    }

    public void selectFlowers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        String name, species, genus;


        try {
            // Select the desired fields from the "flowers" table
            String[] columns = {"name", "species", "genus"};
            cursor = db.query("flowers", columns, null, null, null, null, null);

            // Process the result set
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                species = cursor.getString(cursor.getColumnIndexOrThrow("species"));
                genus = cursor.getString(cursor.getColumnIndexOrThrow("genus"));
                Log.d("selectFlowers:", "name = " + name + " species = " + species + " genus = " + genus);
                flowerList.add(new Flower(name, species, genus));


            }
        } finally {
            // Close the cursor and the database
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

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


    public void deleteAllFlowerRows() {
        SQLiteDatabase db = getWritableDatabase();

        try {
            // Delete all rows from the "flowers" table
            db.delete("flowers", null, null);
        } finally {
            // Close the database
            db.close();
        }
    }

}


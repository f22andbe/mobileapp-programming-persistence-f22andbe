package com.example.persistence;

public class Tables {

    private Tables(){} //prevent Tables from being instantiated
    static class FlowerTable {
        static final String TABLE_NAME = "flowers";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_SPECIES = "species";
        static final String COLUMN_NAME_GENUS = "genus";
    }

    static final String SQL_CREATE_TABLE_FLOWERS =
    // "CREATE TABLE mountain (id INTEGER PRIMARY KEY, name TEXT, height INT)"
            "CREATE TABLE IF NOT EXISTS " + FlowerTable.TABLE_NAME + "(" +
            FlowerTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
            FlowerTable.COLUMN_NAME_NAME + " TEXT, " +
            FlowerTable.COLUMN_NAME_SPECIES + " TEXT, " +
            FlowerTable.COLUMN_NAME_GENUS + " TEXT, " +
            "UNIQUE(" + FlowerTable.COLUMN_NAME_NAME + "," + FlowerTable.COLUMN_NAME_SPECIES + "," + FlowerTable.COLUMN_NAME_GENUS +")" + " )";

    static final String SQL_DELETE_TABLE_FLOWERS =
            // "DROP TABLE IF EXISTS mountain"
            "DROP TABLE IF EXISTS " + FlowerTable.TABLE_NAME;


}

package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    EditText editFlowerName;
    EditText editFlowerSpecies;
    EditText editFlowerGenus;
    TextView displayDataView;
    Button readButton;
    Button writeButton;
    RecyclerView recyclerView;
    FlowerViewAdapter flowerAdapter;
    List<Flower> flowerList;
    FlowerTableQuery flowerQuery;
    //FlowerTableInsert flowerInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFlowerName = findViewById(R.id.edit_flowername);
        editFlowerSpecies = findViewById(R.id.edit_flowerspecies);
        editFlowerGenus = findViewById(R.id.edit_flowergenus);
        readButton = findViewById(R.id.readbutton);
        writeButton = findViewById(R.id.writebutton);
        displayDataView = findViewById(R.id.displaydata_textview);
        //recyclerView = findViewById(R.id.recyclerview);

        flowerList = new ArrayList<Flower>();
        flowerQuery = new FlowerTableQuery(this, flowerList);

        //flowerInsert = new FlowerTableInsert(this);

        /* Set up adapter
        recyclerView.setAdapter(flowerAdapter); //part of recyclerview design
        flowerAdapter = new FlowerViewAdapter(flowerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); */
    }

    /* Fetch contents of database and display in a view */
    public void onClickRead(View v) {
        //Log.d("onClickRead_top:", flowerList.toString());
        StringBuilder data = new StringBuilder();
        flowerList.clear();
        flowerQuery.selectFlowers();
        for(int i=0; i < flowerList.size(); i++) {
            data.append(flowerList.get(i).toString());
        }
        displayDataView.setText(data);
        //flowerAdapter.notifyDataSetChanged();
        //Log.d("onClickRead:", flowerList.toString());
    }

    /* write the contents of the EditText fields to the database */
    public void onClickWrite(View v) {
        /* get data */
        String name = editFlowerName.getText().toString();
        String species = editFlowerSpecies.getText().toString();
        String genus = editFlowerGenus.getText().toString();

        long ret;

        //Log.d("onClickWrite:", "name = " + name + " species = " + species + " genus = " + genus);

        /* insert data in database */
        try {
            flowerQuery.insertFlowerData(name, species, genus);
        }catch (SQLiteConstraintException e){
            editFlowerName.setText("Entries have to be unique");
            editFlowerSpecies.setText("");
            editFlowerGenus.setText("");
            return;
        }

        /*clear edit fields */
        editFlowerName.setText("");
        editFlowerSpecies.setText("");
        editFlowerGenus.setText("");
        //flowerAdapter.notifyDataSetChanged(); //part of recyclerview design
    }

    /*public void onClickDeleteTable(View v) {
        flowerQuery.deleteFlowerTable();
    }

    public void onClickCreateTable(View v) {
        flowerQuery.createFlowerTable();
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /* Delete all rows in the database */
        if (id == R.id.action_empty_database) {
            Log.d("onOptionItemSelected","Empty database");
            /* empty the database */
            showDialog();
            return true;
        }

        /* clear the view showing the contents of the database */
        if (id == R.id.action_empty_view) {
            Log.d("onOptionItemSelected","clear view");
            /* clear the view */
            Log.d("create table string ", Tables.SQL_CREATE_TABLE_FLOWERS);
            flowerList.clear();
            displayDataView.setText("");
            //flowerAdapter.notifyDataSetChanged(); //part of recyclerview design
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        ConfirmDialog dialogFragment = new ConfirmDialog();

        /* Would have been a cleaner design to just get information from
         * showDialog about which button was pressed and take action accordingly
         * but I have yet to figure out how to do that so, we register these objects
         * with the dialog and do the work in the click listener. */


        dialogFragment.setFlowerList(flowerList);
        dialogFragment.setFlowerQuery(flowerQuery);
        dialogFragment.setDataDisplayView(displayDataView);

        /* This is part of the recyclerview design I originally used
        dialogFragment.setAdapter(flowerAdapter); */
        dialogFragment.show(getSupportFragmentManager(), "ConfirmDialog");
    }
}

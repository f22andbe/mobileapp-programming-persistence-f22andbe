package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    EditText editFlowerName;
    EditText editFlowerSpecies;
    EditText editFlowerGenus;
    Button readButton;
    Button writeButton;
    RecyclerView recyclerView;
    FlowerViewAdapter flowerAdapter;
    List<Flower> flowerList;
    FlowerTableQuery flowerQuery;
    FlowerTableInsert flowerInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFlowerName = findViewById(R.id.edit_flowername);
        editFlowerSpecies = findViewById(R.id.edit_flowerspecies);
        editFlowerGenus = findViewById(R.id.edit_flowergenus);
        readButton = findViewById(R.id.readbutton);
        writeButton = findViewById(R.id.writebutton);
        recyclerView = findViewById(R.id.recyclerview);

        flowerList = new ArrayList<Flower>();
        flowerQuery = new FlowerTableQuery(this, flowerList);
        flowerInsert = new FlowerTableInsert(this);

        /* Set up adapter */
        flowerAdapter = new FlowerViewAdapter(flowerList);
        recyclerView.setAdapter(flowerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    /* click handler for read button */
    public void onClickRead(View v) {
        flowerList.clear();
        flowerQuery.selectFlowers();
        flowerAdapter.notifyDataSetChanged();
        Log.d("onClickRead:", flowerList.toString());
    }

    public void onClickWrite(View v) {
        /* get data */
        String name = editFlowerName.getText().toString();
        String species = editFlowerSpecies.getText().toString();
        String genus = editFlowerGenus.getText().toString();
        long ret;

        Log.d("onClickWrite:", "name = " + name + " species = " + species + " genus = " + genus);

        /* insert data in database */
        ret = flowerInsert.insertFlowerData(name, species, genus);

        /*clear edit fields */
        editFlowerName.setText("");
        if(ret == -1)
            editFlowerName.setText("Problem writing to database");
        editFlowerSpecies.setText("");
        editFlowerGenus.setText("");
        flowerAdapter.notifyDataSetChanged();
    }

    public void onClickEmpty(View v) {
        flowerQuery.deleteAllFlowerRows();
        flowerList.clear();
        flowerAdapter.notifyDataSetChanged();

    }

    public void onClickEmptyView(View v) {
        flowerList.clear();
        flowerAdapter.notifyDataSetChanged();
    }
}

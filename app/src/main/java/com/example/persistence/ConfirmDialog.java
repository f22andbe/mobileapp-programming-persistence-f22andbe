package com.example.persistence;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class ConfirmDialog extends DialogFragment {

    List<Flower> flowerList;
    FlowerTableQuery flowerQuery;
    TextView dataDisplayView;
    //FlowerViewAdapter flowerAdapter;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm database delete")
                .setMessage("Are you sure you want to delete the database")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click
                        flowerQuery.deleteAllFlowerRows();
                        flowerList.clear();
                        dataDisplayView.setText("");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle negative button click
                    }
                });
        return builder.create();
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public void setFlowerQuery(FlowerTableQuery flowerQuery) {
        this.flowerQuery = flowerQuery;
    }

    public void setDataDisplayView(TextView dataDisplayView) {
        this.dataDisplayView = dataDisplayView;
    }
}


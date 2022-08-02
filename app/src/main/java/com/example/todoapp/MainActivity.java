package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_popupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_popupDialog = findViewById(R.id.fab_popUpDialog);

        fab_popupDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialogFragment addDialogFragment = new AddDialogFragment();
                addDialogFragment.show(getSupportFragmentManager(), "MyFragment");
            }
        });

    }
}
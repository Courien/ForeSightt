package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Coorinate extends AppCompatActivity {

    public EditText Address;
    public EditText City;
    public EditText State;
    public EditText Zip;
    public EditText Country;
    public Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coorinate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Address = (EditText) findViewById(R.id.Address);
        City = (EditText) findViewById(R.id.City);
        State = (EditText) findViewById(R.id.State);
        Zip = (EditText) findViewById(R.id.Zip);
        Country = (EditText) findViewById(R.id.Country);

        next = (Button) findViewById(R.id.Next);

    }

    public void Next(View v)
    {

        Intent firstScreen = new Intent(this, MapsActivity.class);
        startActivity(firstScreen);

        Intent secondScreen = new Intent(this, MapsActivity.class);
        startActivity(secondScreen);
    }
}

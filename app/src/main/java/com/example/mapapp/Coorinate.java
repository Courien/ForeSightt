package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;

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


    public EditText AdressDest;
    public EditText CityDest;
    public EditText StateDest;
    public EditText ZipDest;
    public EditText CountryDest;

    public Button next;

    public String ad;
    public  String Ci;
    public  String St;
    public  String Zi;
    public  String Co;


    public String SD;
    public String CiD;
    public String StD;
    public String ZiD;
    public String CoD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coorinate);
        Address = (EditText) findViewById(R.id.Address);
        City = (EditText) findViewById(R.id.City);
        State = (EditText) findViewById(R.id.State);
        Zip = (EditText) findViewById(R.id.Zip);
        Country = (EditText) findViewById(R.id.Country);
        AdressDest = (EditText)findViewById(R.id.StreetDest);
        CityDest = (EditText) findViewById(R.id.CityDest);
        StateDest = (EditText) findViewById(R.id.StateDest);
        ZipDest = (EditText) findViewById(R.id.ZipDest);
        CountryDest = (EditText) findViewById(R.id.CountryDest);

        next = (Button) findViewById(R.id.Next);

    }

    public void Next(View v)
    {

        Intent firstScreen = new Intent(this, MapsActivity.class);
        ad = Address.getText().toString();
        Ci = City.getText().toString();
        St = State.getText().toString();
        Zi = Zip.getText().toString();
        Co = Country.getText().toString();

        SD = AdressDest.getText().toString();
        CiD = CityDest.getText().toString();
        StD = StateDest.getText().toString();
        ZiD = ZipDest.getText().toString();
        CoD = CountryDest.getText().toString();

        firstScreen.putExtra("Address", ad);
        firstScreen.putExtra("City", Ci);
        firstScreen.putExtra("State", St);
        firstScreen.putExtra("Zip", Zi);
        firstScreen.putExtra("Country", Co);


        firstScreen.putExtra("AddressDest" , SD);
        firstScreen.putExtra("CityDest" , CiD);
        firstScreen.putExtra("StateDest", StD);
        firstScreen.putExtra("ZipDest" , ZiD);
        firstScreen.putExtra("CountryDest", CoD);

        startActivity(firstScreen);
        finish();

    }
}

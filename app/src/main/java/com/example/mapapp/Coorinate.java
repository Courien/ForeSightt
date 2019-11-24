package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Coorinate extends AppCompatActivity {


    public String Collectio;
    FirebaseFirestore StoreCoords;

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


    public String SD;
    public String CiD;
    public String StD;
    public String ZiD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coorinate);

        Address = (EditText) findViewById(R.id.Address);
        City = (EditText) findViewById(R.id.City);
        State = (EditText) findViewById(R.id.TuesdayState);
        Zip = (EditText) findViewById(R.id.Zip);
        AdressDest = (EditText)findViewById(R.id.StreetDest);
        CityDest = (EditText) findViewById(R.id.CityDest);
        StateDest = (EditText) findViewById(R.id.StateDest);
        ZipDest = (EditText) findViewById(R.id.ZipDest);


        StoreCoords = FirebaseFirestore.getInstance();
        next= (Button) findViewById(R.id.Next);

        Collectio = getIntent().getExtras().getString("Collection");

    }

    public void Next(View v)
    {




        Intent firstScreen = new Intent(this, MapsActivity.class);
        ad = Address.getText().toString();
        Ci = City.getText().toString();
        St = State.getText().toString();
        Zi = Zip.getText().toString();

        SD = AdressDest.getText().toString();
        CiD = CityDest.getText().toString();
        StD = StateDest.getText().toString();
        ZiD = ZipDest.getText().toString();

        String Address2 = AdressDest.getText().toString();
        String City2 = CityDest.getText().toString();
        String State2 = StateDest.getText().toString();
        String Zip2 = ZipDest.getText().toString();
        Map<String, Object> Markers = new HashMap<>();
        Markers.put("Ending Address", Address2);
        Markers.put("Ending City", City2);
        Markers.put("Ending State", State2);
        Markers.put("Ending Zip", Zip2);

        StoreCoords.collection("user").document(Collectio).collection("Markers").document("Set Markers").update(Markers);



        firstScreen.putExtra("Address", ad);
        firstScreen.putExtra("City", Ci);
        firstScreen.putExtra("State", St);
        firstScreen.putExtra("Zip", Zi);


        firstScreen.putExtra("AddressDest" , SD);
        firstScreen.putExtra("CityDest" , CiD);
        firstScreen.putExtra("StateDest", StD);
        firstScreen.putExtra("ZipDest" , ZiD);

        firstScreen.putExtra("Collection",Collectio);
        startActivity(firstScreen);
        finish();
    }
}

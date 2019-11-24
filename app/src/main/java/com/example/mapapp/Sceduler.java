package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Sceduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sceduler);
    }

    public String Coll;


    public void Sunday(View v)
    {
            Intent Loginscreen = new Intent(this, Sunday.class);
            startActivity(Loginscreen);

    }
    public void Monday(View v)
    {
        Intent Loginscreen = new Intent(this, Monday.class);
        startActivity(Loginscreen);

    }
    public void Tuesday(View v)
    {
        Coll = getIntent().getExtras().getString("Collection");
        Intent Loginscreen = new Intent(this, Tuesday.class);
        Loginscreen.putExtra("Collection", Coll);
        startActivity(Loginscreen);
        finish();

    }
    public void Wednesday(View v)
    {
        Intent Loginscreen = new Intent(this, Wednseday.class);
        startActivity(Loginscreen);

    }
    public void Thursday(View v)
    {
        Intent Loginscreen = new Intent(this, Thursday.class);
        startActivity(Loginscreen);

    }
    public void Fridays(View v)
    {
        Intent Loginscreen = new Intent(this, Friday.class);
        startActivity(Loginscreen);

    }
    public void Saturday(View v)
    {
        Intent Loginscreen = new Intent(this, Saturday.class);
        startActivity(Loginscreen);

    }
    public void Add(View v)
    {
        Coll = getIntent().getExtras().getString("Collection");
        Intent Loginscreen = new Intent(this, Coorinate.class);
        Loginscreen.putExtra("Collection", Coll);
        startActivity(Loginscreen);
        finish();

    }
}

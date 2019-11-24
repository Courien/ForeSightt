package com.example.mapapp;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class Tuesday extends AppCompatActivity {


    FirebaseFirestore thing;
    public String Colle;
    private EditText ad;
    private EditText Ci;
    private EditText St;
    private EditText Zi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);

        thing = FirebaseFirestore.getInstance();
        ad = findViewById(R.id.TuesdayAddress);
        Ci = findViewById(R.id.TuesdayCity);
        St = findViewById(R.id.TuesdayState);
        Zi = findViewById(R.id.TuesdayZipcode);

        Colle = getIntent().getExtras().getString("Collection");
        thing.collection("user").document(Colle).collection("Markers").document("Set Markers").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String Ad1 = documentSnapshot.getString("Ending Address");
                        String Ci1 = documentSnapshot.getString("Ending City");
                        String St1 = documentSnapshot.getString("Ending State");
                        String Zi1 = documentSnapshot.getString("Ending Zip");

                        ad.setText(Ad1);
                        Ci.setText(Ci1);
                        St.setText(St1);
                        Zi.setText(Zi1);
                    }
                });
    }





}

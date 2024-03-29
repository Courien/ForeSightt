package com.example.mapapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.time.LocalTime;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public EditText Address;
    public EditText City;
    public EditText State;
    public EditText Zip;
    public EditText Country;
    public Button next;

    String Adress;
    String Ci;
    String St;
    String Zi;



    String Colle;


    String AddressDest;
    String CityDest;
    String StateDest;
    String ZipDest;

    public int overview = 0;

    FirebaseFirestore Marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Marker = FirebaseFirestore.getInstance();
        Adress = getIntent().getExtras().getString("Address");
        Ci = getIntent().getExtras().getString("City");
        St = getIntent().getExtras().getString("State");
        Zi = getIntent().getExtras().getString("Zip");

        Colle = getIntent().getExtras().getString("Collection");

        AddressDest = getIntent().getExtras().getString("AddressDest");
        CityDest  = getIntent().getExtras().getString("CityDest");
        StateDest  = getIntent().getExtras().getString("StateDest");
        ZipDest  = getIntent().getExtras().getString("ZipDest");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private DirectionsResult getDirectionsDetails(String origin, String destination, TravelMode mode) {
        DateTime now = new DateTime();
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(mode)
                    .origin(origin)
                    .destination(destination)
                    .departureTime(now)
                    .await();

        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        setupGoogleMapScreenSettings(googleMap);


        DirectionsResult results = getDirectionsDetails(Adress+","+ Ci+","+ St +Zi+", USA" ,AddressDest +"," + CityDest+","+ StateDest + ZipDest+", USA" , TravelMode.DRIVING);
        if (results != null) {
            addPolyline(results, googleMap);
            positionCamera(results.routes[overview], googleMap);
            addMarkersToMap(results, googleMap);

        }


        Marker.collection("user").document(Colle).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String Ad1 = documentSnapshot.getString("Ending Address");
                        String Ci1 = documentSnapshot.getString("Ending City");
                        String St1 = documentSnapshot.getString("Ending State");
                        String Zi1 = documentSnapshot.getString("Ending Zip");
                        String Ad2 = documentSnapshot.getString("Starting Address");
                        String Ci2 = documentSnapshot.getString("Starting City");
                        String St2 = documentSnapshot.getString("Starting State");
                        String Zi2 = documentSnapshot.getString("Starting Zip");
                        DirectionsResult r1 = getDirectionsDetails("101 Blue Lake Street, Richmond Hill, Ga 31324, USA", "3200 Rosebud Lane, Winter Park, FL 32792, USA", TravelMode.DRIVING);
                        //DirectionsResult r2 = getDirectionsDetails(Ad2 + ","+ Ci2 + ","+St2+Zi2+", USA" , Ad1 + "," + Ci1+","+ St1+Zi1+", USA", TravelMode.DRIVING);


                        googleMap.addMarker(new MarkerOptions().position(new LatLng(r1.routes[0].legs[0].endLocation.lat,r1.routes[0].legs[0].endLocation.lng)).title(r1.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(r1)));
                    }
                });


    }

    private void setupGoogleMapScreenSettings(GoogleMap mMap) {
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setTrafficEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);


    }

    private void addMarkersToMap(DirectionsResult results, GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].startLocation.lat,results.routes[0].legs[0].startLocation.lng)).title(results.routes[0].legs[0].startAddress));
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].endLocation.lat,results.routes[0].legs[0].endLocation.lng)).title(results.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(results)));
    }

    private void positionCamera(DirectionsRoute route, GoogleMap mMap) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(route.legs[0].startLocation.lat, route.legs[0].startLocation.lng), 12));
    }

    private void addPolyline(DirectionsResult results, GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

    private String getEndLocationTitle(DirectionsResult results){
        return  "Time :"+ results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext
                .setQueryRateLimit(3)
                .setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }
}

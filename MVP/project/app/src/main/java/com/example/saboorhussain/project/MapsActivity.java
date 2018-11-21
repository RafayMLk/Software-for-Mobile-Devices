package com.example.saboorhussain.project;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    public Marker u1m;
    public Marker u2m;
    public Marker u3m;
    Dialog myDialog;



    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {

            return true;


        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cannot Connect to Play Services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Connected to PLay Services", Toast.LENGTH_SHORT).show();

            myDialog = new Dialog(this);

        }

        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> address = geocoder.getFromLocation(latitude, longitude, 1);
                        String locationName = address.get(0).getLocality() + ",";
                        locationName += address.get(0).getCountryName();

                        mMap.addMarker(new MarkerOptions().position(latLng).title(locationName).draggable(true));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> address = geocoder.getFromLocation(latitude, longitude, 1);
                        String locationName = address.get(0).getLocality() + ",";
                        locationName += address.get(0).getCountryName();

                        mMap.addMarker(new MarkerOptions().position(latLng).title(locationName).draggable(true));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }

    }

    public GoogleMap getmMap() {
        return mMap;
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //List <User> USR = new ArrayList<User>(10);
        User u1 = new User("Saboor", "saboorhussain5@gmail.com", "123456", 15, "Male", "Fastinan", "03214648512", "Doctor", "Lahore", 31.4808480, 74.2997410);
        User u2 = new User("Rafay", "rafay@gmail.com", "123456", 15, "Male", "Fastinan", "03214648512", "Doctor", "Lahore", 31.4867761, 74.3115422);
        User u3 = new User("Maaz", "rafay@gmail.com", "123456", 15, "Male", "Fastinan", "03214648512", "Doctor", "Lahore", 31.4967761, 74.3215422);


        //USR.add(u1);
        //USR.add(u2);

        googleMap.setOnMarkerClickListener(this);

        // Add a marker in Sydney and move the camera
        LatLng Loc1 = new LatLng(u1.latitude, u1.longitude);
        u1m = mMap.addMarker(new MarkerOptions().position(Loc1).title(u1.Name));


        LatLng Loc2 = new LatLng(u2.latitude, u2.longitude);
        u2m = mMap.addMarker(new MarkerOptions().position(Loc2).title(u2.Name));

        LatLng Loc3 = new LatLng(u3.latitude, u3.longitude);
        u3m = mMap.addMarker(new MarkerOptions().position(Loc3).title(u3.Name));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Loc1, 12.2f));

    }

    public void ShowSearched(View view) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        MapsActivity mapsActivity = new MapsActivity();
        if (marker.equals(u1m) || marker.equals(u2m) || marker.equals(u3m)) {

            Button btn;
            myDialog.setContentView(R.layout.markerpopup);
            btn = (Button) myDialog.findViewById(R.id.btViewProfile);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapsActivity.this, Profile.class);
                    startActivity(intent);
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT  ));
            myDialog.show();

        }
        return false;
    }
}
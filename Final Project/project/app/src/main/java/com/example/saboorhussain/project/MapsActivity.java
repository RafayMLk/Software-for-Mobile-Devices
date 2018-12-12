package com.example.saboorhussain.project;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.saboorhussain.project.R.drawable.selena;

public class MapsActivity extends FragmentActivity  implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    Marker u1m;
    Marker u2m;
    Marker u3m;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
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
            Toast.makeText(this, R.string.Cannot_Connect_to_Play_Services, Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(googleServicesAvailable()) {
            Toast.makeText(this, R.string.Connected_to_Play_Services, Toast.LENGTH_SHORT).show();

            myDialog = new Dialog(this);
            myDialog.hide();

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

    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        Log.d("picassoImageTarget", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName); // Create image file
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MapsInitializer.initialize(this);


        googleMap.setOnMarkerClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        myRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Double lat = Double.valueOf(user.getLatitude());
                    Double lon = Double.valueOf(user.getLongitude());
                    LatLng loc = new LatLng(lat,lon);
                    Picasso.with(MapsActivity.this).load(user.getImageaddress().toString()).into(picassoImageTarget(getApplicationContext(), "imageDir", "my_image.jpeg"));

                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File myImageFile = new File(directory, "my_image.jpeg");
                   // Picasso.with(this).load(myImageFile).into(ivImage);
                    Marker usermarker = mMap.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromURL(user.getImageaddress()))));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


      /*  LatLng Loc1 = new LatLng(u1.latitude, u1.longitude);
        u1m = mMap.addMarker(new MarkerOptions().position(Loc1).icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.maaz))));


        LatLng Loc2 = new LatLng(u2.latitude, u2.longitude);
        u2m = mMap.addMarker(new MarkerOptions().position(Loc2).icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.rafay))));

        LatLng Loc3 = new LatLng(u3.latitude, u3.longitude);
        u3m = mMap.addMarker(new MarkerOptions().position(Loc3).icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.saboor))));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Loc1, 12.2f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc1,13.2f));
*/
    }

    public void ShowSearched(View view) {

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private View getImage(@DrawableRes int resId)
    {
        View popupview = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.markerpopup, null);
        CircleImageView  profileimage = popupview.findViewById(R.id.Profile_Image);
        profileimage.setImageResource(resId);

        popupview.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupview.layout(50, 50, popupview.getMeasuredHeight(), popupview.getMeasuredWidth());
        popupview.buildDrawingCache();


        return popupview;
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        MapsActivity mapsActivity = new MapsActivity();

        if (marker.equals(u1m))
        {
            myDialog.setContentView(R.layout.markerpopup);
        }
        else if (marker.equals(u2m))
        {
            myDialog.setContentView(getImage(R.drawable.rafay));

        }
        else if(marker.equals(u3m))
        {
            myDialog.setContentView(getImage(R.drawable.saboor));
        }

            Button btn;

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
        return false;
    }
}
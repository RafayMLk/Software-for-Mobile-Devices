package com.example.saboorhussain.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Button btn;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private FirebaseAuth.AuthStateListener mAuthListener;
    String name;
    String mail;
    String imageaddress;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile1);


        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Users");
        final FirebaseUser user = mAuth.getCurrentUser();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        findViewById(R.id.drawer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open right drawer


                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                }
                else
                    drawer.openDrawer(GravityCompat.END);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.s2, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(toggle);

        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
       final View headerView = navigationView.getHeaderView(0);


        final TextView Username = findViewById(R.id.UserName);
        final ImageView ProfileImage = findViewById(R.id.Profile_Image);
        final ImageView CoverPhoto = findViewById(R.id.imageViewcv);



        if (user != null) {
            mail = user.getEmail();
            Toast.makeText(getApplicationContext(),user.getEmail(), Toast.LENGTH_LONG ).show();
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name = dataSnapshot.child(user.getUid()).child("name").getValue(String.class);
                Username.setText(name);
                imageaddress = dataSnapshot.child(user.getUid()).child("imageaddress").getValue().toString();

                final TextView DrawerUserName =  headerView.findViewById(R.id.drawer_username);
                final TextView DrawerMail =  headerView.findViewById(R.id.drawer_mail);
                final ImageView DrawerProfileImage =  headerView.findViewById(R.id.drawerProfile);
                DrawerUserName.setText(name);
                DrawerMail.setText(mail);

                Toast.makeText(getApplicationContext(),imageaddress, Toast.LENGTH_LONG ).show();
                Picasso.with(Profile.this).load(imageaddress).fit().centerCrop().into(ProfileImage);
                Picasso.with(Profile.this).load(imageaddress).fit().centerCrop().into(CoverPhoto);
                Picasso.with(Profile.this).load(imageaddress).fit().centerCrop().into(DrawerProfileImage);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



        btn = findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, About.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.drawer);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MapsActivity.class);
                startActivity(intent);
            }
        });



        btn = findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Photos.class);
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(),"Here", Toast.LENGTH_LONG ).show();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),"Here2", Toast.LENGTH_LONG ).show();
                String imageaddress = dataSnapshot.child(user.getUid()).child("imageaddress").getValue().toString();
                String name = dataSnapshot.child(user.getUid()).child("name").getValue(String.class);
                        /*DrawerUserName.setText(name);
                        DrawerMail.setText(user.getEmail());
                        Picasso.with(Profile.this).load(imageaddress).fit().centerCrop().into(DrawerProfileImage);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Here4", Toast.LENGTH_LONG ).show();

            }
        });


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), R.string.Camera_is_clicked, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), R.string.Gallery_is_clicked, Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), R.string.Slideshow_is_clicked, Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), R.string.Tools_is_clicked, Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), R.string.Share_is_clicked, Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), R.string.Send_is_clicked, Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }




}

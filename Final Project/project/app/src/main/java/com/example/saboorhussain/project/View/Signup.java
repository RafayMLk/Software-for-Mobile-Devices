package com.example.saboorhussain.project.View;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saboorhussain.project.MainActivity;
import com.example.saboorhussain.project.MapsActivity;
import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.Model.SignupInteractor;
import com.example.saboorhussain.project.Presenter.LoginPresenter;
import com.example.saboorhussain.project.Presenter.SignupPresenter;
import com.example.saboorhussain.project.R;
import com.example.saboorhussain.project.UploadImage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class Signup extends AppCompatActivity implements SignupVIew {


    private GoogleMap mMap;
    public Marker u1m;
    public Marker u2m;
    public Marker u3m;
    Dialog myDialog;
    EditText etEmail, etPassword, etName , etProffession , etAge ,etContact;
    Button tvUpload;
    Button btSignup;
    RadioGroup rgGender;
    RadioButton rbMale,rbFemale;
    ProgressBar pbSignupProgress;
    CheckBox TermAndConditions;
    SignupPresenter mSignupPresenter;
    ImageButton imageButton;
    private int Gallery_intent = 2;
    de.hdodenhof.circleimageview.CircleImageView ProfileImage;
    StorageReference imagePath;
    Uri uri;
    String uri1;
    String lat;
    String lon;


    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    lat = Double.toString(latitude);
                    lon = Double.toString(longitude);
                    LatLng latLng = new LatLng(latitude, longitude);

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





        etName = findViewById(R.id.editText3);
        etProffession = findViewById(R.id.Profession);
        etAge = findViewById(R.id.etAge);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        pbSignupProgress = findViewById(R.id.signup_progress);
        rgGender = findViewById(R.id.radioSex);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btSignup = findViewById(R.id.btSignup);
        imageButton = findViewById(R.id.imageButton1);
        TermAndConditions = findViewById(R.id.TC);
        ProfileImage = findViewById(R.id.Profile_Image);
        tvUpload = findViewById(R.id.TVUpload);

        mSignupPresenter = new SignupPresenter(this, new SignupInteractor());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSignupPresenter.UploadImage();
            }
        });

        tvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), R.string.Upload, Toast.LENGTH_LONG).show();
                imagePath = FirebaseStorage.getInstance().getReference().child("Users").child(uri.getLastPathSegment());
                DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users");

                imagePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                @Override
                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                                    pbSignupProgress.setVisibility(View.GONE);
                                                                    Toast.makeText(getApplicationContext(), R.string.Uploaded, Toast.LENGTH_LONG).show();
                                                                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                        @Override
                                                                        public void onSuccess(Uri uri) {
                                                                            uri1 = uri.toString();
                                                                            Toast.makeText(getApplicationContext(), R.string.Start + uri1, Toast.LENGTH_LONG).show();
                                                                        }
                                                                    });

                                                                }
                                                            }

                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pbSignupProgress.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), R.string.Not_uploaded, Toast.LENGTH_LONG).show();
                            }
                        }
                ).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        pbSignupProgress.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();
                String ImageAddress = imagePath.toString();
                String Name = etName.getText().toString();
                String Age = etAge.getText().toString();
                String Contact = etContact.getText().toString();
                String Proffession = etProffession.getText().toString();
                Toast.makeText(getApplicationContext(), "End" + uri1, Toast.LENGTH_LONG).show();
                mSignupPresenter.ValidateCredentials(Email,Password,rgGender,rbMale,rbFemale,TermAndConditions,ImageAddress,Name, Age, Contact , Proffession,uri1,lat,lon);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Gallery_intent && resultCode == RESULT_OK) {
            uri = data.getData();
            ProfileImage.setImageURI(uri);
        }
    }







    @Override
    public void showProgress() {
        pbSignupProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowSuccessToast() {
        Toast.makeText(getApplicationContext(),R.string.Welcome_You_have_Signed_Up, Toast.LENGTH_LONG ).show();
        Intent intent = new Intent(Signup.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTermAndConditionEmptyError() {
        Toast.makeText(getApplicationContext(),R.string.You_must_check_Term_and_Conditions_to_Sign_Up, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void setEmailAlreadyExistsError() {
        etEmail.setError(R.string.Email_already_exists);
    }

    @Override
    public void setWeakPasswordError() {

        etPassword.setError(R.string.Password_is_very_Weak);
    }

    @Override
    public void setWrongPasswordError() {
        etPassword.setError(R.string.Wrong_Password);
    }

    @Override
    public void showVerficationEmailSentMessage() {
        Toast.makeText(getApplicationContext(),R.string.A_verification_Email_is_sent_on_your_Entered_E_mail, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void showVerficationEmailFailedMessage(String a) {
        Toast.makeText(getApplicationContext(),R.string.Mymessage+a, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void StartIntent(Intent i, int Gallery) {
        startActivityForResult(i, Gallery);
    }

    @Override
    public void showmail(String mail) {
        Toast.makeText(getApplicationContext(),R.string.Email+mail, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void hideProgress() {
        pbSignupProgress.setVisibility(View.GONE);
    }

    @Override
    public void setEmailEmptyError() {
        etEmail.setError(R.string.Email_is_Mandatory_to_Signup);
    }

    @Override
    public void setEmailInvalidError() {
        etEmail.setError(R.string.Email_is_Invalid);
    }

    @Override
    public void setPasswordEmptyError() {
        etPassword.setError(R.string.Password_is_Mandatory_to_Signup);
    }
}

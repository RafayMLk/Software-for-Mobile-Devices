package com.example.saboorhussain.project.Model;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.saboorhussain.project.MainActivity;
import com.example.saboorhussain.project.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class SignupInteractor {

    public interface OnSignupFinishedListener {
        void OnEmailEmptyError();
        void OnPasswordEmptyError();
        void OnEmailInvalidError();
        void onSuccess();
        void onTermAndConditionsEmptyError();
        void onEmailalreadyExistError();
        void onWeakPasswordError();
        void onWrongPasswordError();
        void onvVerificationEmailSent();
        void onVerificationEmailFailed(String a);
        void onIntent(Intent i,int Gallery);

        void setName(String mail);
    }

    private Matcher matcher;
    private static Pattern pattern;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int Gallery_intent = 2;
    de.hdodenhof.circleimageview.CircleImageView ProfileImage;
    StorageReference imagePath;
    Uri uri;

    public void UploadinStorage(final OnSignupFinishedListener listener) {
        Intent intent =  new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        listener.onIntent(intent,Gallery_intent);

    }





    public void PerformLogin(final String Email, final String Password, RadioGroup RadioGroupSex, RadioButton RadioButtonMale, RadioButton RadioButtonFemale, CheckBox TermAndConditions, final String ImageAddress, final String Name, final String Age, final String Contact, final String Proffession, final String uri, final String lat, final String lon , final OnSignupFinishedListener listener) {

        if(TextUtils.isEmpty(Email) )
        {
            listener.OnEmailEmptyError();

        }
        if( TextUtils.isEmpty(Password))
        {
            listener.OnPasswordEmptyError();
        }
        else
        {
            boolean check;
            pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(Email);
            check=TermAndConditions.isChecked();

            if(check)
            {
                if(matcher.matches()) {
                    mAuth = FirebaseAuth.getInstance();
                    myRef = FirebaseDatabase.getInstance().getReference("Users");

                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()) {
                                                try {
                                                    throw task.getException();
                                                }
                                                // if user enters wrong email.
                                                catch (FirebaseAuthWeakPasswordException weakPassword) {
                                                    listener.onWeakPasswordError();

                                                    // TODO: take your actions!
                                                }
                                                // if user enters wrong password.
                                                catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                                                    listener.onWrongPasswordError();

                                                    // TODO: Take your action
                                                } catch (FirebaseAuthUserCollisionException existEmail) {
                                                   listener.onEmailalreadyExistError();

                                                    // TODO: Take your action
                                                } catch (Exception e) {
                                                    e.getCause().getMessage();
                                                }
                                            }
                                            else
                                            {
                                                listener.onSuccess();

                                                FirebaseUser user = mAuth.getCurrentUser();
                                                User myUser = new User(user.getUid(),Email,Password,uri,Name,Age,Contact,Proffession,lat,lon);
                                                myRef.child(user.getUid()).setValue(myUser);

                                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            listener.onvVerificationEmailSent();
                                                            FirebaseAuth.getInstance().signOut();
                                                        }
                                                        else {
                                                            try{
                                                                throw task.getException();
                                                            }catch (Exception e) {

                                                                listener.onVerificationEmailFailed(e.getMessage().toString());
                                                            }

                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    }
                            );

                }
                else
                {
                    listener.OnEmailInvalidError();

                }
            }
            else
            {
                listener.onTermAndConditionsEmptyError();
            }

        }

    }
}
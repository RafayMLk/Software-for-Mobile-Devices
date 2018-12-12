package com.example.saboorhussain.project.Model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.saboorhussain.project.View.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.support.constraint.Constraints.TAG;

public class LoginInteractor {


    public interface OnLoginFinishedListener {
        void OnEmailEmptyError();
        void OnPasswordEmptyError();
        void OnEmailInvalidError();
        void onSuccess();
        void onWrongPasswordError();
        void onEmailNotVerified();
        void onInvalidEmail();
    }

    private Matcher matcher;
    private static Pattern pattern;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private FirebaseAuth mAuth;

    public void PerformLogin(String Email, String Password ,final OnLoginFinishedListener listener) {



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
            pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(Email);

            if(matcher.matches())
            {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        if (!task.isSuccessful())
                                        {
                                            try
                                            {
                                                throw task.getException();
                                            }
                                            // if user enters wrong password.
                                            catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                                            {
                                               listener.onWrongPasswordError();

                                                // TODO: Take your action
                                            }
                                            catch (FirebaseAuthInvalidUserException invalidEmail)
                                            {
                                                listener.onInvalidEmail();
                                            }
                                            catch (Exception e)
                                            {
                                                Log.d(TAG, R.string.onComplete + e.getMessage());
                                            }
                                        }
                                        else
                                        {
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                            if (user.isEmailVerified())
                                            {
                                                // user is verified, so you can finish this activity or send user to activity which you want.
                                                listener.onSuccess();
                                            }
                                            else
                                            {
                                                // email is not verified, so just prompt the message to the user and restart this activity.
                                                // NOTE: don't forget to log out the user.
                                                FirebaseAuth.getInstance().signOut();
                                                listener.onEmailNotVerified();
                                                //restart this activity
                                            }
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

    }
}

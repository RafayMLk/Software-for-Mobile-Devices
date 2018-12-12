package com.example.saboorhussain.project.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.saboorhussain.project.MapsActivity;
import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.Presenter.LoginPresenter;
import com.example.saboorhussain.project.Profile;
import com.example.saboorhussain.project.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener , LoginView {


    EditText etEmail, etPassword;
    Button btlogin;
    ProgressBar pbLoginProgress;

    LoginPresenter mloginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        pbLoginProgress = findViewById(R.id.pb_login_progress);
        btlogin = findViewById(R.id.btLogin);
        btlogin.setOnClickListener(this);

        mloginPresenter = new LoginPresenter(this, new LoginInteractor());
    }


    @Override
    public void onClick(View v) {
        String Email = etEmail.getText().toString();
        String Password = etPassword.getText().toString();
        mloginPresenter.ValidateCredentials(Email,Password);
    }

    @Override
    public void showProgress() {
        pbLoginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowSuccessToast() {
        Toast.makeText(getApplicationContext(),R.string.Welcome, Toast.LENGTH_LONG ).show();
        Intent intent = new Intent(LoginActivity.this, Profile.class);
        startActivity(intent);
    }

    @Override
    public void hideProgress() {
        pbLoginProgress.setVisibility(View.GONE);
    }

    @Override
    public void setEmailEmptyError() {
        etEmail.setError(R.string.Email_is_Mandatory_to_Login);
    }

    @Override
    public void setEmailInvalidError() {
        etEmail.setError(R.string.Email_is_Invalid);
    }

    @Override
    public void setPasswordEmptyError() {
        etPassword.setError(R.string.Password_is_Mandatory_to_Login);
    }

    @Override
    public void setWrongPasswordError() {
        etPassword.setError(R.string.Wrong_Password);
    }

    @Override
    public void EmailNotVerifiedError() {
        Toast.makeText(getApplicationContext(),R.string.First_Verify_your_Email_through_link_to_Sign_in, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void InvalidEmailError() {
        etEmail.setError(R.string.Must_Sign_Up_before_Sign_In);
    }
}


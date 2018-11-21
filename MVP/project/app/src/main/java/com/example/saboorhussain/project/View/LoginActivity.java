package com.example.saboorhussain.project.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.saboorhussain.project.MainActivity;
import com.example.saboorhussain.project.MapsActivity;
import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.Presenter.LoginPresenter;
import com.example.saboorhussain.project.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener , loginview {


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
        Toast.makeText(getApplicationContext(),"Here", Toast.LENGTH_LONG ).show();
        mloginPresenter.ValidateCredentials(Email,Password);
    }

    @Override
    public void showProgress() {
        pbLoginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowSuccessToast() {
        Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void hideProgress() {
        pbLoginProgress.setVisibility(View.GONE);
    }

    @Override
    public void setEmailEmptyError() {
        etEmail.setError("Email is Mandatory to Login");
    }

    @Override
    public void setEmailInvalidError() {
        etEmail.setError("Email is Invalid");
    }

    @Override
    public void setPasswordEmptyError() {
        etPassword.setError("Password is Mandatory to Login");
    }

    @Override
    public void goOnMaps() {
        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}


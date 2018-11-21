package com.example.saboorhussain.project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saboorhussain.project.MapsActivity;
import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.Model.SignupInteractor;
import com.example.saboorhussain.project.Presenter.LoginPresenter;
import com.example.saboorhussain.project.Presenter.SignupPresenter;
import com.example.saboorhussain.project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements View.OnClickListener , SignupVIew {


    EditText etEmail, etPassword;
    Button btSignup;
    RadioGroup rgGender;
    RadioButton rbMale,rbFemale;
    ProgressBar pbSignupProgress;
    CheckBox TermAndConditions;
    SignupPresenter mSignupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        pbSignupProgress = findViewById(R.id.signup_progress);
        rgGender = findViewById(R.id.radioSex);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btSignup = findViewById(R.id.btSignup);
        TermAndConditions = findViewById(R.id.TC);
        btSignup.setOnClickListener(this);

        mSignupPresenter = new SignupPresenter(this, new SignupInteractor());
    }


    @Override
    public void onClick(View v) {
        String Email = etEmail.getText().toString();
        String Password = etPassword.getText().toString();
        mSignupPresenter.ValidateCredentials(Email,Password,rgGender,rbMale,rbFemale,TermAndConditions);
    }

    @Override
    public void showProgress() {
        pbSignupProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowSuccessToast() {
        Toast.makeText(getApplicationContext(),"Welcome You have Signed Up", Toast.LENGTH_LONG ).show();
        Intent intent = new Intent(Signup.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTermAndConditionEmptyError() {
        Toast.makeText(getApplicationContext(),"You must check Term and Conditions to Sign Up", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void hideProgress() {
        pbSignupProgress.setVisibility(View.GONE);
    }

    @Override
    public void setEmailEmptyError() {
        etEmail.setError("Email is Mandatory to Signup");
    }

    @Override
    public void setEmailInvalidError() {
        etEmail.setError("Email is Invalid");
    }

    @Override
    public void setPasswordEmptyError() {
        etPassword.setError("Password is Mandatory to Signup");
    }
}

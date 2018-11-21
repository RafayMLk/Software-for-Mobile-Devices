package com.example.saboorhussain.project.Presenter;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.Model.SignupInteractor;
import com.example.saboorhussain.project.View.LoginActivity;
import com.example.saboorhussain.project.View.SignupVIew;
import com.example.saboorhussain.project.View.loginview;

import java.net.PasswordAuthentication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPresenter implements SignupInteractor.OnSignupFinishedListener {

    SignupVIew mSignupView;
    SignupInteractor mSignupInteractor;

    public SignupPresenter(SignupVIew mSignupView , SignupInteractor mSignupInteractor) {
        this.mSignupView = mSignupView;
        this.mSignupInteractor = mSignupInteractor;
    }


    public void ValidateCredentials(String Email, String Password, RadioGroup RadioGroupSex, RadioButton RadioButtonMale, RadioButton RadioButtonFemale, CheckBox TermAndConditions)
    {
        if(mSignupView!= null)
        {
            mSignupView.showProgress();
        }

        mSignupInteractor.PerformLogin(Email,Password,RadioGroupSex,RadioButtonMale,RadioButtonFemale,TermAndConditions,this);
    }

    public void onDestroy()
    {
        mSignupView = null;
    }

    @Override
    public void OnEmailEmptyError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setEmailEmptyError();
        }
    }

    @Override
    public void OnPasswordEmptyError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setPasswordEmptyError();
        }
    }

    @Override
    public void OnEmailInvalidError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setEmailInvalidError();
        }
    }

    @Override
    public void onSuccess() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.ShowSuccessToast();
        }
    }

    @Override
    public void onTermAndConditionsEmptyError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.showTermAndConditionEmptyError();
        }
    }
}
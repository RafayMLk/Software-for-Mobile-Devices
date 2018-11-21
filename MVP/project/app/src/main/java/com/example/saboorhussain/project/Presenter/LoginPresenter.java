package com.example.saboorhussain.project.Presenter;

import android.text.TextUtils;

import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.View.LoginActivity;
import com.example.saboorhussain.project.View.loginview;

import java.net.PasswordAuthentication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

    loginview mloginview;
    LoginInteractor mloginInteractor;

    public LoginPresenter(loginview mloginview , LoginInteractor mloginInteractor) {
        this.mloginview = mloginview;
        this.mloginInteractor = mloginInteractor;
    }


    public void ValidateCredentials(String Email, String Password)
    {
        if(mloginview!= null)
        {
            mloginview.showProgress();
        }

        mloginInteractor.PerformLogin(Email,Password,this);
    }

    public void onDestroy()
    {
        mloginview = null;
    }

    @Override
    public void OnEmailEmptyError() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.setEmailEmptyError();
        }
    }

    @Override
    public void OnPasswordEmptyError() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.setPasswordEmptyError();
        }
    }

    @Override
    public void OnEmailInvalidError() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.setEmailInvalidError();
        }
    }

    @Override
    public void onSuccess() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.ShowSuccessToast();
            mloginview.goOnMaps();
        }
    }
}

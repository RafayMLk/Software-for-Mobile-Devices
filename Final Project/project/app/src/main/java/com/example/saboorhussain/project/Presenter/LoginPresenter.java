package com.example.saboorhussain.project.Presenter;

import com.example.saboorhussain.project.Model.LoginInteractor;
import com.example.saboorhussain.project.View.LoginView;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

    private LoginView mloginview;
    private LoginInteractor mloginInteractor;

    public LoginPresenter(LoginView mloginview , LoginInteractor mloginInteractor) {
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
        }
    }

    @Override
    public void onWrongPasswordError() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.setWrongPasswordError();
        }
    }


    @Override
    public void onEmailNotVerified() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.EmailNotVerifiedError();
        }

    }

    @Override
    public void onInvalidEmail() {
        if(mloginview != null)
        {
            mloginview.hideProgress();
            mloginview.InvalidEmailError();
        }

    }
}

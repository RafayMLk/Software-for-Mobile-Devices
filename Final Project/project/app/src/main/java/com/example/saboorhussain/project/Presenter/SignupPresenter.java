package com.example.saboorhussain.project.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.saboorhussain.project.Model.SignupInteractor;
import com.example.saboorhussain.project.View.SignupVIew;

public class SignupPresenter implements SignupInteractor.OnSignupFinishedListener{

    SignupVIew mSignupView;
    SignupInteractor mSignupInteractor;

    public SignupPresenter(SignupVIew mSignupView , SignupInteractor mSignupInteractor) {
        this.mSignupView = mSignupView;
        this.mSignupInteractor = mSignupInteractor;
    }


    public void ValidateCredentials(String Email, String Password, RadioGroup RadioGroupSex, RadioButton RadioButtonMale, RadioButton RadioButtonFemale, CheckBox TermAndConditions, String ImageAddress, String Name, String Age, String Contact, String Proffession, String uri, String lat, String lon)
    {
        if(mSignupView!= null)
        {
            mSignupView.showProgress();
        }

        mSignupInteractor.PerformLogin(Email,Password,RadioGroupSex,RadioButtonMale,RadioButtonFemale,TermAndConditions,ImageAddress,Name, Age, Contact , Proffession,uri,lat,lon,this);
    }

    public void UploadImage() {
        mSignupInteractor.UploadinStorage(this);
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

    @Override
    public void onEmailalreadyExistError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setEmailAlreadyExistsError();
        }
    }

    @Override
    public void onWeakPasswordError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setWeakPasswordError();
        }
    }

    @Override
    public void onWrongPasswordError() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.setWrongPasswordError();
        }
    }

    @Override
    public void onvVerificationEmailSent() {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.showVerficationEmailSentMessage();
        }
    }

    @Override
    public void onVerificationEmailFailed(String a) {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.showVerficationEmailFailedMessage(a);
        }
    }

    @Override
    public void setName(String mail) {
        if(mSignupView != null)
        {
            mSignupView.hideProgress();
            mSignupView.showmail(mail);
        }
    }


    @Override
    public void onIntent(Intent i, int Gallery) {
        mSignupView.StartIntent(i,Gallery);
    }

}
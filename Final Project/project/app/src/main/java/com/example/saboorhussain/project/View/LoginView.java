package com.example.saboorhussain.project.View;

public interface LoginView {

    void showProgress();
    void ShowSuccessToast();
    void hideProgress();
    void setEmailEmptyError();
    void setEmailInvalidError();
    void setPasswordEmptyError();
    void setWrongPasswordError();
    void EmailNotVerifiedError();
    void InvalidEmailError();
}




package com.example.saboorhussain.project.View;

import android.content.Intent;

public interface SignupVIew {

    void showProgress();
    void hideProgress();
    void setEmailEmptyError();
    void setEmailInvalidError();
    void setPasswordEmptyError();
    void ShowSuccessToast();
    void showTermAndConditionEmptyError();
    void setEmailAlreadyExistsError();
    void setWeakPasswordError();
    void setWrongPasswordError();
    void showVerficationEmailSentMessage();
    void showVerficationEmailFailedMessage(String a);
    void StartIntent(Intent i, int Gallery);

    void showmail(String mail);
}

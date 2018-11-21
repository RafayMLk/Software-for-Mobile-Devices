package com.example.saboorhussain.project.Model;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginInteractor {


    public interface OnLoginFinishedListener {
        void OnEmailEmptyError();
        void OnPasswordEmptyError();
        void OnEmailInvalidError();
        void onSuccess();

    }

    private Matcher matcher;
    private static Pattern pattern;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    public void PerformLogin(String Email, String Passowrod ,final OnLoginFinishedListener listener) {



        if(TextUtils.isEmpty(Email) )
        {
            listener.OnEmailEmptyError();

        }
        if( TextUtils.isEmpty(Passowrod))
        {
            listener.OnPasswordEmptyError();
        }
        else
        {
            pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(Email);

            if(matcher.matches()==true)
            {
                listener.onSuccess();

            }
            else
            {
               listener.OnEmailInvalidError();

            }
        }

    }
}

package com.example.saboorhussain.project.Model;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupInteractor {


    public interface OnSignupFinishedListener {
        void OnEmailEmptyError();
        void OnPasswordEmptyError();
        void OnEmailInvalidError();
        void onSuccess();
        void onTermAndConditionsEmptyError();

    }

    private Matcher matcher;
    private static Pattern pattern;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";


    public void PerformLogin(String Email, String Password, RadioGroup RadioGroupSex, RadioButton RadioButtonMale, RadioButton RadioButtonFemale,CheckBox TermAndConditions,final OnSignupFinishedListener listener) {


        if(TextUtils.isEmpty(Email) )
        {
            listener.OnEmailEmptyError();

        }
        if( TextUtils.isEmpty(Password))
        {
            listener.OnPasswordEmptyError();
        }
        else
        {
            boolean check;
            pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(Email);
            check=TermAndConditions.isChecked();

            if(check)
            {
                if(matcher.matches())
                {
                    listener.onSuccess();

                }
                else
                {
                    listener.OnEmailInvalidError();

                }
            }
            else
            {
                listener.onTermAndConditionsEmptyError();
            }

        }

    }
}
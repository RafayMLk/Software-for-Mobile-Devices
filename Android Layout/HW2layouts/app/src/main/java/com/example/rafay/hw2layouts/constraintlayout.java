package com.example.rafay.hw2layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class constraintlayout extends AppCompatActivity{



    Button buttonnew;
    TextView textfield;
    private boolean male=false;
    private boolean female=false;
    RadioButton radio1;
    RadioButton radio2;
    private Matcher matcher;
    private static Pattern pattern;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private boolean checking;
    private boolean flag2;
    private String email;
    private String password;
    private String gender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayout);
        f1();
        f2();
        f3();
    }
    public void f1() {
        buttonnew=(Button)findViewById(R.id.buttonrel);
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checking=((CheckBox)findViewById(R.id.checkBox)).isChecked();
                if(checking==true) {
                    textfield = (TextView) findViewById(R.id.namer);
                    email = textfield.getText().toString();
                    textfield = (TextView) findViewById(R.id.emailr);
                    password = textfield.getText().toString();
                    radio1 = (RadioButton) findViewById(R.id.radioButton);
                    male = radio1.isChecked();
                    radio2 = (RadioButton) findViewById(R.id.femradio);
                    female = radio2.isChecked();
                    if (male == true)
                    {
                        gender=radio1.getText().toString();
                        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
                        matcher = pattern.matcher(email);
                        if(matcher.matches()==true)
                        {
                            Toast.makeText(getApplicationContext(), "Your Email is " + email + " ,   Password is  " + password+"   Gender is    "+gender , Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email entered is Invalid...!"  , Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(female==true)
                    {
                        gender=radio2.getText().toString();
                        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
                        matcher = pattern.matcher(email);
                        if(matcher.matches()==true)
                        {
                            Toast.makeText(getApplicationContext(), "Your Email is \" + email + \" ,   Password is  \" + password+\"   Gender is    "+gender , Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email entered is Invalid...!"  , Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please select the gender!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"In order to sign up, you should accept terms and connditions...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void f2() {
        radio1=(RadioButton)findViewById(R.id.radioButton);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag2=((RadioButton)findViewById(R.id.radioButton)).isChecked();
                if(flag2==true) {
                    radio1 = (RadioButton) findViewById(R.id.femradio);
                    radio1.setChecked(false);
                }
            }
        });
    }
    public void f3() {
        radio1=(RadioButton)findViewById(R.id.femradio);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag2=((RadioButton)findViewById(R.id.femradio)).isChecked();
                if(flag2==true) {
                    radio1 = (RadioButton) findViewById(R.id.radioButton);
                    radio1.setChecked(false);
                }
            }
        });
    }



}

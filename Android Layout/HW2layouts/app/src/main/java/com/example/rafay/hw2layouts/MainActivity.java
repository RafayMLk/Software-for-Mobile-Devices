package com.example.rafay.hw2layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonnew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        buttonnew=(Button)findViewById(R.id.buttonr);
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,relativelayout.class);
                startActivity(intent);
            }});
        buttonnew=(Button)findViewById(R.id.buttonc);
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(MainActivity.this,constraintlayout.class);
                startActivity(intent);
            }
        }
        );






    }
}

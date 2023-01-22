package com.example.shortstoryEnApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingLayout extends AppCompatActivity implements View.OnClickListener {
private TextView rateapp,shareApp,contactus,about,textabout;
  private   TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_layout);

        getSupportActionBar().hide();
        rateapp = findViewById(R.id.rate_id);
        shareApp = findViewById(R.id.share_id);
        about = findViewById(R.id.about);
        contactus = findViewById(R.id.contact_id);
        email = findViewById(R.id.email_id);
        textabout = findViewById(R.id.textabout);


         email.setVisibility(View.GONE);

        rateapp.setOnClickListener(this);
        shareApp.setOnClickListener(this);
        about.setOnClickListener(this);
        contactus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rate_id:
                break;
            case R.id.share_id:
                break;
            case R.id.about:
                textabout.setVisibility(View.VISIBLE);
                break;
            case R.id.contact_id:
                email.setVisibility(View.VISIBLE);
                break;
        }
    }
}
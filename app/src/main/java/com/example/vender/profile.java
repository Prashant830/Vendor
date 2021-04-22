package com.example.vender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profile extends AppCompatActivity {
TextView Emailhome,UidHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");

        Emailhome=(TextView)findViewById(R.id.emailhome);
        UidHome=(TextView)findViewById(R.id.uidhome);

        Emailhome.setText(getIntent().getStringExtra("email").toString());
        UidHome.setText("UID :"+getIntent().getStringExtra("uid").toString());

    }
}
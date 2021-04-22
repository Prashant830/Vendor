package com.example.vender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class home extends AppCompatActivity {
    EditText mage, name, discount, mcat, mgst, dc;
    Button upload;
    DatabaseReference reff;
    ProgressBar progressBar3;
    Member member;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");

        mAuth = FirebaseAuth.getInstance();
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        mage = findViewById(R.id.age);
        name = findViewById(R.id.name);
        discount = findViewById(R.id.discount);
        mcat = findViewById(R.id.catagory);
        mgst = findViewById(R.id.GST_Amount);
        dc = findViewById(R.id.dilivery_charge);
        upload = findViewById(R.id.upload);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar3.setVisibility(View.INVISIBLE);
                int agea = Integer.parseInt(mage.getText().toString().trim());
                int hit = Integer.parseInt(discount.getText().toString().trim());

                int a = Integer.parseInt(mgst.getText().toString().trim());
                int h = Integer.parseInt(dc.getText().toString().trim());


                member.setMcat(mcat.getText().toString().trim());
                member.setName(name.getText().toString().trim());
                member.setMage(agea);
                member.setDc(h);
                member.setGst(a);
                member.setDiscount(hit);
                reff.push().setValue(member);
                Toast.makeText(home.this, "data inserted successfully", Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout:
                Toast.makeText(this, "logout successfully", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(home.this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.profile:
                Intent m = new Intent(home.this, profile.class);
                m.putExtra("email",mAuth.getCurrentUser().getEmail());
                m.putExtra("uid",mAuth.getCurrentUser().getUid());
                startActivity(m);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
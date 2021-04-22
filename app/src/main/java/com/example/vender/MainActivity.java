package com.example.vender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
TextView  noAccount;
EditText  mEmail,mPassword;
Button  mLogin;
ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mEmail= findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mLogin=findViewById(R.id.login);
        noAccount=findViewById(R.id.text2);
        mAuth = FirebaseAuth.getInstance();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(Email)) {
                    progressBar.setVisibility(View.INVISIBLE);
                    mEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    progressBar.setVisibility(View.INVISIBLE);
                    mPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    progressBar.setVisibility(View.INVISIBLE);
                    mPassword.setError("Password Must be >= 6 Character");
                    return;
                }
                if (Email.equals("") && password.equals("")) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Email and Password is Blank", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Intent i = new Intent(MainActivity.this, home .class);
                                    startActivity(i);

                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Invalid Email and Password!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, home.class));
            finish();
        }
    }

}

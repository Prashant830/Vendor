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

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView alreadyAccount;
    EditText mName,mId,mPhone,mPass,mConfirmPass;
    ProgressBar progressBar2;
    Button mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        alreadyAccount=findViewById(R.id.text2);
        mName=findViewById(R.id.person);
        mId=findViewById(R.id.username);
        mPhone=findViewById(R.id.mobile_phone);
        mPass=findViewById(R.id.r_password);
        mConfirmPass=findViewById(R.id.confirm_password);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        mRegister=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar2.setVisibility(View.VISIBLE);
                String Email = mId.getText().toString().trim();
                String password = mPass.getText().toString().trim();
                String username = mName.getText().toString().trim();
                String mobile = mPhone.getText().toString().trim();
                String confirmPass = mConfirmPass.getText().toString().trim();
                if (TextUtils.isEmpty(Email)) {
                    progressBar2.setVisibility(View.INVISIBLE);
                    mId.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    progressBar2.setVisibility(View.INVISIBLE);
                    mPass.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    progressBar2.setVisibility(View.INVISIBLE);
                    mName.setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty(mobile)){
                    progressBar2.setVisibility(View.INVISIBLE);
                    mPhone.setError("Phone Number is required");
                }
                if (TextUtils.isEmpty(confirmPass)){
                    progressBar2.setVisibility(View.INVISIBLE);
                    mConfirmPass.setError("confirmPass is required");
                }

                if (password.length() < 6) {
                    progressBar2.setVisibility(View.INVISIBLE);
                    mPass.setError("Password Must be >= 6 Character");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar2.setVisibility(View.INVISIBLE);

                                    Toast.makeText(Register.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                                    Intent R = new Intent(Register.this, MainActivity.class);
                                    startActivity(R);

                                } else {
                                    progressBar2.setVisibility(View.INVISIBLE);

                                    Toast.makeText(Register.this,"Process Error!",Toast.LENGTH_SHORT).show();

                                }


                            }
                        });
            }

        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent R = new Intent(Register.this, MainActivity.class);
                startActivity(R);
                finish();
            }
        });


    }


}


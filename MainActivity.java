package com.example.smarthomefinale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView registerbtn,signinhabitant;
    EditText memail,mpassword;
    Button mlogin;

    FirebaseAuth ffauth ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerbtn =findViewById(R.id.register_text);
        memail=findViewById(R.id.memailEditText);
        mpassword=findViewById(R.id.mpasswordEditText);
        mlogin=findViewById(R.id.loginButton);
        signinhabitant =findViewById(R.id.registerhabitant_text);
    ffauth =FirebaseAuth.getInstance();


    signinhabitant.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,loginhabitant.class));
        }
    });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }

        });
mlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String eemail=memail.getText().toString().trim();
        String ppassword=mpassword.getText().toString().trim();

        if (TextUtils.isEmpty(eemail)){
            memail.setError("email is required");
        return;
        }if (TextUtils.isEmpty(ppassword)){
            mpassword.setError("password is required");
            return;
        }

        if (ppassword.length()<6){
            mpassword.setError("password must be >=6 characters");
            return;
        }

        ffauth.signInWithEmailAndPassword(eemail,ppassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "login succeful  ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,home.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
});

    }
}
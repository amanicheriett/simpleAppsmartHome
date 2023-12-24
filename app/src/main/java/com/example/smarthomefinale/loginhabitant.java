package com.example.smarthomefinale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginhabitant extends Activity {
    TextView gotosignup ;
    EditText habitantmail ,habitantpassword;
    Button loginhabitanthh;
    FirebaseAuth hfhath ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginhabitant);
        gotosignup =findViewById(R.id.hregister_text);
        habitantmail=findViewById(R.id.hemailEditText);
        habitantpassword =findViewById(R.id.hpasswordEditText);
        loginhabitanthh=findViewById(R.id.hloginButton);

        hfhath = FirebaseAuth.getInstance();

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginhabitant.this,signup.class));
            }
        });
        //login

        loginhabitanthh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hemail=habitantmail.getText().toString().trim();
                String hpassword=habitantpassword.getText().toString().trim();

                if (TextUtils.isEmpty(hemail)){
                    habitantmail.setError("email is required");
                    return;
                }if (TextUtils.isEmpty(hpassword)){
                    habitantpassword.setError("password is required");
                    return;
                }

                if (hpassword.length()<6){
                    habitantpassword.setError("password must be >=6 characters");
                    return;
                }

                hfhath.signInWithEmailAndPassword(hemail,hpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "login succeful  ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginhabitant.this,homehabitant.class));

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

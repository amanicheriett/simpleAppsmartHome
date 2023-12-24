package com.example.smarthomefinale;



import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends Activity {
    public static final String TAG="TAG";
    EditText name , mail ,password;
    Button signupadmin ,signuphabitant;
    TextView loginbtn;

FirebaseFirestore fstore;
String userID;
FirebaseAuth fauth ;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name=findViewById(R.id.fullnameEditText);
        mail=findViewById(R.id.emailEditText);
        password =findViewById(R.id.passwordEditText);
        signupadmin=findViewById(R.id.signupButton);
        signuphabitant=findViewById(R.id.Buttonhabitant);
        loginbtn =findViewById(R.id.login_text);


        fauth =FirebaseAuth.getInstance();
        fstore =FirebaseFirestore.getInstance();

        if (fauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),home.class));
            finish();
        }



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
     signupadmin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             final String email =mail.getText().toString().trim();
             String pass =password.getText().toString().trim();
             final String fullname= name.getText().toString().trim();


             if(TextUtils.isEmpty(email)){
                 mail.setError("email is required ");
return; }

            if (TextUtils.isEmpty(pass)){
                password.setError("password is required");
                return;
            }

            if (pass.length()<6){
                password.setError("password must be >=6 characters");
            }

fauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
            FirebaseUser fuser =fauth.getCurrentUser();
            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getApplicationContext(), "register succeful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: email not sent"+e.getMessage() );

                }
            });
            Toast.makeText(getApplicationContext(), "user created", Toast.LENGTH_SHORT).show();
                    userID=fauth.getCurrentUser().getUid();
            DocumentReference documentrefrence =fstore.collection("user").document(userID);
            Map<String,Object>user =new HashMap<>();
            user.put("fName",fullname);
            user.put("email",email);
            documentrefrence.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "onSuccess: user profile is created"+userID);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure:  "+e.toString());
                }
            });

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
  }
        else
        {
            Toast.makeText(signup.this, "error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
});


         }
     });

//nbda 7kayt habitant

        signuphabitant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email =mail.getText().toString().trim();
                String pass =password.getText().toString().trim();
                final String fullname= name.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mail.setError("email is required ");
                    return; }

                if (TextUtils.isEmpty(pass)){
                    password.setError("password is required");
                    return;
                }

                if (pass.length()<6){
                    password.setError("password must be >=6 characters");
                }

                fauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser fuser =fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "register succeful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "onFailure: email not sent"+e.getMessage() );

                                }
                            });
                            Toast.makeText(getApplicationContext(), "user created", Toast.LENGTH_SHORT).show();
                            userID=fauth.getCurrentUser().getUid();
                            DocumentReference documentrefrence =fstore.collection("user").document(userID);
                            Map<String,Object>user =new HashMap<>();
                            user.put("fName",fullname);
                            user.put("email",email);
                            documentrefrence.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created"+userID);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure:  "+e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),loginhabitant.class));
                        }
                        else
                        {
                            Toast.makeText(signup.this, "error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });


    }
}

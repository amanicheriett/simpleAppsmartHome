package com.example.smarthomefinale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class home extends Activity {
    Button deconecte,sucure,gerer,survailance;

   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        deconecte =findViewById(R.id.deconnecterButton);
        sucure =findViewById(R.id.secureButton);
        gerer=findViewById(R.id.gererButton);
        survailance=findViewById(R.id.survButton);
        firebaseAuth = FirebaseAuth.getInstance();

survailance.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(home.this,survaillance.class));
    }
});
gerer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(home.this,user.class));
    }
});

      deconecte.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              firebaseAuth.signOut();
              Intent intent = new Intent(home.this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);



          }

      });

      sucure.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(home.this,securite.class));

          }
      });


    }




}

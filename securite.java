package com.example.smarthomefinale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class securite extends Activity {
    TextView go ;
    Button camera,notification ,historique;
    private static final int REQUEST_IMAGE_CAPTURE = 1;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seecurite);
        go =findViewById(R.id.go_title);
        camera=findViewById(R.id.cameraButton);
        notification =findViewById(R.id.notificationButton);
        historique=findViewById(R.id.historiqueButton);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } else {
                    Toast.makeText(securite.this, "Aucune application de caméra disponible", Toast.LENGTH_SHORT).show();
                }


            }
  });





        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),home.class));
            }
        });

    }
}

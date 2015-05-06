package com.example.qtm_quantum.valutsapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button btnimage,btnvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnimage=(Button) findViewById(R.id.button1);
        btnvideo=(Button) findViewById(R.id.button2);

        btnvideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        btnimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent=new Intent(MainActivity.this,MultiPhotoSelectActivity.class);
                startActivity(intent);
            }
        });


    }


}
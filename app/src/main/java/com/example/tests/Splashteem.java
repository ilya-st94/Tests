package com.example.tests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splashteem extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashteem);


        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Splashteem.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidein_y,R.anim.slideout_y);
                finish();
            }
        }).start();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


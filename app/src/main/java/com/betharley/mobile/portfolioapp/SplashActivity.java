package com.betharley.mobile.portfolioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splash = findViewById(R.id.splash_botao);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1700);*/

        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity( intent );
                finish();
            }
        });

    }
}

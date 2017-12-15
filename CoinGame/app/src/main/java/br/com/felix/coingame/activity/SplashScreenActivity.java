package br.com.felix.coingame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.felix.coingame.R;
import br.com.felix.coingame.patern.AppFactory;

public class SplashScreenActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AppFactory.inicializaServicos(getApplicationContext());
        AppFactory.inicializaConfiguracao();

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run(){
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}

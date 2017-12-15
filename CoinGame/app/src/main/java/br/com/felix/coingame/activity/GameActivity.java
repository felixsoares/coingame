package br.com.felix.coingame.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import br.com.felix.coingame.R;
import br.com.felix.coingame.componente.FinalGameDialog;
import br.com.felix.coingame.componente.YesNoDialog;
import br.com.felix.coingame.patern.AppFactory;
import pl.droidsonroids.gif.GifImageView;

public class GameActivity extends AppCompatActivity implements Runnable{

    private TextView textViewPontuacao;
    private ImageView imgFrente;
    private GifImageView imgCoinFlip;
    private Button btnCara, btnCoroa;
    private Button btnFlip;
    private int coinSide;
    private MediaPlayer mp;

    private int quantidadeAcertosSeguidos = 0;
    private int contadorAcerto = 0;
    private int contadorDerrota = 0;
    private int contadorCara = 0;
    private int contadorCoroa = 0;

    private FinalGameDialog finalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewInit();
        viewSetup();
    }

    private void verify(){
        coinSide = 0;

        if(mp == null) {
            mp = MediaPlayer.create(GameActivity.this, R.raw.coin_flip);
        }
    }

    private void viewInit(){
        imgFrente = (ImageView) findViewById(R.id.imgFrente);
        imgCoinFlip = (GifImageView) findViewById(R.id.imgCoinFlip);
        textViewPontuacao = (TextView) findViewById(R.id.textViewPontuacao);
        btnFlip = (Button) findViewById(R.id.btnFlip);
        btnCara = (Button) findViewById(R.id.btnCara);
        btnCoroa = (Button) findViewById(R.id.btnCoroa);
    }

    private void viewSetup() {
        imgFrente.setVisibility(View.VISIBLE);
        imgCoinFlip.setVisibility(View.GONE);

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnCara.isPressed() || btnCoroa.isPressed()) {
                    btnFlip.setEnabled(false);

                    imgFrente.setVisibility(View.GONE);
                    imgCoinFlip.setVisibility(View.VISIBLE);

                    verify();

                    if (AppFactory.getConfiguracao().getTocarSom() && mp != null) {
                        mp.start();
                    }

                    coinSide = new Random().nextInt(2);

                    Handler handler = new Handler();
                    handler.postDelayed(GameActivity.this, 3000);
                }else{
                    Toast.makeText(GameActivity.this, "Selecione um lado da moeda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCara.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btnCara.setPressed(true);
                btnCoroa.setPressed(false);
                return true;
            }
        });

        btnCoroa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btnCara.setPressed(false);
                btnCoroa.setPressed(true);
                return true;
            }
        });
    }

    @Override
    public void run(){
        imgFrente.setVisibility(View.VISIBLE);
        imgCoinFlip.setVisibility(View.GONE);

        RotateAnimation rotate = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verifyWinLoose();
                btnFlip.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if(coinSide == 0){
            imgFrente.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.coin_game_frente));
        }else if(coinSide == 1){
            imgFrente.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, AppFactory.getConfiguracao().getMoedaSlecionada()));
        }

        imgFrente.startAnimation(rotate);
        mp = null;
    }

    private void verifyWinLoose(){
        boolean lost = false;

        if(coinSide == 0 && btnCara.isPressed()){
            //perdeu
            contadorCoroa = 1;
            contadorDerrota = 1;
            lost = true;
        }else if(coinSide == 0 && btnCoroa.isPressed()){
            //ganhou
            contadorCoroa = 1;
            contadorAcerto = 1;
            quantidadeAcertosSeguidos++;
        }else if(coinSide == 1 && btnCara.isPressed()){
            //ganhou
            contadorCara = 1;
            contadorAcerto = 1;
            quantidadeAcertosSeguidos++;
        }else if(coinSide == 1 && btnCoroa.isPressed()){
            //perdeu
            contadorCara = 1;
            contadorDerrota = 1;
            lost = true;
        }

        AppFactory.atualizaContadores(contadorDerrota, contadorAcerto, contadorCara, contadorCoroa, quantidadeAcertosSeguidos);

        if(lost){
            lostDialog();
        }

        contadorAcerto = 0;
        contadorDerrota = 0;
        contadorCara = 0;
        contadorCoroa = 0;

        textViewPontuacao.setText("Pontos: " + quantidadeAcertosSeguidos);
    }

    private void lostDialog(){
        finalDialog = new FinalGameDialog() {
            @Override
            public void clickImageButtonHome(View v) {
                finish();
            }

            @Override
            public void clickImageButtonShare(View v) {

            }

            @Override
            public void clickImageButtonNewGame(View v) {
                contadorAcerto = 0;
                contadorDerrota = 0;
                contadorCara = 0;
                contadorCoroa = 0;
                quantidadeAcertosSeguidos = 0;

                btnCara.setPressed(false);
                btnCoroa.setPressed(false);

                finalDialog.close();
                textViewPontuacao.setText("Pontos: " + quantidadeAcertosSeguidos);
            }
        };
        finalDialog.create(GameActivity.this, quantidadeAcertosSeguidos);
    }

    @Override
    public void onBackPressed() {
        YesNoDialog dialog = new YesNoDialog(GameActivity.this, "Alerta", "Seu progresso será perdido, deseja realmente sair?", "sim", "não") {
            @Override
            public void btnPositivo(DialogInterface dialog, int which) {
                finish();
            }

            @Override
            public void btnNegativo(DialogInterface dialog, int which) {

            }
        };
        dialog.show();
    }
}

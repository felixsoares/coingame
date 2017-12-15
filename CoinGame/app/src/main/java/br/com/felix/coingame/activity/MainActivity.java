package br.com.felix.coingame.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.felix.coingame.R;
import br.com.felix.coingame.patern.AppFactory;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private Button btnJogar;
    private ImageButton imageButtonMusica, imageButtonSom, imageButtonConfig;
    private TextView textViewQuantidadeJogada, textViewQuantidadeAcerto, textViewQuantidadeErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
        viewSetup();
        verifyMusic();
        verifySound();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewSetup();
    }

    private void viewInit(){
        imageButtonMusica = (ImageButton) findViewById(R.id.imageButtonMusica);
        imageButtonSom = (ImageButton) findViewById(R.id.imageButtonSom);
        imageButtonConfig = (ImageButton) findViewById(R.id.imageButtonConfig);

        textViewQuantidadeJogada = (TextView) findViewById(R.id.textViewQuantidadeJogada);
        textViewQuantidadeAcerto = (TextView) findViewById(R.id.textViewQuantidadeAcerto);
        textViewQuantidadeErro = (TextView) findViewById(R.id.textViewQuantidadeErro);

        btnJogar = (Button) findViewById(R.id.btnJogar);
    }

    private void viewSetup(){
        imageButtonMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null && mp.isPlaying()){
                    changeDrawableMusicImageButton(false);
                    mp.stop();
                }else{
                    mp = null;
                    changeDrawableMusicImageButton(true);
                    initMusic();
                }

                AppFactory.getConfiguracao().setTocarMusica(mp.isPlaying());
                AppFactory.getInstanceConfiguracaoDAO().atualiza(AppFactory.getConfiguracao());
            }
        });

        imageButtonSom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppFactory.getConfiguracao().getTocarSom()){
                    changeDrawableSoundImageButton(false);
                    AppFactory.getConfiguracao().setTocarSom(false);
                }else{
                    changeDrawableSoundImageButton(true);
                    AppFactory.getConfiguracao().setTocarSom(true);
                }

                AppFactory.getInstanceConfiguracaoDAO().atualiza(AppFactory.getConfiguracao());
            }
        });

        textViewQuantidadeAcerto.setText(AppFactory.getConfiguracao().getQuantidadeAcerto().toString());
        textViewQuantidadeErro.setText(AppFactory.getConfiguracao().getQuantidadeErro().toString());
        textViewQuantidadeJogada.setText(AppFactory.getConfiguracao().getQuantidadeJogada().toString());

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }

    private void verifyMusic(){
        if(AppFactory.getConfiguracao().getTocarMusica()){
            changeDrawableMusicImageButton(true);
            initMusic();
        }else{
            changeDrawableMusicImageButton(false);
        }
    }

    private void verifySound(){
        if(AppFactory.getConfiguracao().getTocarSom()){
            changeDrawableSoundImageButton(true);
        }else{
            changeDrawableSoundImageButton(false);
        }
    }

    private void initMusic(){
        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.vintage_elecro_pop_loop);
            mp.setVolume(25, 25);
        }

        mp.setLooping(true);
        mp.start();
    }

    private void changeDrawableMusicImageButton(Boolean show){
        if(!show) {
            imageButtonMusica.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_music_off_grey600_18dp));
        }else{
            imageButtonMusica.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_music_grey600_18dp));
        }
    }

    private void changeDrawableSoundImageButton(Boolean show){
        if(!show) {
            imageButtonSom.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_music_note_off_grey600_18dp));
        }else{
            imageButtonSom.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_music_note_grey600_18dp));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mp != null){
            mp.stop();
        }
    }
}

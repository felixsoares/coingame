package br.com.felix.coingame.componente;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.com.felix.coingame.R;
import br.com.felix.coingame.patern.AppFactory;

public abstract class FinalGameDialog {

    private Dialog dialog;

    public void create(Context context, int contadorAcerto){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_restart_game);
        dialog.setCancelable(false);
        dialog.setTitle("Você perdeu");

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        TextView textViewPontuacao = (TextView) dialog.findViewById(R.id.textViewPontuacao);
        textViewPontuacao.setText(contadorAcerto+"");

        TextView textViewPontuacaoSeguida = (TextView) dialog.findViewById(R.id.textViewPontuacaoSeguida);
        textViewPontuacaoSeguida.setText(AppFactory.getConfiguracao().getQuantidadePontosSeguidos().toString());

        TextView textViewQuantidadeAcerto = (TextView) dialog.findViewById(R.id.textViewQuantidadeAcerto);
        textViewQuantidadeAcerto.setText(AppFactory.getConfiguracao().getQuantidadeAcerto().toString());

        TextView textViewQuantidadeErro = (TextView) dialog.findViewById(R.id.textViewQuantidadeErro);
        textViewQuantidadeErro.setText(AppFactory.getConfiguracao().getQuantidadeErro().toString());

        TextView textViewPorcentagem = (TextView) dialog.findViewById(R.id.textViewPorcentagem);
        Double porcentagem = ((Double.parseDouble(AppFactory.getConfiguracao().getQuantidadeAcerto() + ".0") / Double.parseDouble(AppFactory.getConfiguracao().getQuantidadeErro() + ".0")) * 100);
        int color = R.color.red;
        if(porcentagem >= 100){
            color = R.color.green;
        }
        textViewPorcentagem.setTextColor(context.getResources().getColor(color));
        textViewPorcentagem.setText(getDoubleValue(porcentagem));

        ImageButton imageButtonHome = (ImageButton) dialog.findViewById(R.id.imageButtonHome);
        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImageButtonHome(v);
            }
        });

        ImageButton imageButtonShare = (ImageButton) dialog.findViewById(R.id.imageButtonShare);
        imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImageButtonShare(v);
            }
        });

        ImageButton imageButtonNewGame = (ImageButton) dialog.findViewById(R.id.imageButtonNewGame);
        imageButtonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImageButtonNewGame(v);
            }
        });

        dialog.show();
    }

    public void close(){
        if(dialog != null){
            dialog.dismiss();
        }
    }

    private String getDoubleValue(Double valor){
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(valor);
    }

    public abstract void clickImageButtonHome(View v);
    public abstract void clickImageButtonShare(View v);
    public abstract void clickImageButtonNewGame(View v);
}

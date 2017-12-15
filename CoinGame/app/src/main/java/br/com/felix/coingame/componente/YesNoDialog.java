package br.com.felix.coingame.componente;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class YesNoDialog extends AlertDialog.Builder {

    public YesNoDialog(Context context, String titulo, String menssagem, String btnPositivo, String btnNegativo){
        super(context);

        setTitle(titulo);
        setMessage(menssagem);
        setPositiveButton(btnPositivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnPositivo(dialog, which);
                dialog.dismiss();
            }
        });

        setNegativeButton(btnNegativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnNegativo(dialog, which);
                dialog.dismiss();
            }
        });
    }

    public abstract void btnPositivo(DialogInterface dialog, int which);
    public abstract void btnNegativo(DialogInterface dialog, int which);
}

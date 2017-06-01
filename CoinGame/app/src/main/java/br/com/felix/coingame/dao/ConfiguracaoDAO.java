package br.com.felix.coingame.dao;

import android.content.ContentValues;
import android.database.Cursor;

import br.com.felix.coingame.helper.DataBaseService;
import br.com.felix.coingame.model.Configuracao;
import br.com.felix.coingame.patern.AppFactory;
import br.com.felix.coingame.util.UtilDB;

public class ConfiguracaoDAO {
    private DataBaseService dataBaseService;

    public ConfiguracaoDAO() {
        dataBaseService = AppFactory.getInstanceDataBaseService();
    }

    public void incluir(Configuracao configuracao) {
        ContentValues values = new ContentValues();
        values.put(UtilDB.COLUMN_CONFIGURACAO_ID, configuracao.getId());
        values.put(UtilDB.COLUMN_CONFIGURACAO_TOCAR_MUSICA, configuracao.getTocarMusica().toString());
        values.put(UtilDB.COLUMN_CONFIGURACAO_TOCAR_SOM, configuracao.getTocarSom().toString());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_JOGADA, configuracao.getQuantidadeJogada());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_PONTOS_SEGUIDOS, configuracao.getQuantidadePontosSeguidos());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ACERTO, configuracao.getQuantidadeAcerto());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ERRO, configuracao.getQuantidadeErro());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_CARAS, configuracao.getQuantidadeCaras());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_COROAS, configuracao.getQuantidadeCoroas());

        dataBaseService.getDataBase().insert(UtilDB.TABLE_CONFIGURACAO, null, values);
    }

    public void atualiza(Configuracao configuracao) {
        ContentValues values = new ContentValues();
        values.put(UtilDB.COLUMN_CONFIGURACAO_ID, configuracao.getId());
        values.put(UtilDB.COLUMN_CONFIGURACAO_TOCAR_MUSICA, configuracao.getTocarMusica().toString());
        values.put(UtilDB.COLUMN_CONFIGURACAO_TOCAR_SOM, configuracao.getTocarSom().toString());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_JOGADA, configuracao.getQuantidadeJogada());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_PONTOS_SEGUIDOS, configuracao.getQuantidadePontosSeguidos());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ACERTO, configuracao.getQuantidadeAcerto());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ERRO, configuracao.getQuantidadeErro());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_CARAS, configuracao.getQuantidadeCaras());
        values.put(UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_COROAS, configuracao.getQuantidadeCoroas());

        dataBaseService.getDataBase().update(UtilDB.TABLE_CONFIGURACAO, values, UtilDB.COLUMN_CONFIGURACAO_ID + " = " + configuracao.getId(), null);
    }

    public Configuracao consultaConfiguracao() {

        String[] columns = {UtilDB.COLUMN_CONFIGURACAO_ID, UtilDB.COLUMN_CONFIGURACAO_TOCAR_MUSICA,
                UtilDB.COLUMN_CONFIGURACAO_TOCAR_SOM, UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_JOGADA,
                UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_PONTOS_SEGUIDOS,
                UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ACERTO, UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ERRO,
                UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_CARAS, UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_COROAS

        };

        Cursor mCursor = dataBaseService.getDataBase().query(true, UtilDB.TABLE_CONFIGURACAO, columns, null, null, null, null, null, null);

        Configuracao configuracao = null;

        if (mCursor != null) {

            if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();

                configuracao = new Configuracao();
                configuracao.setId(mCursor.getLong(0));
                configuracao.setTocarMusica(Boolean.parseBoolean(mCursor.getString(1)));
                configuracao.setTocarSom(Boolean.parseBoolean(mCursor.getString(2)));
                configuracao.setQuantidadeJogada(mCursor.getInt(3));
                configuracao.setQuantidadePontosSeguidos(mCursor.getInt(4));
                configuracao.setQuantidadeAcerto(mCursor.getInt(5));
                configuracao.setQuantidadeErro(mCursor.getInt(6));
                configuracao.setQuantidadeCaras(mCursor.getInt(7));
                configuracao.setQuantidadeCoroas(mCursor.getInt(8));
            }

            mCursor.close();
        }

        return configuracao;
    }
}

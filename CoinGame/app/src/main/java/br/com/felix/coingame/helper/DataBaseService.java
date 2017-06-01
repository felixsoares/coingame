package br.com.felix.coingame.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.felix.coingame.util.UtilDB;


public class DataBaseService {

	private SQLiteDatabase dataBase;
    private OpenHelper openHelper;
	
	public DataBaseService(Context context) {
		openHelper = new OpenHelper(context);
		this.dataBase = openHelper.getWritableDatabase();
	}

	public SQLiteDatabase getDataBase() {
		return dataBase;
	}

	private static class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context) {
			super(context, UtilDB.NOME_DB, null, UtilDB.VERSAO_DB);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			criaTabelaConfiguracao(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

		private void criaTabelaConfiguracao(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + UtilDB.TABLE_CONFIGURACAO +
					"("+
						UtilDB.COLUMN_CONFIGURACAO_ID +" INTEGER PRIMARY KEY, "+
						UtilDB.COLUMN_CONFIGURACAO_TOCAR_MUSICA +" TEXT, "+
						UtilDB.COLUMN_CONFIGURACAO_TOCAR_SOM +" TEXT, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_JOGADA +" INTEGER, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_PONTOS_SEGUIDOS +" INTEGER, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ACERTO +" INTEGER, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_ERRO +" INTEGER, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_CARAS +" INTEGER, "+
						UtilDB.COLUMN_CONFIGURACAO_QUANTIDADE_COROAS +" INTEGER"+
					")");
		}
	}
}
package com.notiufg.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelperNotificacao extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "NotiUFG.db";
    public static final String TABLE_NAME = "notificacao";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "id";
    public static final String NOME_REMETENTE = "nomeRemetente";
    public static final String TEXTO = "texto";
    public static final String DATA_ENVIO = "dataEnvio";
    private static final String DATABASE_CREATE = "create table "
		+ TABLE_NAME + "( " + ID
		+ " integer primary key autoincrement, " + NOME_REMETENTE
		+ " text not null, " + TEXTO + " text not null, " + DATA_ENVIO + " DATETIME DEFAULT CURRENT_TIMESTAMP "+");";
    
   public DbHelperNotificacao(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }
   
   @Override
   public void onCreate(SQLiteDatabase db) {
           db.execSQL(DATABASE_CREATE);
   }
   
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           Log.w(DbHelperUsuario.class.getName(),
           "Upgrading database from version " + oldVersion + " to "
           + newVersion + ", which will destroy all old data");
          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          onCreate(db);
  }
}
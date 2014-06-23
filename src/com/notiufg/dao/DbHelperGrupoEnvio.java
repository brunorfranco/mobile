package com.notiufg.dao;

import com.notiufg.util.DBUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelperGrupoEnvio extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "NotiUFG.db";
    public static final String TABLE_NAME = "grupoEnvio";
    private static final int DATABASE_VERSION = DBUtil.versaoDB;
    public static final String ID = "id";
    public static final String NOME_GRUPO = "nomeGrupo";
    public static final String ATIVO = "ativo";
    public static final String ISPUBLICO = "isPublico";
    public static final String IDCURSO = "idCurso";
    private static final String DATABASE_CREATE = "create table "
		+ TABLE_NAME + "( " + ID
		+ " integer primary key autoincrement, " + NOME_GRUPO
		+ " text not null, " + ATIVO + " text not null, " + ISPUBLICO + " integer not null , "
		+ IDCURSO + " integer not null);";
    
   public DbHelperGrupoEnvio(Context context) {
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
  
  @Override
  public void onOpen(SQLiteDatabase db) {
	  super.onOpen(db);
//	  db.execSQL("drop table notificacao");
//	  onCreate(db);
	  
  }
}

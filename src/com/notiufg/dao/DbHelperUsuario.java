package com.notiufg.dao;

import com.notiufg.util.DBUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelperUsuario extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "NotiUFG.db";
    public static final String TABLE_NAME = "usuario";
    private static final int DATABASE_VERSION = DBUtil.versaoDB;
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String SENHA = "senha";
    public static final String MATRICULA = "matricula";
    public static final String IDCURSO = "idCurso";
    private static final String DATABASE_CREATE = "create table "
		+ TABLE_NAME + "( " + ID
		+ " integer primary key autoincrement, " + NOME
		+ " text not null, " + CPF + " text not null, " + EMAIL + " text not null, "
		+ TELEFONE+" text not null, " + SENHA + " text not null " +
		", " + MATRICULA + " text not null, " + IDCURSO + " integer not null);";
    
   public DbHelperUsuario(Context context) {
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
package com.notiufg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.notiufg.entity.Configuracao;

public class DBAdapterConfiguracao {

	private SQLiteDatabase database;
	private DBHelperConfiguracao dbHelper;
	private String[] allColumns = { DBHelperConfiguracao.ID, DBHelperConfiguracao.IDUSUARIO, 
			DBHelperConfiguracao.IDSGRUPOSENVIO};
	
	public DBAdapterConfiguracao(Context context) {          
		dbHelper = new DBHelperConfiguracao(context);
	}
	
	public Configuracao createConfiguracao(Long idUsuario, String idsGruposEnvio) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.IDUSUARIO, idUsuario); 
        values.put(dbHelper.IDSGRUPOSENVIO, idsGruposEnvio); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToConfiguracao(cursor); 
	}
	
	public Configuracao cursorToConfiguracao(Cursor cursor) { 
		Configuracao configuracao = new Configuracao(cursor.getLong(0),cursor.getLong(1),cursor.getString(2)); 
        return configuracao; 
	}
	
	public void dropTable(){
		database.execSQL("DROP TABLE IF EXISTS configuracao");
	}
	
	public Cursor getConfiguracoes(){ 
        Cursor cursor = database.rawQuery("select id, idUsuario, idsGruposEnvio from configuracao", null); 
        return cursor; 
	}
	
	public Cursor getConfiguracoesBydIdUsuario(Long idUsuario){ 
        Cursor cursor = database.rawQuery("select id, idUsuario, idsGruposEnvio from configuracao where idUsuario = " + idUsuario, null); 
        return cursor; 
	}
	
	public void updateConfiguracao(Long idUsuario, String listIdsGrupos){ 
		ContentValues data = new ContentValues();
		data.put("idsGruposEnvio", listIdsGrupos);
		database.update("configuracao", data, "idUsuario ="+idUsuario, null);
	}
	
	public Configuracao getConfiguracao (int idConfiguracao){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idConfiguracao, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToConfiguracao(cursor); 
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void atualizaTabela(){
		dbHelper.onUpgrade(database, 7, 8);
	}
	
	public void deletaConfiguracao (Long idConfiguracao){ 
        database.delete(DbHelperUsuario.TABLE_NAME, DBHelperConfiguracao.ID + " = " + idConfiguracao, null); 
	}
	
	public void createTable(){
		dbHelper.onCreate(database);
	}
	
}

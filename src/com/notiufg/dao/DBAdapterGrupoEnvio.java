package com.notiufg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.notiufg.entity.GrupoEnvio;

public class DBAdapterGrupoEnvio {

	private SQLiteDatabase database;
	private DbHelperGrupoEnvio dbHelper;
	private String[] allColumns = { DbHelperGrupoEnvio.ID, DbHelperGrupoEnvio.NOME_GRUPO, 
			DbHelperGrupoEnvio.ATIVO, DbHelperGrupoEnvio.ISPUBLICO};
	
	public DBAdapterGrupoEnvio(Context context) {          
		dbHelper = new DbHelperGrupoEnvio(context);
	}
	
	public GrupoEnvio createGrupoEnvio(String nome, String ativo, Integer isPublico) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.NOME_GRUPO, nome); 
        values.put(dbHelper.ATIVO, ativo); 
        values.put(dbHelper.ISPUBLICO, isPublico); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToGrupoEnvio(cursor); 
	}
	
	public GrupoEnvio cursorToGrupoEnvio(Cursor cursor) { 
		GrupoEnvio grupoEnvio = new GrupoEnvio(cursor.getLong(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3)); 
        return grupoEnvio; 
	}
	
	public void dropTable (){ 
        database.rawQuery("drop table grupoEnvio", null);
	}
	
	public Cursor getGruposEnvio(){ 
        Cursor cursor = database.rawQuery("select id, nomeGrupo, ativo from grupoEnvio", null); 
        return cursor; 
	}
	
	public GrupoEnvio getGrupoEnvio (int idGrupo){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idGrupo, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToGrupoEnvio(cursor); 
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void atualizaTabela(){
		dbHelper.onUpgrade(database, 5, 6);
	}
	
}

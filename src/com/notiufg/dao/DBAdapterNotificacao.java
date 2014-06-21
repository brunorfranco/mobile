package com.notiufg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.notiufg.entity.Notificacao;

public class DBAdapterNotificacao {

	private SQLiteDatabase database;
	private DbHelperNotificacao dbHelper;
	private String[] allColumns = { DbHelperNotificacao.ID, DbHelperNotificacao.NOME_REMETENTE, 
			DbHelperNotificacao.TEXTO, DbHelperNotificacao.DATA_ENVIO,
			DbHelperNotificacao.ID_GRUPO_ENVIO, DbHelperNotificacao.FOILIDA};
	
	public DBAdapterNotificacao(Context context) {          
		dbHelper = new DbHelperNotificacao(context);
	}
	
	public Notificacao createNotificacao(String nome, String texto, String dataEnvio, 
			Integer idGrupoEnvio, Integer foiLida) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.NOME_REMETENTE, nome); 
        values.put(dbHelper.TEXTO, texto); 
        values.put(dbHelper.DATA_ENVIO,dataEnvio); 
        values.put(dbHelper.ID_GRUPO_ENVIO,idGrupoEnvio); 
        values.put(dbHelper.FOILIDA,foiLida); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToNotificacao(cursor); 
	}
	
	public Notificacao cursorToNotificacao(Cursor cursor) { 
		Notificacao notificacao = new Notificacao(cursor.getLong(0),cursor.getString(1),cursor.getString(2), 
        cursor.getString(3), cursor.getLong(4), cursor.getInt(5)); 
        return notificacao; 
	}
	
	public void deletaNotificacao (Long idNotificacao){ 
        database.delete(DbHelperNotificacao.TABLE_NAME, DbHelperUsuario.ID + " = " + idNotificacao, null); 
	}
	
	public void dropTable (){ 
        database.rawQuery("drop table notificacao", null);
	}
	
	public void deleteFromTable (){ 
        database.rawQuery("delete from notificacao", null);
	}
	
	public Cursor getNotificacoes(){ 
        Cursor cursor = database.rawQuery("select id, nomeRemetente, texto, dataEnvio, idGrupoEnvio, foiLida from notificacao", null); 
        return cursor; 
	}
	
	public Notificacao getNotificacao (Long idNotificacao){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idNotificacao, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToNotificacao(cursor); 
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
	
	public void marcaComoNaoLida (Long idNotificacao){ 
        database.rawQuery("update notificacao set foiLida = 0 where id = " + idNotificacao, null);
	}
	
	public void marcaComoLida (Long idNotificacao){ 
        database.rawQuery("update notificacao set foiLida = 1 where id = " + idNotificacao, null);
	}
	
}
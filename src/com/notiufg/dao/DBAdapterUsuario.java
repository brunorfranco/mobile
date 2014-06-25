package com.notiufg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.notiufg.entity.Usuario;

public class DBAdapterUsuario {

	private SQLiteDatabase database;
	private DbHelperUsuario dbHelper;
	private String[] allColumns = { DbHelperUsuario.ID, DbHelperUsuario.NOME, dbHelper.CPF, DbHelperUsuario.EMAIL,
	DbHelperUsuario.TELEFONE, DbHelperUsuario.SENHA, DbHelperUsuario.MATRICULA, DbHelperUsuario.IDCURSO};
	
	public DBAdapterUsuario(Context context) {          
		dbHelper = new DbHelperUsuario(context);
	}
	
	public Usuario createUsuario(String nome, String cpf, String email, String telefone,
			String senha, String matricula, Long idCurso) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.NOME, nome); 
        values.put(dbHelper.CPF, nome); 
        values.put(dbHelper.EMAIL,email); 
        values.put(dbHelper.TELEFONE,telefone); 
        values.put(dbHelper.SENHA,senha); 
        values.put(dbHelper.MATRICULA,matricula); 
        values.put(dbHelper.IDCURSO,idCurso); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToUsuario(cursor); 
	}
	
	public Usuario cursorToUsuario(Cursor cursor) { 
		Usuario contacto = null;
		
		try {
			contacto = new Usuario(cursor.getLong(0),cursor.getString(1),cursor.getString(2), 
			cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getLong(7));
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return contacto; 
	}
	
	public void deletaUsuario (Long idUsuario){ 
        database.delete(DbHelperUsuario.TABLE_NAME, DbHelperUsuario.ID + " = " + idUsuario, null); 
	}
	
	public Cursor getUsuarios(){ 
        Cursor cursor = database.rawQuery("select id, nome, cpf, email, telefone, senha, matricula, idCurso from usuario", null); 
        return cursor; 
	}
	
	public Usuario getUsuario (int idUsuario){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idUsuario, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToUsuario(cursor); 
	}
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void createTable(){
		dbHelper.onCreate(database);
	}
	
	public Usuario findUsuarioValido (String login, String senha){ 
        Cursor cursor = null;
		cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.NOME + " like '%" + 
				login + "%' AND " + dbHelper.SENHA + " like '"+ senha +"' " , null,null, null, null); 
		cursor.moveToFirst();
       return cursorToUsuario(cursor);
	}
	
	public void atualizaTabela(){
		dbHelper.onUpgrade(database, 6, 7);
	}
	
	public void dropTable(){
		database.execSQL("DROP TABLE IF EXISTS usuario");
	}
	
}

package com.notify.db;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.notify.entity.Usuario;

public class DBAdapterUsuario {

	private SQLiteDatabase database;
	private DbHelperUsuario dbHelper;
	private String[] allColumns = { DbHelperUsuario.ID, DbHelperUsuario.NOME, dbHelper.CPF, DbHelperUsuario.EMAIL,
	DbHelperUsuario.TELEFONE};
	
	public DBAdapterUsuario(Context context) {          
		dbHelper = new DbHelperUsuario(context);
	}
	
	public Usuario createUsuario(String nome, String cpf, String email, String telefone, Bitmap foto) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.NOME, nome); 
        values.put(dbHelper.CPF, nome); 
        values.put(dbHelper.EMAIL,email); 
        values.put(dbHelper.TELEFONE,telefone); 
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        foto.compress(Bitmap.CompressFormat.PNG, 100, baos); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToUsuario(cursor); 
	}
	
	private Usuario cursorToUsuario(Cursor cursor) { 
		Usuario contacto = new Usuario(cursor.getLong(0),cursor.getString(1),cursor.getString(2), 
        cursor.getString(3),cursor.getString(4)); 
        return contacto; 
	}
	
	public void deletaUsuario (int idUsuario){ 
        database.delete(DbHelperUsuario.TABLE_NAME, DbHelperUsuario.ID + " = " + idUsuario, null); 
	}
	
	public Cursor getUsuarios(){ 
        Cursor cursor = database.rawQuery("select id, nome, cpf, telefone from usuario", null); 
        return cursor; 
	}
	
	public Usuario getUsuario (int idUsuario){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idUsuario, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToUsuario(cursor); 
	}
	
}

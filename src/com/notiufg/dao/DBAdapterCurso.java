package com.notiufg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.notiufg.entity.Curso;

public class DBAdapterCurso {

	private SQLiteDatabase database;
	private DBHelperCurso dbHelper;
	private String[] allColumns = { DBHelperCurso.ID, DBHelperCurso.NOMECURSO};
	
	public DBAdapterCurso(Context context) {          
		dbHelper = new DBHelperCurso(context);
	}
	
	public Curso createCurso(String nomeCurso) { 
        ContentValues values = new ContentValues(); 
        values.put(dbHelper.NOMECURSO, nomeCurso); 
        long insertId = database.insert(dbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToCurso(cursor); 
	}
	
	public Curso cursorToCurso(Cursor cursor) { 
		Curso curso = null;
		
		try {
			curso = new Curso(cursor.getLong(0),cursor.getString(1));
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return curso; 
	}
	
	public void deletaCurso (Long idCurso){ 
        database.delete(DBHelperCurso.TABLE_NAME, DBHelperCurso.ID + " = " + idCurso, null); 
	}
	
	public Cursor getCursos(){ 
        Cursor cursor = database.rawQuery("select id, nomeCurso from curso", null); 
        return cursor; 
	}
	
	public Curso getCurso (int idCurso){ 
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + 
        idCurso, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToCurso(cursor); 
	}
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	public void createTable(){
		database.rawQuery("create table curso (id integer primary key autoincrement, nomeCurso text not null);", null); 
	}
	
	public void atualizaTabela(){
		dbHelper.onUpgrade(database, 6,7);
	}
	
}

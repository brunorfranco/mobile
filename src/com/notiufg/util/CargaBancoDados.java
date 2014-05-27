package com.notiufg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import android.database.Cursor;

import com.notiufg.activity.MainActivity;
import com.notiufg.db.DBAdapterNotificacao;
import com.notiufg.db.DBAdapterUsuario;
import com.notiufg.entity.Notificacao;

public class CargaBancoDados {

	public static void carregaNotificacoesIniciais(MainActivity mainActivity){
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(mainActivity); 
		datasource.open();
		
		limpaTabelaNotificacao(datasource);
		carregaDadosTabelaNotificacao(datasource);
		
		datasource.close();
	}
	
	private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
	
	private static void limpaTabelaNotificacao(DBAdapterNotificacao datasource){
		Cursor cursor = datasource.getNotificacoes();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Notificacao noti = datasource.cursorToNotificacao(cursor);
			datasource.deletaNotificacao(noti.getId());
		    cursor.moveToNext();
		}
	}
	
	private static void carregaDadosTabelaNotificacao(DBAdapterNotificacao datasource){
		for (int i = 0; i < 20; i++) {
//			String nome = UUID.randomUUID().toString();
			String nome = "Disciplina" + i;
//			String texto = UUID.randomUUID().toString();
			String texto = "Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste" + i;
			datasource.createNotificacao(nome, texto, getDateTime());
		}
	}
	
	public static void insereUsuarioPadrao(MainActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		
		Cursor cursor = datasource.getUsuarios();
		cursor.moveToFirst();
		if (cursor.isAfterLast() != false) {
			datasource.createUsuario("Bruno", "030.813.361-71", "bruno@teste.com", "32618888");
		}
		
		datasource.close();
	}
	
}

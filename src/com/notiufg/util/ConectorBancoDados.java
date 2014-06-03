package com.notiufg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.database.Cursor;

import com.notiufg.activity.LoginActivity;
import com.notiufg.activity.MainActivity;
import com.notiufg.db.DBAdapterGrupoEnvio;
import com.notiufg.db.DBAdapterNotificacao;
import com.notiufg.db.DBAdapterUsuario;
import com.notiufg.entity.Notificacao;
import com.notiufg.entity.Usuario;

public class ConectorBancoDados {

	public static void carregaNotificacoesIniciais(MainActivity mainActivity){
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(mainActivity); 
		datasource.open();
		
		limpaTabelaNotificacao(datasource);
		carregaDadosTabelaNotificacao(datasource);
		
		datasource.close();
	}
	
	public static void carregaGrupoEnvio(MainActivity mainActivity){
		DBAdapterGrupoEnvio datasource = new DBAdapterGrupoEnvio(mainActivity); 
		datasource.open();
		
		carregaDadosGrupoEnvio(datasource);
		
		datasource.close();
	}
	
	public static void dropTableNotificacoes(MainActivity mainActivity){
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(mainActivity); 
		datasource.open();
		
		datasource.dropTable();
		datasource.close();
	}
	
	public static void createTableUsuario(MainActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		
		datasource.createTable();
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
			String nome = "Disciplina" + i;
			String texto = "Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste "
					+ "Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto "
					+ "Teste Texto Teste Texto Teste" + i;
			Integer idGrupoEnvio = 1 + (int)(Math.random() * ((4 - 1) + 1));
			datasource.createNotificacao(nome, texto, getDateTime(), idGrupoEnvio);
		}
	}
	
	private static void carregaDadosGrupoEnvio(DBAdapterGrupoEnvio datasource){
		for (int i = 0; i < 5; i++) {
			String nome = "Disciplina" + i;
			datasource.createGrupoEnvio(nome, "S");
		}
	}
	
	public static void insereUsuarioPadrao(MainActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		
		Cursor cursor = datasource.getUsuarios();
		cursor.moveToFirst();
		if (cursor.isAfterLast() != false) {
			datasource.createUsuario("Bruno2", "030.813.361-71", "bruno@teste.com", "32618888", "senhateste");
		}
		
		datasource.close();
	}
	
	public static Boolean verificaUsuario(String login, String senha, LoginActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		
		Usuario usuario = datasource.findUsuarioValido(login, senha);
		datasource.close();
		
		if(usuario != null ){
			return true;
		} else {
			return false;
		}
		
	}
	
	
}

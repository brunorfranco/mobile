package com.notiufg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.database.Cursor;

import com.notiufg.activity.LoginActivity;
import com.notiufg.activity.MainActivity;
import com.notiufg.dao.DBAdapterCurso;
import com.notiufg.dao.DBAdapterGrupoEnvio;
import com.notiufg.dao.DBAdapterNotificacao;
import com.notiufg.dao.DBAdapterUsuario;
import com.notiufg.entity.Curso;
import com.notiufg.entity.GrupoEnvio;
import com.notiufg.entity.Notificacao;
import com.notiufg.entity.Usuario;

public class CargaBancoDados {

	public static void carregaNotificacoesIniciais(MainActivity mainActivity){
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(mainActivity); 
		datasource.open();

		limpaTabelaNotificacao(datasource);
		carregaDadosTabelaNotificacao(datasource, mainActivity);
		
		datasource.close();
	}
	
	public static void carregaGrupoEnvio(MainActivity mainActivity){
		DBAdapterGrupoEnvio datasource = new DBAdapterGrupoEnvio(mainActivity); 
		datasource.open();
		
		limpaDadosGrupoEnvio(datasource);
		carregaDadosGrupoEnvioReal(datasource);
		
		datasource.close();
	}
	
	public static void carregaCursosIniciais(MainActivity mainActivity){
		DBAdapterCurso datasource = new DBAdapterCurso(mainActivity); 
		datasource.open();

//		limpaTabelaCursos(datasource);
		carregaDadosTabelaCurso(datasource);
		
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
//		datasource.dropTable();
//		datasource.deleteFromTable();
		datasource.open();
		datasource.atualizaTabela();
		Cursor cursor = datasource.getNotificacoes();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Notificacao noti = datasource.cursorToNotificacao(cursor);
			datasource.deletaNotificacao(noti.getId());
		    cursor.moveToNext();
		}
	}
	
	private static void limpaTabelaCursos(DBAdapterCurso datasource){
//		datasource.dropTable();
//		datasource.deleteFromTable();
		datasource.open();
//		datasource.atualizaTabela();
		Cursor cursor = datasource.getCursos();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Curso curso = datasource.cursorToCurso(cursor);
			datasource.deletaCurso(Long.valueOf(curso.getId()));
		    cursor.moveToNext();
		}
	}
	
	private static void limpaDadosGrupoEnvio(DBAdapterGrupoEnvio datasource){
		datasource.open();
//		datasource.atualizaTabela();
		Cursor cursor = datasource.getGruposEnvio();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			GrupoEnvio grupo = datasource.cursorToGrupoEnvio(cursor);
			datasource.deletaGrupoEnvio(grupo.getId());
		    cursor.moveToNext();
		}
	}
	
	private static void carregaDadosTabelaNotificacao(DBAdapterNotificacao datasource, MainActivity mainActivity){
		for (int i = 0; i < 20; i++) {
			String texto = "www.google.com.br Texto Teste Texto Teste Texto Teste "
					+ "Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto "
					+ "Teste Texto Teste Texto Teste" + i;
			Integer idGrupoEnvio = 1 + (int)(Math.random() * ((11 - 1) + 1));
			
			DBAdapterGrupoEnvio datasourceGrupo = new DBAdapterGrupoEnvio(mainActivity); 
			datasourceGrupo.open();
			GrupoEnvio grupo = datasourceGrupo.getGrupoEnvio(idGrupoEnvio);
			
			datasource.createNotificacao(grupo.getNomeGrupoEnvio(), texto, getDateTime(), idGrupoEnvio, 0);
		}
	}
	
	private static void carregaDadosGrupoEnvioRandomico(DBAdapterGrupoEnvio datasource){
//		datasource.atualizaTabela();
		for (int i = 0; i < 5; i++) {
			String nome = "Disciplina" + i;
			Random rand=new Random();
			int isPublico = rand.nextInt(1);
			datasource.createGrupoEnvio(nome, "S",isPublico);
		}
	}
	
	private static void carregaDadosGrupoEnvioReal(DBAdapterGrupoEnvio datasource){
//		datasource.atualizaTabela();
		//UFG
		datasource.createGrupoEnvio("Reitoria", "S",1);
		datasource.createGrupoEnvio("Pro Reitoria", "S",1);
		datasource.createGrupoEnvio("Biblioteca", "S",1);
		
		//CURSO
		datasource.createGrupoEnvio("Coordenador de Curso", "S",0);
		datasource.createGrupoEnvio("Direcao de unidade", "S",0);
		
		//DISCIPLINAS
		datasource.createGrupoEnvio("Matematica Discreta", "S",0);
		datasource.createGrupoEnvio("Persistencia", "S",0);
		datasource.createGrupoEnvio("Desenvolvimento para Web", "S",0);
		datasource.createGrupoEnvio("Sistemas Operacionais", "S",0);
		datasource.createGrupoEnvio("Engenharia de Software", "S",0);
		datasource.createGrupoEnvio("Integracao", "S",0);
	}
	
	private static void carregaDadosTabelaCurso(DBAdapterCurso datasource){
//		datasource.atualizaTabela();
//		datasource.createTable();
		datasource.createCurso("Engenharia de Software");
		datasource.createCurso("Ciencia da Computacao");
		datasource.createCurso("Letras");
		datasource.createCurso("Engenharia Civil");
	}
	
	public static void insereUsuarioPadrao(MainActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		datasource.atualizaTabela();
		
		Cursor cursor = datasource.getUsuarios();
		cursor.moveToFirst();
		if (cursor.isAfterLast() != false) {
			datasource.createUsuario("Bruno", "030.813.361-71", "bruno@teste.com", 
					"32618888", "senhateste", "071750", 1l);
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
	
	public static Usuario findUsuario(String login, String senha, LoginActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
		
		Usuario usuario = datasource.findUsuarioValido(login, senha);
		datasource.close();
		
		return usuario;
		
	}
	
	
}

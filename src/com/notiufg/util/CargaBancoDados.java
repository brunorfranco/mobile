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

		limpaTabelaCursos(datasource);
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
		datasource.atualizaTabela();
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
		datasource.atualizaTabela();
		Cursor cursor = datasource.getGruposEnvio();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			GrupoEnvio grupo = datasource.cursorToGrupoEnvio(cursor);
			datasource.deletaGrupoEnvio(grupo.getId());
		    cursor.moveToNext();
		}
	}
	
	private static void carregaDadosTabelaNotificacao(DBAdapterNotificacao datasource, MainActivity mainActivity){
		
		DBAdapterGrupoEnvio datasourceGrupo = new DBAdapterGrupoEnvio(mainActivity); 
		datasourceGrupo.open();
		for (int i = 0; i < 100; i++) {
			String texto = "www.google.com.br Texto Teste Texto Teste Texto Teste "
					+ "Texto Teste Texto Teste Texto Teste Texto Teste Texto Teste Texto "
					+ "Teste Texto Teste Texto Teste" + i;
			Integer idGrupoEnvio = 1 + (int)(Math.random() * ((20 - 0) + 1));
			
			GrupoEnvio grupo = datasourceGrupo.getGrupoEnvio(idGrupoEnvio);
			
			datasource.createNotificacao(grupo.getNomeGrupoEnvio(), texto, getDateTime(), idGrupoEnvio, 0);
		}
		datasource.close();
	}
	
	private static void carregaDadosGrupoEnvioRandomico(DBAdapterGrupoEnvio datasource){
//		datasource.atualizaTabela();
		for (int i = 0; i < 5; i++) {
			String nome = "Disciplina" + i;
			Random rand=new Random();
			int isPublico = rand.nextInt(1);
			datasource.createGrupoEnvio(nome, "S",isPublico, 0l);
		}
	}
	
	private static void carregaDadosGrupoEnvioReal(DBAdapterGrupoEnvio datasource){
//		datasource.atualizaTabela();
		//UFG
		datasource.createGrupoEnvio("Reitoria", "S",1, 0l);
		datasource.createGrupoEnvio("Pro Reitoria", "S",1, 0l);
		datasource.createGrupoEnvio("Biblioteca", "S",1, 0l);
		
		//CURSO
		datasource.createGrupoEnvio("Coordenador de Curso", "S",0, 0l);
		datasource.createGrupoEnvio("Direcao de unidade", "S",0,0l);
		
		//DISCIPLINAS ENG SOFTWARE
		datasource.createGrupoEnvio("Matematica Discreta", "S",0, 1l);
		datasource.createGrupoEnvio("Persistencia", "S",0,1l);
		datasource.createGrupoEnvio("Desenvolvimento para Web", "S",0,1l);
		datasource.createGrupoEnvio("Sistemas Operacionais", "S",0,1l);
		datasource.createGrupoEnvio("Engenharia de Software", "S",0,1l);
		datasource.createGrupoEnvio("Integracao", "S",0,1l);
		
		//DISCIPLINAS CIENCIA COMPUTACAO
		datasource.createGrupoEnvio("Calculo CP1", "S",0,2l);
		datasource.createGrupoEnvio("Compiladores", "S",0,2l);
		datasource.createGrupoEnvio("Ciencia Comp1", "S",0,2l);
		datasource.createGrupoEnvio("Ciencia Comp2", "S",0,2l);
		
		//DISCIPLINAS LETRAS
		datasource.createGrupoEnvio("Letras1", "S",0,3l);
		datasource.createGrupoEnvio("Letras2", "S",0,3l);
		datasource.createGrupoEnvio("Letras3", "S",0,3l);
		
		//DISCIPLINAS ENG CIVIL
		datasource.createGrupoEnvio("Fisica1", "S",0,4l);
		datasource.createGrupoEnvio("Desenho Tecnico", "S",0,4l);
		datasource.createGrupoEnvio("Calculo2", "S",0,4l);
	}
	
	private static void carregaDadosTabelaCurso(DBAdapterCurso datasource){
//		datasource.atualizaTabela();
//		datasource.createTable();
		datasource.createCurso("Engenharia de Software", 1);
		datasource.createCurso("Ciencia da Computacao", 2);
		datasource.createCurso("Letras", 3);
		datasource.createCurso("Engenharia Civil", 4);
	}
	
	public static void insereUsuarioPadrao(MainActivity mainActivity){
		DBAdapterUsuario datasource = new DBAdapterUsuario(mainActivity); 
		datasource.open();
//		datasource.atualizaTabela();
		
		Cursor cursor = datasource.getUsuarios();
		cursor.moveToFirst();
		//limpa tabela de usuarios
//		while (cursor.isAfterLast() == false) {
//			Usuario usuario = datasource.cursorToUsuario(cursor);
//			datasource.deletaUsuario(Long.valueOf(usuario.getId()));
//		    cursor.moveToNext();
//		}
		
		if (cursor.isAfterLast() != false) {
			datasource.createUsuario("Bruno", "030.813.361-71", "bruno@teste.com", 
					"32618888", "senhateste", "071750", new Long(1));
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

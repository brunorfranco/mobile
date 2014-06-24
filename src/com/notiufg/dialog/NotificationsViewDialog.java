package com.notiufg.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.example.notiufg.R;
import com.notiufg.activity.ListNotificacaoActivity;
import com.notiufg.dao.DBAdapterConfiguracao;
import com.notiufg.entity.Configuracao;
import com.notiufg.util.VariaveisGlobais;

public class NotificationsViewDialog extends DialogFragment {
	
	ListNotificacaoActivity listActivity;
	
	public ListNotificacaoActivity getListActivity() {
		return listActivity;
	}

	public void setListActivity(ListNotificacaoActivity listActivity) {
		this.listActivity = listActivity;
	}

	//switchs publicos 
	Switch reitoriaView;
	Switch proReitoriaView;
	Switch bibliotecaView;
	Switch coordenadorCursoView;
	Switch direcaoUnidadeView;
	
	//switchs especificos
	Switch switch1;
	Switch switch2;
	Switch switch3;
	Switch switch4;
	Switch switch5;
	Switch switch6;

	Context mContext;

	public static NotificationsViewDialog newInstance() {
		return new NotificationsViewDialog();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(VariaveisGlobais.usuarioLogado == null){
			getDialog().setTitle(R.string.title_fragment_notifications_view);

			View view = inflater.inflate(R.layout.fragment_usuario_deslogado,
					container, true);
			return view;
		} else {
			getDialog().setTitle(R.string.title_fragment_notifications_view);
	
			View view = inflater.inflate(R.layout.fragment_notifications_dialog,
					container, true);
			
			reitoriaView = (Switch) view.findViewById(R.id.reitoriaView);
			proReitoriaView = (Switch) view.findViewById(R.id.proReitoriaView);
			bibliotecaView = (Switch) view.findViewById(R.id.bibliotecaView);
			coordenadorCursoView = (Switch) view.findViewById(R.id.coordenadorCursoView);
			direcaoUnidadeView = (Switch) view.findViewById(R.id.direcaoUnidadeView);
			
			switch1 = (Switch) view.findViewById(R.id.switch1);
			switch2 = (Switch) view.findViewById(R.id.switch2);
			switch3 = (Switch) view.findViewById(R.id.switch3);
			switch4 = (Switch) view.findViewById(R.id.switch4);
			switch5 = (Switch) view.findViewById(R.id.switch5);
			switch6 = (Switch) view.findViewById(R.id.switch6);
			
			
			DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
			datasourceConfig.open();
			Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
			String listaGruposStr = "";
			if(cursorListaGruposEnvioUsuario != null){
				cursorListaGruposEnvioUsuario.moveToFirst();
				while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
					Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
					listaGruposStr = conf.getIdsGruposEnvio();
					cursorListaGruposEnvioUsuario.moveToNext();
				}
			}
			String[] arrayGrupos = new String[20];
			if(!listaGruposStr.isEmpty()){
				arrayGrupos = listaGruposStr.split(";");
			}	
			
			Integer[] arrayGruposInt = new Integer[20];
			if(arrayGrupos != null && arrayGrupos[0] != null){
				for (int i = 0; i < arrayGrupos.length; i++) {
					arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
				}
			}
			
			for (int i = 0; i < arrayGruposInt.length; i++) {
				if(arrayGruposInt[i] == null){
					break;
				}
				//switchs publics
				if(arrayGruposInt[i] == 1){
					reitoriaView.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 2){
					proReitoriaView.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 3){
					bibliotecaView.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 4){
					coordenadorCursoView.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 5){
					direcaoUnidadeView.setChecked(true);
					continue;
				}
				
				//switchs especificos
				if(arrayGruposInt[i] == 6){
					switch1.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 7){
					switch2.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 8){
					switch3.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 9){
					switch4.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 10){
					switch5.setChecked(true);
					continue;
				}
				if(arrayGruposInt[i] == 11){
					switch6.setChecked(true);
					continue;
				}
				
			}
			
			reitoriaView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "1;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 1){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			proReitoriaView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "2;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 2){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			bibliotecaView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "3;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 3){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			coordenadorCursoView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "4;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 4){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			direcaoUnidadeView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "5;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 5){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "6;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 6){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "7;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 7){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "8;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 8){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "9;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 9){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "10;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 10){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			switch6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				   @Override
				   public void onCheckedChanged(CompoundButton buttonView,
				     boolean isChecked) {
				 
				    if(isChecked){
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						listaGruposStr += "11;";
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), listaGruposStr);
						datasourceConfig.close();
				    }else{
				    	DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(listActivity); 
						datasourceConfig.open();
						Cursor cursorListaGruposEnvioUsuario = datasourceConfig.getConfiguracoesBydIdUsuario(VariaveisGlobais.usuarioLogado.getId());
						String listaGruposStr = "";
						if(cursorListaGruposEnvioUsuario != null){
							cursorListaGruposEnvioUsuario.moveToFirst();
							while (cursorListaGruposEnvioUsuario.isAfterLast() == false) {
								Configuracao conf = datasourceConfig.cursorToConfiguracao(cursorListaGruposEnvioUsuario);
								listaGruposStr = conf.getIdsGruposEnvio();
								cursorListaGruposEnvioUsuario.moveToNext();
							}
						}
						
						String[] arrayGrupos = new String[20];
						if(!listaGruposStr.isEmpty()){
							arrayGrupos = listaGruposStr.split(";");
						}
						
						Integer[] arrayGruposInt = new Integer[20];
						if(arrayGrupos != null && arrayGrupos[0] != null){
							for (int i = 0; i < arrayGrupos.length; i++) {
								if(Integer.parseInt(arrayGrupos[i]) == 11){
									continue;
								}
								arrayGruposInt[i] = Integer.parseInt(arrayGrupos[i]);
							}
						}
						String lista = "";
						for (int i = 0; i < arrayGruposInt.length; i++) {
							if(arrayGruposInt[i] == null){
								continue;
							}
							lista += arrayGruposInt[i] + ";";
						}
						
						datasourceConfig.updateConfiguracao(VariaveisGlobais.usuarioLogado.getId(), lista);
						datasourceConfig.close();
				    }
				   }
				  });
			
			mContext = view.getContext();
			return view;
		}
		
	}

	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Toast.makeText(mContext, buttonView.getText(), Toast.LENGTH_SHORT)
					.show();
		}
	};
	
}
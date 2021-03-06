package com.notiufg.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notiufg.R;
import com.notiufg.adapter.NotificacaoArrayAdapter;
import com.notiufg.dao.DBAdapterConfiguracao;
import com.notiufg.dao.DBAdapterNotificacao;
import com.notiufg.dialog.NotificationsViewDialog;
import com.notiufg.entity.Configuracao;
import com.notiufg.entity.Notificacao;
import com.notiufg.util.VariaveisGlobais;

public class ListNotificacaoActivity extends ListActivity {

	static Boolean orderByData = false;
	ListNotificacaoActivity lista;
	public ListNotificacaoActivity() {
		super();
		lista = this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Long[] ids = new Long[100] ;
		String[] nomesArray = new String[100] ;
		String[] textosArray = new String[100] ;
		String[] datasArray = new String[100] ;
		
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(this); 
		datasource.open();
		
		Cursor cursor = null;
		if(VariaveisGlobais.usuarioLogado == null){
			cursor = datasource.getNotificacoesPublicas(orderByData);
			Toast.makeText(this, "Usuario nao logado, apenas notificacoes publicas!", Toast.LENGTH_LONG).show();
		
			if(orderByData.booleanValue() == true){
				Toast.makeText(this, "Ordenado por Data Crescente!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Nao Ordenado!", Toast.LENGTH_LONG).show();
			}
		} else {
			DBAdapterConfiguracao datasourceConfig = new DBAdapterConfiguracao(this); 
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
			
			if(arrayGrupos[0] == null){
				Toast.makeText(this, "Nao ha notificacoes para serem exibidas!", Toast.LENGTH_LONG).show();
				return;
			}
			
			cursor = datasource.getNotificacoesEspecificas(VariaveisGlobais.usuarioLogado.getIdCurso(), arrayGrupos, orderByData);
			Toast.makeText(this, "Usuario logado, notificacoes publicas e do curso especifico!", Toast.LENGTH_LONG).show();
			if(orderByData.booleanValue() == true){
				Toast.makeText(this, "Ordenado por Data Crescente!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Nao Ordenado!", Toast.LENGTH_LONG).show();
			}
		}
		
		cursor.moveToFirst();
		int i = 0;
		while (cursor.isAfterLast() == false) {
			Notificacao noti = datasource.cursorToNotificacao(cursor);
			ids[i] = noti.getId();
			nomesArray[i] = noti.getNomeRemetente();
			textosArray[i] = noti.getTexto();
			datasArray[i] = noti.getDataEnvio();
		    cursor.moveToNext();
		    i++;
		}
		if(ids[0] == null){
			datasource.close();
			Toast.makeText(this, "Nao ha notificacoes!", Toast.LENGTH_LONG).show();
			return;
		}
		
		setListAdapter(new NotificacaoArrayAdapter(this, ids, nomesArray, textosArray, datasArray));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				DBAdapterNotificacao datasource = new DBAdapterNotificacao(lista); 
				datasource.open();
				datasource.marcaComoLida(Long.valueOf(view.getId()));
				datasource.close();
				VariaveisGlobais.idNotificacaoSelecionada = Long.valueOf(view.getId());
				Intent intent = new Intent(lista, SingleNotificationActivity.class);
			    startActivity(intent);
			    
			}
		});
		
		datasource.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		
		MenuItem item = menu.findItem(R.id.action_config);
		
		if (item == null)
		    return true;
		
		item.setOnMenuItemClickListener
		(
		    new MenuItem.OnMenuItemClickListener () 
		    { 
		        public boolean onMenuItemClick(MenuItem item) {
		        	NotificationsViewDialog notificationDialog = NotificationsViewDialog.newInstance();
		        	notificationDialog.setListActivity(lista);
		    		notificationDialog.show(getFragmentManager(), "dialog");
		        	return true;
		        }
		    } 
		); 
		
		MenuItem itemRefresh = menu.findItem(R.id.action_refresh);
		
		if (itemRefresh == null)
		    return true;
		
		itemRefresh.setOnMenuItemClickListener
		(
		    new MenuItem.OnMenuItemClickListener () 
		    { 
		        public boolean onMenuItemClick(MenuItem item) {
		        	Intent intent = new Intent(lista, ListNotificacaoActivity.class);
		    	    startActivity(intent);
		        	return true;
		        }
		    } 
		); 
		
		MenuItem itemOrder = menu.findItem(R.id.action_order);
		
		if (itemOrder == null)
		    return true;
		
		itemOrder.setOnMenuItemClickListener
		(
		    new MenuItem.OnMenuItemClickListener () 
		    { 
		        public boolean onMenuItemClick(MenuItem item) {
		        	if(orderByData == true){
		        		orderByData = false;
		        	} else {
		        		orderByData = true;
		        	}
		        	Intent intent = new Intent(lista, ListNotificacaoActivity.class);
		    	    startActivity(intent);
		        	return true;
		        }
		    } 
		); 
		
		return true;
	}
	
}

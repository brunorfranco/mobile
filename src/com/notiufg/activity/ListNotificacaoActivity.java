package com.notiufg.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.notiufg.adapter.NotificacaoArrayAdapter;
import com.notiufg.db.DBAdapterNotificacao;
import com.notiufg.entity.Notificacao;

public class ListNotificacaoActivity extends ListActivity {

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	String[] nomesArray = new String[20] ;
	String[] textosArray = new String[20] ;
	String[] datasArray = new String[20] ;
	
	DBAdapterNotificacao datasource = new DBAdapterNotificacao(this); 
	datasource.open();
	Cursor cursor = datasource.getNotificacoes();
	cursor.moveToFirst();
	int i = 0;
	while (cursor.isAfterLast() == false) {
		Notificacao noti = datasource.cursorToNotificacao(cursor);
		nomesArray[i] = noti.getNomeRemetente();
		textosArray[i] = noti.getTexto();
		datasArray[i] = noti.getDataEnvio();
	    cursor.moveToNext();
	    i++;
	}
	setListAdapter(new NotificacaoArrayAdapter(this, nomesArray, textosArray, datasArray));
	ListView listView = getListView();
	listView.setTextFilterEnabled(true);

	listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
		    // When clicked, show a toast with the TextView text
		    Toast.makeText(getApplicationContext(),
			((TextView) view).getText(), Toast.LENGTH_SHORT).show();
		}
	});

	}
}

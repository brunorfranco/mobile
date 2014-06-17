package com.notiufg.activity;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notiufg.R;
import com.notiufg.adapter.NotificacaoArrayAdapter;
import com.notiufg.db.DBAdapterNotificacao;
import com.notiufg.dialog.NotificationsViewDialog;
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
		        	DialogFragment notificationDialog = NotificationsViewDialog.newInstance();
		    		notificationDialog.show(getFragmentManager(), "dialog");
		        	return true;
		        }
		    } 
		); 
		
		return true;
	}
	
	public void onMenuItemClick(MenuItem item) {
    	DialogFragment notificationDialog = NotificationsViewDialog.newInstance();
		notificationDialog.show(getFragmentManager(), "dialog");
    }
}

package com.notiufg.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;

import com.example.notiufg.R;
import com.notify.db.DBAdapterNotificacao;

public class NotificacaoActivity extends ListActivity {

	ListAdapter adapter; 
    DBAdapterNotificacao datasource; 
   @Override 
    public void onCreate(Bundle savedInstanceState) { 
              super.onCreate(savedInstanceState); 
              setContentView(R.layout.main_list_notificacao); 
              datasource = new DBAdapterNotificacao(this); 
//              datasource.open(); 
              Cursor cursor = datasource.getNotificacoes(); 
              String[] columns = new String[] { "nomeRemetente","texto" }; 
              int[] to = new int[] { R.id.nomeRemetente, R.id.texto}; 
              adapter = new SimpleCursorAdapter(this, R.layout.notificacao_list_item, 
                                cursor, 
                                columns, 
                                to); 
                                this.setListAdapter(adapter); 
//              datasource.close(); 
     } 
}

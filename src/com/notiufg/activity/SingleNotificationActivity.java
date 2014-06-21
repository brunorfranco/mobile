package com.notiufg.activity;

import com.example.notiufg.R;
import com.example.notiufg.R.id;
import com.example.notiufg.R.layout;
import com.example.notiufg.R.menu;
import com.notiufg.dao.DBAdapterNotificacao;
import com.notiufg.entity.Notificacao;
import com.notiufg.util.VariaveisGlobais;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class SingleNotificationActivity extends Activity {

	static SingleNotificationActivity activity;
	
	public SingleNotificationActivity() {
		super();
		activity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_notification);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_notification, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_single_notification, container, false);
			
			Long idNotificacaoSelecionada = VariaveisGlobais.idNotificacaoSelecionada;
			
			DBAdapterNotificacao datasource = new DBAdapterNotificacao(activity); 
			datasource.open();
			
			Notificacao notificacao = datasource.getNotificacao(idNotificacaoSelecionada);
			datasource.close();
			
			TextView textViewNotificacao = (TextView) rootView.findViewById(R.id.txtNotificacao);
			textViewNotificacao.setText(notificacao.getTexto());
			
			TextView textViewGrupo= (TextView) rootView.findViewById(R.id.txtGrupo);
			textViewGrupo.setText(notificacao.getNomeRemetente());
			textViewGrupo.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
			
			return rootView;
		}
	}
	
	public void excluir(View view) {
		Long idNotificacaoSelecionada = VariaveisGlobais.idNotificacaoSelecionada;
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(activity); 
		datasource.open();
		
		datasource.deletaNotificacao(idNotificacaoSelecionada);;
		datasource.close();
		
		Intent intent = new Intent(this, ListNotificacaoActivity.class);
	    startActivity(intent);
	}
	
	public void marcarComoNaoLida(View view) {
		Long idNotificacaoSelecionada = VariaveisGlobais.idNotificacaoSelecionada;
		DBAdapterNotificacao datasource = new DBAdapterNotificacao(activity); 
		datasource.open();
		
		datasource.marcaComoNaoLida(idNotificacaoSelecionada);;
		datasource.close();
		
		Intent intent = new Intent(this, ListNotificacaoActivity.class);
	    startActivity(intent);
	}

}

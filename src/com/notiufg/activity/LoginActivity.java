package com.notiufg.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notiufg.R;
import com.notiufg.util.ConectorBancoDados;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		
		TextView text = (TextView) findViewById(R.id.text1);
	    text.setTextColor(Color.BLUE);
	    
	    TextView text2 = (TextView) findViewById(R.id.text2);
	    text2.setTextColor(Color.BLUE);
	    
	    Button buttonEntrar = (Button) findViewById(R.id.buttonEntrar);
	    buttonEntrar.setTextColor(Color.BLUE);
	    
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main_actions, menu);
	 
	    return super.onCreateOptionsMenu(menu);
	    
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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}
	
	EditText loginText;
	EditText senhaText;
	public void validarLogin(View view) {
		
		loginText   = (EditText)findViewById(R.id.fname);
		senhaText   = (EditText)findViewById(R.id.fname2);
		String login = loginText.getText().toString();
		String senha = senhaText.getText().toString();
		
		Intent intent = null;
		if(ConectorBancoDados.verificaUsuario(login, senha, this)){
			intent = new Intent(this, ListNotificacaoActivity.class);
		} else {
			intent = new Intent(this, LoginActivity.class);
		}
		
	    startActivity(intent);
	}
	

}

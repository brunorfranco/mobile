package com.notiufg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.notiufg.R;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		 MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.activity_main_actions, menu);
	     
	     return super.onCreateOptionsMenu(menu);
	}

	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		    case R.id.action_config:
		    	Intent intent = new Intent(this, ConfiguracaoActivity.class);
			    startActivity(intent);
		      break;
	    default:
	    	break;
	    }

	    return true;
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
			View rootView = inflater.inflate(R.layout.fragment_inicio, container,
					false);
			return rootView;
		}
	}
	
	public void login(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
	    startActivity(intent);
	}
	
	public void cadastrar(View view) {
		Intent intent = new Intent(this, NovoUsuarioActivity.class);
	    startActivity(intent);
	}

}

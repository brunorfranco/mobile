package com.notiufg.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.notiufg.R;
import com.notiufg.dao.DBAdapterConfiguracao;
import com.notiufg.dao.DBAdapterUsuario;
import com.notiufg.entity.GrupoEnvio;
import com.notiufg.entity.Usuario;
import com.notiufg.listener.CustomOnItemSelectedListener;

public class NovoUsuarioActivity extends ActionBarActivity {

	private Spinner spinner1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_usuario);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
//		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novo_usuario, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_novo_usuario,
					container, false);
			return rootView;
		}
	}
	
	public void cadastrar(View view) {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		String curso = String.valueOf(spinner1.getSelectedItem());
		
		EditText nome   = (EditText)findViewById(R.id.ednome);
		EditText cpf   = (EditText)findViewById(R.id.edcpf);
		EditText email   = (EditText)findViewById(R.id.edEmail);
		EditText telefone   = (EditText)findViewById(R.id.edTelefone);
		EditText matricula   = (EditText)findViewById(R.id.edMatricula);
		EditText senha   = (EditText)findViewById(R.id.edSenha);
		EditText senhaConfirmada   = (EditText)findViewById(R.id.edConfirmarSenha);
		
		String nomeStr = nome.getText().toString();
		String cpfStr = cpf.getText().toString();
		String emailStr = email.getText().toString();
		String telefoneStr = telefone.getText().toString();
		String matriculaStr = matricula.getText().toString();
		String senhaStr = senha.getText().toString();
		String senhaConfirmadaStr = senhaConfirmada.getText().toString();
		
		Long idCurso = getIdCurso(curso);
		
		
		//validarSenha
		if(!senhaStr.equalsIgnoreCase(senhaConfirmadaStr)){
			Toast.makeText(this, "Senhas diferentes, confira as senhas.", Toast.LENGTH_LONG).show();
			return;
		}
		
		DBAdapterUsuario datasource = new DBAdapterUsuario(this); 
		datasource.open();
		Usuario usuarioNovo = datasource.createUsuario(nomeStr, cpfStr, emailStr, 
				telefoneStr, senhaStr, matriculaStr, idCurso);
		datasource.close();
		
		Toast.makeText(this, "Usuario criado com sucesso!", Toast.LENGTH_LONG).show();
		
		//cria configuracoes basicas para o usuario, grupos de envio publico e grupos de materias do curso em questao
		DBAdapterConfiguracao datasourceConf = new DBAdapterConfiguracao(this); 
		datasourceConf.open();
		String gruposEnvio = "1;2;3;4;5"; // grupos publicos
		if(idCurso.intValue() == 1){
			gruposEnvio += ";6;7;8;9;10;11";
		} else if(idCurso.intValue() == 2){
			gruposEnvio += ";12;13;14;15";
		} else if(idCurso.intValue() == 3){
			gruposEnvio += ";16;17;18";
		} else if(idCurso.intValue() == 4){
			gruposEnvio += ";19;20;21";
		}
		
		datasourceConf.createConfiguracao(usuarioNovo.getId(), gruposEnvio); //combinacao de gruposEnvio publicos + eng de software
		datasourceConf.close();
		
//		limparCampos
		nome.setText("");
		cpf.setText("");
		email.setText("");
		telefone.setText("");
		matricula.setText("");
		senha.setText("");
		senhaConfirmada.setText("");
	}
	
	private Long getIdCurso(String curso){
		if(curso.contains("Engenharia de Software")){
			return 1l;
		} else if (curso.contains("Ciencia da Computacao")){
			return 2l;
		} else if (curso.contains("Letras")){
			return 3l;
		} else if (curso.contains("Engenharia Civil")){
			return 4l;
		} else
			return null;
	}
	
}

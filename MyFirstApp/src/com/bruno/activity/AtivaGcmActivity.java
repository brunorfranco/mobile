package com.bruno.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bruno.configuracao.GCM;
import com.example.myfirstapp.R;

public class AtivaGcmActivity extends Activity {
    
    private Button botaoAtivarDesativar;
    private boolean gcmAtivo;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativa_gcm);
        botaoAtivarDesativar = (Button) findViewById(R.id.botao_ativar_desativar);
        /**
         *  Verifica se o GCM est� ativo ou n�o para definir a Label 
         *  do bot�o na tela
         */
        gcmAtivo = GCM.isAtivo(getApplicationContext());
        // Define a Label do bot�o
        defineLabelBotao();
    }
     
    /**
     * M�todo que ir� ativar/desativar o servi�o GCM de acordo 
     * com o seu status atual. Se o GCM estiver desabilitado, este 
     * m�todo ir� ativ�-lo, ou vice-versa.
     * 
     * @param view
     */
    public void ativaDesativaGCM(View view) {
        if (GCM.isAtivo(getApplicationContext())) {
            GCM.desativa(getApplicationContext());
            gcmAtivo = false;
            Toast.makeText(getApplicationContext(), "GCM desativado!", Toast.LENGTH_LONG).show();
        } else {
            GCM.ativa(getApplicationContext());
            gcmAtivo = true;
            Toast.makeText(getApplicationContext(), "GCM ativado!", Toast.LENGTH_LONG).show();
        }
        defineLabelBotao();
    }
     
    /**
     * M�todo que ir� definir a Label do bot�o da tela, de
     * acordo com a seguinte regra:
     * 1. se o GCM estiver ativo, a label ser�: Desativar
     * 2. se o GCM N�O estiver ativo, a label ser�: Ativar
     */
    private void defineLabelBotao() {
        if (gcmAtivo) {
            botaoAtivarDesativar.setText("Desativar");
        } else {
            botaoAtivarDesativar.setText("Ativar");
        }
    }
 
}
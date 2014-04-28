package com.bruno.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MostraMensagemActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Definimos uma TextView para mostrar a mensagem na tela
        TextView texto = new TextView(getApplicationContext());
        // Define como texto da TextView a mensagem recebida do GCM
        texto.setText(getIntent().getStringExtra("mensagem_recebida"));
        // Ajusta tamanho e cor da fonte
        texto.setTextSize(20.0F);
        texto.setTextColor(Color.BLACK);
        /*
         * Para tornar as coisas mais simples, mostraremos apenas uma TextView
         * na tela com o conte�do da mensagem recebida da Nuvem atrav�s do GCM.
         */
        setContentView(texto);
    }
 
}
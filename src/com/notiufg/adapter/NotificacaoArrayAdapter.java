package com.notiufg.adapter;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.notiufg.R;
import com.notiufg.dao.DBAdapterNotificacao;
import com.notiufg.entity.Notificacao;
import com.notiufg.util.VariaveisGlobais;

public class NotificacaoArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final Long[] ids;
	private final String[] nomesArray;
	private final String[] textosArray;
	private final String[] datasArray;
 
	public NotificacaoArrayAdapter(Context context, Long[] ids, String[] nomes, String[] textos, String[] datas) {
		super(context, R.layout.list_notificacao, nomes);
		this.context = context;
		this.ids = ids;
		this.nomesArray = nomes;
		this.textosArray = textos;
		this.datasArray = datas;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_notificacao, parent, false);
		TextView textViewNome = (TextView) rowView.findViewById(R.id.nome);
		TextView textViewTexto = (TextView) rowView.findViewById(R.id.texto);
		TextView textViewData = (TextView) rowView.findViewById(R.id.data);
		textViewNome.setText(nomesArray[position]);
		textViewNome.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
		textViewTexto.setText(textosArray[position]);
		textViewData.setText(datasArray[position]);
		textViewData.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
		
		int anInt = new BigDecimal(ids[position]).intValueExact();
		rowView.setId(anInt);
		
		if(VariaveisGlobais.usuarioLogado != null){
			DBAdapterNotificacao datasource = new DBAdapterNotificacao(context); 
			datasource.open();
			Notificacao noti = datasource.getNotificacao(Long.valueOf(anInt));
			if(noti.getFoiLida() == 0){
				textViewTexto.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
			}
			datasource.close();
		}
		return rowView;
	}
}

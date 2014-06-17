package com.notiufg.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.example.notiufg.R;

public class NotificationsViewDialog extends DialogFragment {
	Switch switch1;
	Switch switch2;
	Switch switch3;
	Switch switch4;
	Switch switch5;
	Switch switch6;

	Context mContext;

	public static NotificationsViewDialog newInstance() {
		return new NotificationsViewDialog();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.title_fragment_notifications_view);

		View view = inflater.inflate(R.layout.fragment_notifications_dialog,
				container, true);

		switch1 = (Switch) view.findViewById(R.id.switch1);
		switch2 = (Switch) view.findViewById(R.id.switch2);
		switch3 = (Switch) view.findViewById(R.id.switch3);
		switch4 = (Switch) view.findViewById(R.id.switch4);
		switch5 = (Switch) view.findViewById(R.id.switch5);
		switch6 = (Switch) view.findViewById(R.id.switch6);

		mContext = view.getContext();
		
		return view;
	}

	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Toast.makeText(mContext, buttonView.getText(), Toast.LENGTH_SHORT)
					.show();
		}
	};
	
}
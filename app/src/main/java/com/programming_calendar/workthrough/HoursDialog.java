package com.programming_calendar.workthrough;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.SeekBar;
import android.widget.ArrayAdapter;
import android.content.Context;

import java.util.List;

public class HoursDialog extends Dialog
{
		LayoutInflater inflater;
		View layout;
		
		Spinner spinProg;
		final SeekBar sbarHours;
		final TextView nrHours;
		Button btnConfirm;
		
		ArrayAdapter<String> spinProgArrayAdapter;

		int sbarProgressRead;
		double hoursSelected;
		int colorSelected;
		String[] tasksArray;

		public void updateLists (double hoursSelected, int colorSelected, List themeColors, List hoursSpent){

			for (int i = 0; i < themeColors.size(); i++){
				if (themeColors.get(i).equals(colorSelected)) {
					hoursSpent.set(i,((Double) hoursSpent.get(i)).doubleValue() + hoursSelected );
				}
			}
		}
		
		public HoursDialog (Context context, List dayProgHoursList) {
			super(context);
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.hours_dialog, (ViewGroup) findViewById(R.id.hoursDialogRoot));
			this.setContentView(layout);

			spinProg = (Spinner) layout.findViewById(R.id.spinProg);
			sbarHours = (SeekBar) layout.findViewById(R.id.sbarHours);
			nrHours = (TextView) layout.findViewById(R.id.nrHours);
			btnConfirm = (Button) layout.findViewById(R.id.btnConfirm);

			tasksArray = context.getResources().getStringArray(R.array.programming_array);

			spinProgArrayAdapter =
			new ArrayAdapter<String>(context, 
									android.R.layout.simple_spinner_item,
									tasksArray);
			spinProgArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinProg.setAdapter(spinProgArrayAdapter);

			switch (spinProg.getSelectedItemPosition()){
				case 0:
					colorSelected = 0xff00a813;
					break;
				case 1:
					colorSelected = 0xffffdd66;
					break;
				case 2:
					colorSelected = 0xff7070ff;
					break;
					default:
						break;
			}

		sbarHours.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){
				
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){

			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
				sbarProgressRead = sbarHours.getProgress();
				hoursSelected = sbarProgressRead / 2.0;
				nrHours.setText("" + hoursSelected);
				updateLists(hoursSelected,colorSelected,themeColors,hoursSpent);
			}
		});
		
		btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				dismiss();
			}
		});
		
		show();
	} 
}
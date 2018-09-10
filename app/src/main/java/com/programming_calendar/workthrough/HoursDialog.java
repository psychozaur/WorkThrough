package com.programming_calendar.workthrough;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.SeekBar;
import android.widget.ArrayAdapter;
import android.widget.Toast;
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

		int index;

		Context mContext;
		String output;
		
		ColorBarButton mDay;
		ProgHoursDbHelper mDbHelper;

		int sbarProgressRead;
		double mTotalHours;
		double mProductiveHours;
		double mHoursSelected;
		double mHoursSeekBar;
		int mColorSelected;
		String mDateSelected;
		String mJobSelected;
		String[] tasksArray;

		ProgHours newEntry;
		List <ProgHours> mDayProgHoursList;

		/*
		is selected item in database?
			yes: modify existing item in list (only hours needed)
			no: create new item in list (date, color, job, hours needed)

		also, hours already put in a job need to be on a seekbar (input for hoursdialog!)
		 */

		private int generateColor(String jobSelected){
			int color;
			switch (jobSelected) {
				case "Android: kalendarz":
					color = 0xff7070ff;
					break;
				case "Git/GitHub":
					color = 0xffffdd66;
					break;
				case "Python":
					color = 0xff00a813;
					break;
				default:
					color = 0x00000000;
					break;
			}
			return color;
		}
		 
		public int getIndexAndSetData(String jobSelected){
			for (int i = 0; i < mDayProgHoursList.size(); i++) {
				if (jobSelected.equals(mDayProgHoursList.get(i).getJob())){
					mDateSelected = mDayProgHoursList.get(i).getDate();
					mColorSelected = mDayProgHoursList.get(i).getColor();
					mJobSelected = mDayProgHoursList.get(i).getJob();
					mHoursSelected = mDayProgHoursList.get(i).getHours();
					return i;
				}
			}
			mDateSelected = mDayProgHoursList.get(0).getDate();
			mColorSelected = generateColor(jobSelected);
			mJobSelected = jobSelected;
			return -1;
		}
		
		public void saveData (){
			mDayProgHoursList.get(index).setHours(mHoursSeekBar);
			mDayProgHoursList.get(0).setHours((sbarHours.getMax() / 2.0) - mHoursSeekBar);
			mDay.setProductiveHours(mTotalHours - ((sbarHours.getMax() / 2.0) - mHoursSeekBar));
			newEntry = mDayProgHoursList.get(index);
		}
		
		public void createData(){
			newEntry = new ProgHours(mDateSelected,
								mColorSelected,
								mJobSelected,
								mHoursSeekBar);
		}
		
		public void updateSeekBar (double newHours){
			sbarHours.setMax((int)(2 * mTotalHours) - (int)((mProductiveHours - newHours) * 2));
			sbarHours.setProgress((int)( 2 * newHours));
			nrHours.setText("" + newHours);
		}
/*
		public void updateLists (double hoursSelected, int colorSelected, List themeColors, List hoursSpent){

			for (int i = 0; i < themeColors.size(); i++){
				if (themeColors.get(i).equals(colorSelected)) {
					hoursSpent.set(i,((Double) hoursSpent.get(i)).doubleValue() + hoursSelected );
				}
			}
		}
*/
		public HoursDialog (Context context, ColorBarButton day, ProgHoursDbHelper dbHelper) {
			super(context);
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.hours_dialog, (ViewGroup) findViewById(R.id.hoursDialogRoot));
			this.setContentView(layout);

			mContext = context;
			mDay = day;
			mDbHelper = dbHelper;

			mDayProgHoursList = day.getDayProgHoursList();
			mProductiveHours = day.getProductiveHours();
			mTotalHours = day.getTotalHours();

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
/*
			output = "";
			index = getIndexAndSetData((String)spinProg.getSelectedItem());
			sbarHours.setMax((int)(2 * mTotalHours) - (int)((mProductiveHours - mHoursSelected) * 2));
			sbarHours.setProgress((int)( 2 * mHoursSelected));
			nrHours.setText("" + mHoursSelected);
			output = mJobSelected + ", " +
					mColorSelected + ", " +
					mDateSelected + ", " +
					mHoursSelected;
			Toast.makeText(mContext, output, Toast.LENGTH_SHORT).show();
*/
			spinProg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
			{
				public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
				{
						//parent.getItemAtPosition(pos);
						output = "";
						index = getIndexAndSetData((String)parent.getItemAtPosition(pos));
						if (index == -1){
							updateSeekBar(0.0);
						}
						else updateSeekBar(mHoursSelected);

						output = mJobSelected + ", " +
								mColorSelected + ", " +
								mDateSelected + ", " +
								mHoursSelected + ", " +
								index;

						//Toast.makeText(mContext, output, Toast.LENGTH_SHORT).show();

				}

				public void onNothingSelected(AdapterView<?> parent)
				{

				}
			});

/*
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
*/
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
				mHoursSeekBar = sbarProgressRead / 2.0;
				nrHours.setText("" + mHoursSeekBar);
				//Toast.makeText(mContext, "progress changed", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				if (index != -1){
					saveData();
				}
				if (index == -1){
					createData();
				}
				mDay.invalidate();
				mDbHelper.saveOrModifyData(newEntry);
				dismiss();
			}
		});
		
		show();
	} 
}

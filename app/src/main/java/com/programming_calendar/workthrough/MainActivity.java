package com.programming_calendar.workthrough;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends Activity 
{
   	TextView txt1;
   NextDate nextDate;
	LinearLayout weekOneLayout, weekTwoLayout,
	weekThreeLayout, weekFourLayout,
	weekFiveLayout, weekSixLayout;
	LinearLayout[] weeks;
	ColorBarButton[] days;
	ColorBarButton day;
	ProgHoursDbHelper dbHelper;
//	ProgHours progHours;
	//ColorBarDrawable[] colorBars;
	//ColorBarDrawable colorBar;
	int transparentColor = 0x00000000;
	int productiveHours = 0;
	int totalWorkHours = 12;
	String output =  "";

	List<Integer> colorList = new ArrayList<Integer>();
	List<Integer> hoursList = new ArrayList<Integer>();

	List<ProgHours> progHoursList;
	
	public void onDayClick(View v, ColorBarDrawable colorBar){

		final HoursDialog hoursDialog = new HoursDialog(this);
		v.setBackground(colorBar);
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

				colorList.add(transparentColor);
				hoursList.add(totalWorkHours);
		
		txt1 = (TextView) findViewById(R.id.txt1);
		
		nextDate = new NextDate();

		dbHelper = new ProgHoursDbHelper (this);
		progHoursList = dbHelper.getAllProgHours();
		
	//	progHours = progHoursList.get(2);

		txt1.setText(nextDate.dateToString() + "");
		
		weekOneLayout = (LinearLayout) findViewById(R.id.calendar_week_1);
		weekTwoLayout = (LinearLayout) findViewById(R.id.calendar_week_2);
		weekThreeLayout = (LinearLayout) findViewById(R.id.calendar_week_3);
		weekFourLayout = (LinearLayout) findViewById(R.id.calendar_week_4);
		weekFiveLayout = (LinearLayout) findViewById(R.id.calendar_week_5);
		weekSixLayout = (LinearLayout) findViewById(R.id.calendar_week_6);
		
		weeks = new LinearLayout[6];
		days = new ColorBarButton[6 * 7];
		//colorBars = new ColorBarDrawable [6 * 7];
		
		weeks[0] = weekOneLayout;
		weeks[1] = weekTwoLayout;
		weeks[2] = weekThreeLayout;
		weeks[3] = weekFourLayout;
		weeks[4] = weekFiveLayout;
		weeks[5] = weekSixLayout;
		
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.FILL_PARENT,
			ViewGroup.LayoutParams.FILL_PARENT);
		buttonParams.weight = 1;
		
		int daysArrayCount = 0;
		
		for (int weekNumber = 0; weekNumber < 6; ++weekNumber){
			for (int dayInWeek = 0; dayInWeek < 7; ++dayInWeek){

				productiveHours = dbHelper.countProductiveHours(nextDate.dateToString());
				hoursList.set(0,totalWorkHours - productiveHours);

				for (int i = 0; i < progHoursList.size(); i++){
			   	if (progHoursList.get(i).getDate().equals(nextDate.dateToString())){
						colorList.add(progHoursList.get(i).getColor());
						hoursList.add(progHoursList.get(i).getHours());

						if (colorList.size() == hoursList.size()) {
							output = "";
							for(int j = 0; j < colorList.size(); j++) {
								output += "(" + colorList.get(j);
								output += "," + hoursList.get(j) + ")*";
							} 
						//	Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
						} 

					}
				}

				day = new ColorBarButton(MainActivity.this, colorList,hoursList) ;
				//output = day.getString();
				//day.getLists(colorList,hoursList);

				day.setText(nextDate.getDayNumber());
				day.setTextColor(nextDate.whatTextColor());
				day.setBackgroundColor(nextDate.whatBackground());
				day.setLayoutParams(buttonParams);
				day.setSingleLine();
				day.setBackground(day.mDrawable);
				//colorBar = new ColorBarDrawable(colorList,hoursList);
				//Toast.makeText(this, "" + hoursList.get(hoursList.size() - 1), Toast.LENGTH_SHORT).show();
	
						day.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v){
						onDayClick(v,day.mDrawable );
						txt1.setText(output);
					}
				});

				//colorBars[daysArrayCount] = colorBar;
				days[daysArrayCount] = day;
				weeks[weekNumber].addView(day);

				if (!(colorList.isEmpty())) colorList.clear();
				if (!(hoursList.isEmpty())) hoursList.clear();

				colorList.add(transparentColor);
				hoursList.add(totalWorkHours);

				++daysArrayCount;
				nextDate.addOneDay();
			}
		}

    }
}
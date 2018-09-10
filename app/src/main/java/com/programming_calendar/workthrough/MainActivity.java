package com.programming_calendar.workthrough;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.ArrayList;

//import com.psychozaur.keeptrack.R;

public class MainActivity extends Activity 
{
   	TextView txt1;
	NextDate nextDate;
	LinearLayout weekOneLayout, weekTwoLayout,
	weekThreeLayout, weekFourLayout,
	weekFiveLayout, weekSixLayout;
	LinearLayout[] weeks;
	ColorBarButton[] days;
	int daysArrayCount;
	ColorBarButton day;
	
	ProgHoursDbHelper dbHelper;

	int transparentColor = 0x00000000;
	double productiveHours = 0;
	double totalWorkHours = 12;
	String output =  "";

	ProgHours idleTime;
	List<ProgHours> progHoursList;
	List<ProgHours> dayProgHoursList;

	public void onDayClick(View v, ColorBarButton day, ProgHoursDbHelper dbHelper){

		final HoursDialog hoursDialog = new HoursDialog(this, day, dbHelper);
		//v.setBackground(day.getDrawable());
	}

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		txt1 = (TextView) findViewById(R.id.txt1);

		nextDate = new NextDate();

		dbHelper = new ProgHoursDbHelper (this);
		//dbHelper.open();
		progHoursList = dbHelper.getAllProgHours();
		dayProgHoursList = new ArrayList<ProgHours>();

		txt1.setText(nextDate.dateToString() + "");

		weekOneLayout = (LinearLayout) findViewById(R.id.calendar_week_1);
		weekTwoLayout = (LinearLayout) findViewById(R.id.calendar_week_2);
		weekThreeLayout = (LinearLayout) findViewById(R.id.calendar_week_3);
		weekFourLayout = (LinearLayout) findViewById(R.id.calendar_week_4);
		weekFiveLayout = (LinearLayout) findViewById(R.id.calendar_week_5);
		weekSixLayout = (LinearLayout) findViewById(R.id.calendar_week_6);

		weeks = new LinearLayout[6];
		days = new ColorBarButton[6 * 7];

		weeks[0] = weekOneLayout;
		weeks[1] = weekTwoLayout;
		weeks[2] = weekThreeLayout;
		weeks[3] = weekFourLayout;
		weeks[4] = weekFiveLayout;
		weeks[5] = weekSixLayout;

		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.MATCH_PARENT);
		buttonParams.weight = 1;

		daysArrayCount = 0;

		for (int weekNumber = 0; weekNumber < 6; ++weekNumber){
			for (int dayInWeek = 0; dayInWeek < 7; ++dayInWeek){

				final int dayNumber = daysArrayCount;
				productiveHours = dbHelper.countProductiveHours(nextDate.dateToString());
				idleTime = new ProgHours (nextDate.dateToString(),
										  0x00000000,
										  "idle",
										  totalWorkHours - productiveHours);
				dayProgHoursList.add(idleTime);

				for (int i = 0; i < progHoursList.size(); i++){
					if (progHoursList.get(i).getDate().equals(nextDate.dateToString())){
						dayProgHoursList.add(progHoursList.get(i));

					}
				}

				day = new ColorBarButton(MainActivity.this, 
										 dayProgHoursList,
										 productiveHours, 
										 totalWorkHours);

				day.setText(nextDate.getDayNumber());
				day.setTextColor(nextDate.whatTextColor());
				day.setBackgroundColor(nextDate.whatBackground());
				day.setLayoutParams(buttonParams);
				day.setSingleLine();
				day.setBackground(day.mDrawable);

				day.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v){
							onDayClick(v, days[dayNumber], dbHelper);
						}
					});
				
				day.setIndex(daysArrayCount);
				days[daysArrayCount] = day;
				weeks[weekNumber].addView(day);

				if (!(dayProgHoursList.isEmpty())) dayProgHoursList.clear();

				++daysArrayCount;
				nextDate.addOneDay();
			}
		}

    }

	@Override
	protected void onPause()
	{
		super.onPause();
		dbHelper.close();
	}
	
}

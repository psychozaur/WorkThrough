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

public class MainActivity extends Activity
{
    TextView txt1;
    NextDate nextDate;
    LinearLayout weekOneLayout, weekTwoLayout,
            weekThreeLayout, weekFourLayout,
            weekFiveLayout, weekSixLayout;
    LinearLayout[] weeks;
    Button[] days;
    ProgHours progHours;

    List<ProgHours> progHoursList;

    public void onDayClick(View v, ColorBarDrawable colorBar){
/*
		String output = "";
		int[] tagInfo = (int[])v.getTag();
		for (int i = 0; i < 3; ++i){
			if (i == 1) output += ((tagInfo[i] + 1) + " ");
			else output += (tagInfo[i] + " ");
		}
		Toast.makeText(getApplicationContext(), output,Toast.LENGTH_SHORT).show();
		*/
        final HoursDialog hoursDialog = new HoursDialog(this);
        v.setBackground(colorBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txt1 = (TextView) findViewById(R.id.txt1);

        nextDate = new NextDate();

        ProgHoursDbHelper dbHelper = new ProgHoursDbHelper (this);
        progHoursList = dbHelper.getAllProgHours();

        progHours = progHoursList.get(2);

        txt1.setText(progHours.getColor() + "");

        weekOneLayout = (LinearLayout) findViewById(R.id.calendar_week_1);
        weekTwoLayout = (LinearLayout) findViewById(R.id.calendar_week_2);
        weekThreeLayout = (LinearLayout) findViewById(R.id.calendar_week_3);
        weekFourLayout = (LinearLayout) findViewById(R.id.calendar_week_4);
        weekFiveLayout = (LinearLayout) findViewById(R.id.calendar_week_5);
        weekSixLayout = (LinearLayout) findViewById(R.id.calendar_week_6);

        weeks = new LinearLayout[6];
        days = new Button[6 * 7];

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

        int[] colors = {Color.TRANSPARENT,0xff7070ff,0xffffdd66};
        int[] hours = {7,2,3};
        final ColorBarDrawable colorBar = new ColorBarDrawable(colors,hours);
        int daysArrayCount = 0;

        for (int weekNumber = 0; weekNumber < 6; ++weekNumber){
            for (int dayInWeek = 0; dayInWeek < 7; ++dayInWeek){
                final Button day = new Button(MainActivity.this);
                day.setText(nextDate.getDayNumber());
                day.setTextColor(nextDate.whatTextColor());
                day.setBackgroundColor(nextDate.whatBackground());
                day.setLayoutParams(buttonParams);
                day.setSingleLine();

                days[daysArrayCount] = day;
                weeks[weekNumber].addView(day);

                day.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        onDayClick(v, colorBar);
                    }
                });

                ++daysArrayCount;
                nextDate.addOneDay();
            }
        }

				/*
				int[] dateArr = new int[3];
				dateArr[0] = dayNumber;
				dateArr[1] = chosenMonth;
				dateArr[2] = chosenYear;
				days[i].setTag(dateArr);
				*/

    }
}
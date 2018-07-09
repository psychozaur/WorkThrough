package com.programming_calendar.workthrough;

import java.util.Calendar;
import android.graphics.Color;

public class NextDate
{
    private Calendar rightNow;

    private int dayOfWeekOfTheFirstDayOfMonth;
    private int daysSinceMonday;
    private int[] convertWeekDays;

    private int currentDayOfMonth;
    private int currentMonth;
    private int currentYear;

    private int chosenDayOfMonth;
    private int chosenMonth;
    private int chosenYear;

    private int buttonColor;
    private int buttonBackground;
    private String dayNumberString;

    public NextDate ()
    {
        this.rightNow = Calendar.getInstance ();

        this.currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
        this.currentMonth = rightNow.get(Calendar.MONTH);
        this.currentYear = rightNow.get(Calendar.YEAR);

        this.chosenDayOfMonth = currentDayOfMonth;
        this.chosenMonth = currentMonth;
        this.chosenYear = currentYear;

        this.convertWeekDays = new int[] {Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY};

        //set calendar to the 1st day of the month
        rightNow.set(currentYear,currentMonth,1);

        //get the day of week of the 1st day of this month
        this.dayOfWeekOfTheFirstDayOfMonth = rightNow.get(Calendar.DAY_OF_WEEK);
        this.daysSinceMonday = countDaysSinceMonday();

        //set calendar to Monday before the 1st (or do nothing if 1st was on Monday)
        rightNow.add(Calendar.DAY_OF_MONTH,-1*(daysSinceMonday));
        currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    public void addOneDay ()
    {
        rightNow.add(Calendar.DAY_OF_MONTH,1);
        currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    public int countDaysSinceMonday()
    {
        int result = 0;
        for (int i = 0; i < convertWeekDays.length; i++)
        {
            if (dayOfWeekOfTheFirstDayOfMonth == convertWeekDays[i]) result = i;
        }
        return result;
    }

    public int whatTextColor ()
    {
        if (currentMonth == rightNow.get(Calendar.MONTH))
        {
            buttonColor = Color.BLACK;
            if (chosenDayOfMonth == rightNow.get(Calendar.DAY_OF_MONTH) &&
                    chosenMonth == rightNow.get(Calendar.MONTH) &&
                    chosenYear == rightNow.get(Calendar.YEAR))
                buttonColor = Color.WHITE;
        }
        else
            buttonColor = Color.GRAY;
        return buttonColor;
    }

    public int whatBackground ()
    {
        if (chosenDayOfMonth == rightNow.get(Calendar.DAY_OF_MONTH) &&
                chosenMonth == rightNow.get(Calendar.MONTH) &&
                chosenYear == rightNow.get(Calendar.YEAR))
            buttonBackground = Color.GREEN;
        else
            buttonBackground = Color.TRANSPARENT;
        return buttonBackground;
    }

    public String getDayNumber ()
    {
        dayNumberString = "" + currentDayOfMonth;
        return dayNumberString;
    }

}
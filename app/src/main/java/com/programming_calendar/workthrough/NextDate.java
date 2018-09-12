package com.programming_calendar.workthrough;

import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
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

  private String dateTime;
  private String monthName;
  private int monthValue;
  private int buttonColor;
  private int buttonBackground;
  private String dayNumberString;

  public NextDate ()
  {
    this.rightNow = Calendar.getInstance ();
	
	//running date
    this.currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
    this.currentMonth = rightNow.get(Calendar.MONTH);
    this.currentYear = rightNow.get(Calendar.YEAR);

	//date of current day
    this.chosenDayOfMonth = currentDayOfMonth;
    this.chosenMonth = currentMonth;
    this.chosenYear = currentYear;
    
    this.convertWeekDays = new int[] 
	{Calendar.MONDAY, Calendar.TUESDAY,
	Calendar.WEDNESDAY, Calendar.THURSDAY,
	Calendar.FRIDAY, Calendar.SATURDAY,
	Calendar.SUNDAY};
	
	init();
  }
  
  	public void init ()
	{
		//set calendar to the 1st day of the month
		rightNow.set(currentYear,currentMonth,1);

		//get the day of week of the 1st day of this month
		this.dayOfWeekOfTheFirstDayOfMonth = rightNow.get(Calendar.DAY_OF_WEEK);
		this.daysSinceMonday = countDaysSinceMonday();

		//set calendar to Monday before the 1st (or do nothing if 1st was on Monday)
		rightNow.add(Calendar.DAY_OF_MONTH,-1*(daysSinceMonday));
		currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
	}

	public String dateToString ()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		try {
				dateTime = dateFormat.format(rightNow.getTime());
		} catch (Exception e) {
				e.printStackTrace();
		}
		return dateTime;
	} 
	
	public String monthYearToString ()
	{
		switch (currentMonth) {
			case 0:
				monthName = "styczeń";
				break;
			case 1:
				monthName = "luty";
				break;
			case 2:
				monthName = "marzec";
				break;
			case 3:
				monthName = "kwiecień";
				break;
			case 4:
				monthName = "maj";
				break;
			case 5:
				monthName = "czerwiec";
				break;
			case 6:
				monthName = "lipiec";
				break;
			case 7:
				monthName = "sierpień";
				break;
			case 8:
				monthName = "wrzesień";
				break;
			case 9:
				monthName = "październik";
				break;
			case 10:
				monthName = "listopad";
				break;
			case 11:
				monthName = "grudzień";
				break;
			default:
				monthName = "miesiąc";
				break;
		}
		return monthName + " " + currentYear;
	}

  public void addOneDay ()
  {
    rightNow.add(Calendar.DAY_OF_MONTH,1);
    currentDayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
  }
  
  public void addMonths (int value)
  {
	  rightNow.add(Calendar.MONTH,value);
	  if (rightNow.get(Calendar.MONTH) == 12
	  		&& value > 0) 
			rightNow.add(Calendar.MONTH,1);
	  if (rightNow.get(Calendar.MONTH) == 12
		  && value < 0) 
		  rightNow.add(Calendar.MONTH,-1);
	  currentMonth = rightNow.get(Calendar.MONTH);
	  currentYear = rightNow.get(Calendar.YEAR);
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
	  buttonColor = Color.GREEN;
      }
    else
      buttonColor = Color.GRAY;
    return buttonColor;
  }

  public int whatBackground ()
  {
/*
  if (chosenDayOfMonth == rightNow.get(Calendar.DAY_OF_MONTH) &&
	    chosenMonth == rightNow.get(Calendar.MONTH) &&
	    chosenYear == rightNow.get(Calendar.YEAR))
      buttonBackground = Color.GREEN;
    else
*/
      buttonBackground = Color.TRANSPARENT;
    return buttonBackground;
  }

  public String getDayNumber ()
  {
    dayNumberString = "" + currentDayOfMonth;
    return dayNumberString;
  }
  
  public void resetMonthFirst()
  {
	  currentMonth = chosenMonth;
	  rightNow.set(Calendar.MONTH,chosenMonth);
	  resetMonth();
  }
  
	public void resetMonth()
	{
		rightNow.set(currentYear,currentMonth,1);
	}

}

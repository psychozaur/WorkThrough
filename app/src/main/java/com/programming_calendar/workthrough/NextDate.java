package com.programming_calendar.workthrough;

pimport java.time.LocalDate;
import java.time.DayOfWeek;
import android.graphics.Color;

public class NextDate
{
    private LocalDate rightNow;

    private DayOfWeek dayOfWeekOfTheFirstDayOfMonth;

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
        this.rightNow = LocalDate.now ();

        this.dayOfWeekOfTheFirstDayOfMonth =
                rightNow.withDayOfMonth (1).getDayOfWeek ();

        this.currentDayOfMonth = rightNow.getDayOfMonth ();
        this.currentMonth = rightNow.getMonthValue ();
        this.currentYear = rightNow.getYear ();

        this.chosenDayOfMonth = currentDayOfMonth;
        this.chosenMonth = currentMonth;
        this.chosenYear = currentYear;

        rightNow =
                rightNow.withDayOfMonth (1).minusDays (dayOfWeekOfTheFirstDayOfMonth.
                        getValue () -
                        DayOfWeek.MONDAY.getValue ());
    }

    public void addOneDay ()
    {
        rightNow = rightNow.plusDays (1);
    }

    public int whatTextColor ()
    {
        if (currentMonth == rightNow.getMonthValue ())
        {
            buttonColor = Color.BLACK;
            if (chosenDayOfMonth == rightNow.getDayOfMonth () &&
                    chosenMonth == rightNow.getMonthValue () &&
                    chosenYear == rightNow.getYear ())
                buttonColor = Color.WHITE;
        }
        else
            buttonColor = Color.GRAY;
        return buttonColor;
    }

    public int whatBackground ()
    {
        if (chosenDayOfMonth == rightNow.getDayOfMonth () &&
                chosenMonth == rightNow.getMonthValue () &&
                chosenYear == rightNow.getYear ())
            buttonBackground = Color.GREEN;
        else
            buttonBackground = Color.TRANSPARENT;
        return buttonBackground;
    }

    public String getDayNumber ()
    {
        dayNumberString = "" + rightNow.getDayOfMonth ();
        return dayNumberString;
    }

}
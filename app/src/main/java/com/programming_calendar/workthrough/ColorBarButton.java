package com.programming_calendar.workthrough;

import android.support.v7.widget.AppCompatButton;
import android.content.Context;
import android.graphics.Canvas;
import java.util.List;
import java.util.ArrayList;

public class ColorBarButton extends AppCompatButton
{
		public ColorBarDrawable mDrawable;

         private List<ProgHours> mDayProgHoursList;
         private double mProductiveHours;

         private String output;

	public ColorBarButton(Context context, List dayProgHoursList, double productiveHours) {
			super(context);

				mDayProgHoursList = new ArrayList<ProgHours>(dayProgHoursList);
				mProductiveHours = productiveHours;
				mDrawable = new ColorBarDrawable(mDayProgHoursList, mProductiveHours);

		}

	public List getDayProgHoursList(){
		return mDayProgHoursList;
	}
 
		public ColorBarDrawable getDrawable(){
			return mDrawable;
		}

		public String getString(){
		output = "";
		for (int i = 0; i < mDayProgHoursList.size(); i++){
			output += "(" + mDayProgHoursList.get(i).getColor() + "," + mDayProgHoursList.get(i).getHours() + ") ";
		}
		return output;
	}

		@Override 
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			mDrawable.draw(canvas);
		}

}